package ar.com.adriabe.daos;

import ar.com.adriabe.model.OperationLog;

import java.util.List;

/**
 * Created by Mildo on 12/17/14.
 */
public interface OperationLogDao {

    public List<OperationLog> findAll();

    public void save(OperationLog operationLog);
}
