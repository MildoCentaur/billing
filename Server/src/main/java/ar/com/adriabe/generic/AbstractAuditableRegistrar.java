package ar.com.adriabe.generic;


import ar.com.adriabe.components.OperationLogProvider;
import ar.com.adriabe.components.OperationLogRegistrar;
import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.model.User;
import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public abstract class AbstractAuditableRegistrar<R, S> implements Registrar<R, S>, OperationLogProvider {


    OperationLogRegistrar operationLogRegistrar;
    FailFastProcessor postProcessor;
    Builder builder;
    private Date operationStart;
    private String errorMessage;
    private boolean completed = false;
    private OperationLog.ACTION_RESULT actionResult;
    private String resultMessage;

    public AbstractAuditableRegistrar(FailFastProcessor postProcessor, Builder builder, OperationLogRegistrar operationLogRegistrar) {
        this.postProcessor = postProcessor;
        this.builder = builder;
        this.operationLogRegistrar = operationLogRegistrar;
    }

    public R register(S provider) throws KendoExecutionException {

        this.operationStart = new Date();
        try {
            R context = registerProcess(provider);
            setCompleted();
            operationLogRegistrar.register(this);
            return (R) context;
        } catch (KendoExecutionException e) {
            setMessage(e.getMessage());
            operationLogRegistrar.register(this);
            throw new KendoExecutionException(e.getMessage());
        } catch (Exception e) {
            setMessage(e.getMessage());
            operationLogRegistrar.register(this);
            throw new KendoExecutionException("Proceso interrumpido.");
        }

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {KendoExecutionException.class, Exception.class, Throwable.class})
    public R registerProcess(S provider) throws KendoExecutionException {
        R context = (R) builder.build(provider);
        context = (R) doRegister(context);
        postProcessor.process(context);
        return (R) context;
    }


    protected abstract R doRegister(R context) throws KendoExecutionException;

    protected void setAuditableEntityData(AuditableDomainObject entity) {
        User loggedUser = operationLogRegistrar.getLoggedUser();
        DateTime createdDate = new DateTime(operationStart.getTime());
        entity.setCreatedBy(loggedUser);
        entity.setLastModifiedBy(loggedUser);
        entity.setCreatedDate(createdDate);
        entity.setLastModifiedDate(createdDate);
    }

    private void setCompleted() {
        completed = true;
        actionResult = OperationLog.ACTION_RESULT.SUCCESS;
    }

    @Override
    public Date getOperationStart() {
        return operationStart;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    @Override
    public void setMessage(String message) {
        this.errorMessage = message;
        actionResult = OperationLog.ACTION_RESULT.FAILURE;
    }

    @Override
    public OperationLog.ACTION_RESULT getActionResult() {
        return actionResult;
    }

    @Override
    public ACTION_TYPE getActionType() {
        return ACTION_TYPE.CREATE;
    }

    @Override
    public String getStringResult() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
