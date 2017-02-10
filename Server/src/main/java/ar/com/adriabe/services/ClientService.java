package ar.com.adriabe.services;

import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */

public interface ClientService {

    public List<Client> findAllClients(String query);

    public Client findClientById(Long id);

    public List<ListPrice> findAllActiveListPrices();

    @ILogableOperation(desc = "Registra o actualiza un nuevo cliente",type = ACTION_TYPE.CREATE)
    public void save(Client client) throws ServiceException;

    @ILogableOperation(desc = "Borrado lógico de un cliente",type = ACTION_TYPE.DELETE)
    void delete(Long idCliente) throws ServiceException;

}

