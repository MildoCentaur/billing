package ar.com.adriabe.components;

import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.model.constant.ACTION_TYPE;

import java.util.Date;


public interface OperationLogProvider {
    boolean isCompleted();

    String getOperationIntention();

    String getOperationTitle();

    Date getOperationStart();

    String getStringResult();

    String getMessage();

    void setMessage(String message);

    OperationLog.ACTION_RESULT getActionResult();

    ACTION_TYPE getActionType();
}
