package ar.com.adriabe.auditing.services;


import ar.com.adriabe.components.AuditorUserLocator;
import ar.com.adriabe.daos.OperationLogDao;
import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.model.User;
import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 12/13/14.
 */
@Aspect
public class ServiceAuditorAware {

    @Autowired
    OperationLogDao operationLogDao;

    @Autowired
    AuditorUserLocator userLocator;

    private OperationLog op;

    @Before(value = "@annotation(ar.com.adriabe.model.common.annotation.ILogableOperation) && @annotation(iLogableOperation)")
    public void logBefore(JoinPoint joinPoint, ILogableOperation iLogableOperation) {
        op = new OperationLog();
        op.setCreatedDate(new Date());
        op.setAction(iLogableOperation.type());
        op.setTitle(joinPoint.getSignature().toShortString() + "   " + joinPoint.getSignature().getDeclaringTypeName());
        User user = userLocator.locateLoggedUser();
        op.setUsername(user.getUsername());
        op.setIntention(iLogableOperation.desc());
        op.setOperationStart(new Date());
        for (Object obj : joinPoint.getArgs()) {
            if (obj != null && obj instanceof AuditableDomainObject) {
                if (((AuditableDomainObject) obj).isNew()) {
                    ((AuditableDomainObject) obj).setCreatedBy(user);
                    ((AuditableDomainObject) obj).setCreatedDate(new DateTime());
                }
                ((AuditableDomainObject) obj).setLastModifiedBy(user);
                ((AuditableDomainObject) obj).setLastModifiedDate(new DateTime());
            }
        }


    }

    @AfterReturning(returning = "result", pointcut = "@annotation(ar.com.adriabe.model.common.annotation.ILogableOperation)")
    public void logAfter(JoinPoint joinPoint, Object result) throws Exception {

        op.setCompleted(true);
        if (result instanceof List) {
            String resultStr = "Lista vacia";
            if (!((List) result).isEmpty()) {
                resultStr = "Lista de " + ((List) result).get(0).getClass().getSimpleName() + " con " + ((List) result).size() + " elementos";
            }
            op.setResult(resultStr);
        } else {
            op.setResult(result);
        }
        op.setOperationEnded(new Date());
        operationLogDao.save(op);
    }


    @AfterThrowing(throwing = "error", pointcut = "@annotation(ar.com.adriabe.model.common.annotation.ILogableOperation)")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) throws Exception {

        op.setCompleted(false);
        op.setError(error.getMessage());
        op.setOperationEnded(new Date());
        operationLogDao.save(op);
        throw new Exception(error);
    }


}