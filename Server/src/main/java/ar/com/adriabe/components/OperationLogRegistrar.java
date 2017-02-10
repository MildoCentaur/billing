package ar.com.adriabe.components;

import ar.com.adriabe.daos.OperationLogDao;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class OperationLogRegistrar {

    AuditorUserLocator auditorUserLocator;

    OperationLogDao operationLogDao;

    @Autowired
    public OperationLogRegistrar(AuditorUserLocator auditorUserLocator, OperationLogDao operationLogDao) {
        this.auditorUserLocator = auditorUserLocator;
        this.operationLogDao = operationLogDao;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OperationLog register(OperationLogProvider provider) throws KendoExecutionException {
        OperationLog log = new OperationLog();
        log.setCreatedDate(new Date());
        log.setCompleted(provider.isCompleted());
        log.setIntention(provider.getOperationIntention());
        log.setTitle(provider.getOperationTitle());
        log.setUsername(auditorUserLocator.locateLoggedUser().getUsername());
        log.setOperationStart(provider.getOperationStart());
        log.setOperationEnded(new Date());
        log.setResult(provider.getStringResult());
        log.setError(provider.getMessage());
        log.setStatus(provider.getActionResult());
        log.setAction(provider.getActionType());
        operationLogDao.save(log);
        return log;
    }

    public User getLoggedUser() {
        return auditorUserLocator.locateLoggedUser();
    }
}
