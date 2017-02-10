package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ClientContact;
import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.model.Store;
import ar.com.adriabe.repositories.ClientContactRepository;
import ar.com.adriabe.repositories.ClientRepository;
import ar.com.adriabe.repositories.ClientStoreRepository;
import ar.com.adriabe.repositories.ListPriceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */
@Component("clientDao")
public class ClientDaoImpl implements ClientDao {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientContactRepository clientContactRepository;

    @Autowired
    ClientStoreRepository clientStoreRepository;



    @Autowired
    ListPriceRepository listPriceRepository;

    final Logger logger =  LogManager.getLogger(ClientDaoImpl.class);

    @Override
    public List<Client> findAllActiveClients(String query) {

        if(query==null || query.isEmpty()) {
            return clientRepository.findByDeleted(false);
        }
        query='%'+query+'%';
        return clientRepository.findLikeName(query);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public List<ListPrice> findAllActiveListPrices() {
        return listPriceRepository.findByDeleted(false);
    }

    @Override
    public Client save(Client client) throws DaoException{
        logger.debug("Saving Client");
        client = clientRepository.save(client);
        logger.debug("Client Saved");
        return client;

    }

    @Override
    public void deleteContacts(List<ClientContact> list){
        for(ClientContact cc : list){
            clientContactRepository.delete(cc);
        }
    }

    @Override
    public void deleteStores(List<Store> list){
        for(Store store : list){
            clientStoreRepository.delete(store);
        }
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client update(Client client) throws DaoException {
        Client old = clientRepository.findOne(client.getId());
        deleteContacts(old.getContacts());
        deleteStores(old.getStores());
        old.setAddress(client.getAddress());
        old.setAddressDelivery(client.getAddressDelivery());
        old.setBalanceActivity(client.getBalanceActivity());
        old.setBalanceBilling(client.getBalanceBilling());
        old.setClientType(client.getClientType());
        old.setComments(client.getComments());
        old.setCuit(client.getCuit());
        old.setCredit(client.getCredit());
        old.setCreditInCheques(client.getCreditInCheques());
        old.setCountry(client.getCountry());
        old.setContacts(client.getContacts());
        old.setEmail(client.getEmail());
        old.setFax(client.getFax());
        old.setIVACondition(client.getIVACondition());
        old.setNickname(client.getNickname());
        old.setName(client.getName());
        old.setNextel(client.getNextel());
        old.setPhone(client.getPhone());
        old.setPostalCode(client.getPostalCode());
        old.setState(client.getState());
        old.setStores(client.getStores());
        old.setLocalidad(client.getLocalidad());
        old.setDeleted(false);


        return old;
    }
}
