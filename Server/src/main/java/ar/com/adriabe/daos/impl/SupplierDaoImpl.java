package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.SupplierDao;
import ar.com.adriabe.model.Supplier;
import ar.com.adriabe.repositories.SupplierRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
@Component("supplierDao")
public class SupplierDaoImpl implements SupplierDao{

    @Autowired
    SupplierRepository supplierRepository;

    final Logger logger =  LogManager.getLogger(SupplierDaoImpl.class);

    @Override
    public List<Supplier> findAllActiveSuppliers(String name) {
        if (name==null || name.isEmpty()){
            return supplierRepository.findByDeleted(false);
        }
        name='%'+name+'%';
        return supplierRepository.findLikeName(name);

    }

    @Override
    public void save(Supplier supplier) {
        logger.debug("Saving Supplier");
        supplierRepository.save(supplier);
        logger.debug("Supplier Saved");

    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findOne(id);
    }
}
