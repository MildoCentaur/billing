package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.OperationLogDao;
import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.repositories.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 12/17/14.
 */
@Component("operationLogDao")
public class OperationLogDaoImpl implements OperationLogDao {

    @Autowired
    OperationLogRepository operationLogRepository;

    @Override
    public List<OperationLog> findAll() {
        return operationLogRepository.findAll();
    }


    @Override
    public void save(OperationLog operationLog) {
        operationLogRepository.save(operationLog);
    }


}
