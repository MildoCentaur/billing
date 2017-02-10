package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.components.AuditorUserLocator;
import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.postprocessors.AbstractAccountEntryRegistration;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderAccountEntryRegistration extends AbstractAccountEntryRegistration implements Executable<DeliveryOrderContext> {

    DeliveryOrderContext context;
    ClientDao clientDao;


    @Autowired
    public DeliveryOrderAccountEntryRegistration(AccountDao accountDao, AuditorUserLocator auditorUserLocator, ClientDao clientDao) {
        super(accountDao, auditorUserLocator);
        this.clientDao = clientDao;
    }

    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext target) throws KendoExecutionException {
        try {
            context = target;
            Client client = context.getDeliveryOrder().getClient();
            double balance = client.getBalanceActivity() + context.getDeliveryOrder().getTotalAmount();
            client.setBalanceActivity(balance);
            clientDao.save(client);
            generateAccount(target.getDeliveryOrder());
        } catch (DaoException e) {
            e.printStackTrace();
            throw new KendoExecutionException("No se pudo actualizar el saldo del cliente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al registrar el movimiento en la cuenta.");
        }

        return target;
    }

    @Override
    protected void setDocument(Account account) {
        account.setDeliveryOrder(context.getDeliveryOrder());
    }


    @Override
    protected void setAccountableData(Account account) {

        account.setDebit(context.getDeliveryOrder().getTotalAmount());
        account.setCredit(ZERO_VALUE);
        account.setBalance(context.getDeliveryOrder().getClient().getBalanceActivity());

    }
}
