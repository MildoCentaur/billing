package ar.com.adriabe.daos;

import ar.com.adriabe.model.Supplier;

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
public interface SupplierDao {

    List<Supplier> findAllActiveSuppliers(String name);

    void save(Supplier supplier);

    public Supplier findById(Long id);
}
