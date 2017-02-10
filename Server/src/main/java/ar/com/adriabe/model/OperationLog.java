package ar.com.adriabe.model;

import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.model.constant.EurekaConstants;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "operation_log")
public class OperationLog extends AbstractPersistable<Long> {

    /**
     *
     */

    private static final long serialVersionUID = 1686641610457819890L;
    @Enumerated(EnumType.STRING)
    private ACTION_TYPE action;

    private String username;

    private String title;
    @Lob
    @Column(columnDefinition = "TEXT", length = 65535)
    private String result;

    private boolean completed = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private ACTION_RESULT status;
    private String intention;

    @Lob
    @Column(columnDefinition = "TEXT", length = 65535)
    private String error;

    @Temporal(TemporalType.TIME)
    private Date operationStart;

    @Temporal(TemporalType.TIME)
    private Date operationEnded;


    public OperationLog() {
        this.error = "";
        this.result = "";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Auditable#getUsername()
     */
    public String getUsername() {

        return username;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.domain.Auditable#setUsername(java.lang.Object)
     */
    public void setUsername(final String username) {

        this.username = username;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Auditable#getCreatedDate()
     */
    public Date getCreatedDate() {

        return createdDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.domain.Auditable#setCreatedDate(org.joda.time
     * .DateTime)
     */
    public void setCreatedDate(final Date date) {

        this.createdDate = null == date ? null : date;
    }

    /**
     * @return the action
     */
    public ACTION_TYPE getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(ACTION_TYPE action) {
        this.action = action;
    }

    @Transient
    public String getActionLabel() {
        return action.getLabel();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean b) {
        completed = b;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    public void setResult(Object result) {

        this.status = ACTION_RESULT.SUCCESS;
        this.result = "";
        if (result != null) {
            this.result = result.toString();
        }

    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.status = ACTION_RESULT.SUCCESS;
        this.result = result;
    }

    @Transient
    public String getDate() {
        if (createdDate == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE);
        return format.format(createdDate);
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.status = ACTION_RESULT.FAILURE;
        this.error = error;
    }

    public ACTION_RESULT getStatus() {
        return status;
    }

    public void setStatus(ACTION_RESULT status) {
        this.status = status;
    }

    public Date getOperationEnded() {
        return operationEnded;
    }

    public void setOperationEnded(Date operationEnded) {
        this.operationEnded = operationEnded;
    }

    public Date getOperationStart() {
        return operationStart;
    }

    public void setOperationStart(Date operationStart) {
        this.operationStart = operationStart;
    }

    public enum ACTION_RESULT {
        SUCCESS, FAILURE;

    }
}
