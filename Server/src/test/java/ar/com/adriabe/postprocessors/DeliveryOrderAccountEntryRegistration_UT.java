package ar.com.adriabe.postprocessors;

import ar.com.adriabe.components.AuditorUserLocator;
import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.processors.delivery.postprocess.DeliveryOrderAccountEntryRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DeliveryOrderAccountEntryRegistration_UT {

    public static final double DUMMY_TOTAL_AMOUNT = 1000.0;
    @Mock
    AccountDao accountDao;
    DeliveryOrderAccountEntryRegistration deliveryOrderAccountEntryRegistration;
    @Mock
    private AuditorUserLocator auditorUserLocator;
    @Mock
    private ClientDao clientDao;

    @Test
    public void registerAccountForDeliveryOrder() throws Exception {
        deliveryOrderAccountEntryRegistration = new DeliveryOrderAccountEntryRegistration(accountDao, auditorUserLocator,clientDao);

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(10l);
        deliveryOrder.setClient(new Client());
        deliveryOrder.setTotalAmount(DUMMY_TOTAL_AMOUNT);
        deliveryOrder.setDate(new Date());

        ArgumentCaptor<Account> argument = ArgumentCaptor.forClass(Account.class);


        DeliveryOrderContext target = new DeliveryOrderContext(deliveryOrder, false, null);
        deliveryOrderAccountEntryRegistration.execute(target);

        verify(accountDao).save(argument.capture());
        assertEquals(deliveryOrder.getClient(), argument.getValue().getClient());
        assertEquals(deliveryOrder.getDate(), argument.getValue().getDate());
        assertEquals(deliveryOrder.getTotalAmount(), argument.getValue().getDebit());
        assertEquals(DeliveryOrderAccountEntryRegistration.ZERO_VALUE, argument.getValue().getCredit());
        assertThat(argument.getValue().getConcept()).isEqualTo(deliveryOrder.getConceptAccountable());

    }
}
