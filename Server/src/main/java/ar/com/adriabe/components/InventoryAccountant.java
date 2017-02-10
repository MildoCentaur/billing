package ar.com.adriabe.components;


import ar.com.adriabe.model.Barcode;
import ar.com.adriabe.services.ServiceException;

public interface InventoryAccountant {

    public Barcode incrementProductStock(String code, long amount) throws ServiceException;


}
