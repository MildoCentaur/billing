package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.daos.ListPriceDao;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {

    final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    @Autowired
    ClientDao clientDao;
    @Autowired
    ListPriceDao listPriceDao;
    @Autowired
    AccountDao accountDao;

    public List<Client> findAllClients(String query) {

        return clientDao.findAllActiveClients(query);
    }

    public Client findClientById(Long id) {
        return clientDao.findById(id);
    }

    @Override
    public List<ListPrice> findAllActiveListPrices() {
        return clientDao.findAllActiveListPrices();
    }

    @Override
    @Transactional
    public void save(Client client) throws ServiceException {
        try {
            logger.debug("Checkig if updating or saving a new a Client");

            if (client.getId() != 0) {
                client = clientDao.update(client);
            }
            clientDao.save(client);
            logger.debug("Client Saved");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public void delete(Long idClient) throws ServiceException {
        try {
            Client client = null;
            logger.debug("Delete a Client logically");
            logger.debug("Checkig if id is valid");
            if (idClient != null && idClient > 0) {
                client = clientDao.findById(idClient);
            }
            if (client != null) {
                logger.error("deleting the client for the logic deletion");
                clientDao.delete(client);
            }
            logger.debug("Client deleted");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }
}
