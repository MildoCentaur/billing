package ar.com.adriabe.components;

import ar.com.adriabe.model.Barcode;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.services.InvalidDataException;
import ar.com.adriabe.services.ServiceException;

/**
 * Created by ajmild1 on 21/08/2015.
 */
public interface BarcodeAnalyzer {

    Barcode generateBarcode(Product product, Double weight);

    Barcode scanBarcode(String code) throws InvalidDataException, ServiceException;
}
