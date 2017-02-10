package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.FabricDao;
import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.repositories.FabricRepository;
import ar.com.adriabe.repositories.FiberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
@Component("fabricDao")
public class FabricDaoImpl implements FabricDao {

    @Autowired
    FabricRepository fabricRepository;

    @Autowired
    FiberRepository fiberRepository;

    @Override
    public List<Fabric> findAll() {
        return fabricRepository.findByDeleted(false);
    }

    @Override
    public List<Fabric> findLikeName(String name) {
        name = '%'+name+'%';
        return fabricRepository.findLikeName(name);
    }

    @Override
    public Fabric findById(Long id) {
        return fabricRepository.findOne(id);
    }

    @Override
    public void save(Fabric fabric) {
        if (fabric.getId() != 0) {

            Fabric old = findById(fabric.getId());
            fiberRepository.delete(old.getFibers());
            old.update(fabric);

        }
        fabricRepository.save(fabric);
    }

    @Override
    public void delete(Fabric fabric) {
        fabricRepository.delete(fabric);
    }
}

