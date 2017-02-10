package ar.com.adriabe.services;

import ar.com.adriabe.model.Supplier;

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
public interface SupplierService {

    public List<Supplier> findAllSuppliers(String name);

    public void save(Supplier supplier);

    public Supplier findSupplierById(Long id);
}
