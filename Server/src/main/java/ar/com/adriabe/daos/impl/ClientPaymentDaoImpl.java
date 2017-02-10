package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ClientPaymentDao;
import ar.com.adriabe.model.ClientPayment;
import ar.com.adriabe.repositories.ClientPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mildo on 5/16/15.
 */
@Component
public class ClientPaymentDaoImpl implements ClientPaymentDao {

    @Autowired
    ClientPaymentRepository clientPaymentRepository;

    @Override
    public ClientPayment save(ClientPayment payment) {
        return clientPaymentRepository.save(payment);
    }
}
