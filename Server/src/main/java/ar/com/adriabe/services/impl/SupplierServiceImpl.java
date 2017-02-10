package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.SupplierDao;
import ar.com.adriabe.model.Supplier;
import ar.com.adriabe.services.SupplierService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

    final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    @Autowired
    SupplierDao supplierDao;

    @Override
    public List<Supplier> findAllSuppliers(String name) {
        return supplierDao.findAllActiveSuppliers(name);

    }

    @Override
    public void save(Supplier supplier) {
        logger.debug("Saving a Client");
        supplierDao.save(supplier);
        logger.debug("Client Saved");

    }


    @Override
    public Supplier findSupplierById(Long id) {
        return supplierDao.findById(id);
    }
}
