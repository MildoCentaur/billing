package ar.com.adriabe.daos;

import ar.com.adriabe.model.Fabric;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
public interface FabricDao {
    public List<Fabric> findAll();


    public List<Fabric> findLikeName(String name);


    public Fabric findById(Long id);


    public void save(Fabric fabric);

    void delete(Fabric fabric);
}
