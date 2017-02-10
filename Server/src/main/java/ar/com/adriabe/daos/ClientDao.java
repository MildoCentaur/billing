package ar.com.adriabe.daos;

import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ClientContact;
import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.model.Store;

import java.util.List;

public interface ClientDao {

    public List<Client> findAllActiveClients(String name);


    public Client save(Client client) throws DaoException;

    public Client update(Client client) throws DaoException;

    public Client findById(Long id);

    public List<ListPrice> findAllActiveListPrices();

    public void deleteContacts(List<ClientContact> list);

    public void deleteStores(List<Store> list);

    void delete(Client client);
}
