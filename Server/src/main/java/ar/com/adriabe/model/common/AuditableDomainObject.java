package ar.com.adriabe.model.common;

import ar.com.adriabe.model.User;
import ar.com.adriabe.web.json.CustomDateTimeSerializer;
import ar.com.adriabe.web.json.CustomUserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by AJMILD1 on 03/06/14.
 */
@MappedSuperclass
public class AuditableDomainObject extends AbstractAuditable<User,Long> implements IDeleteable,Serializable {
    private boolean deleted=false;


    @Override
    @JsonIgnore
    public boolean isNew() {
        return super.isNew() || getId()==0;
    }


    @Override
    @JsonSerialize(using = CustomUserSerializer.class)
    public User getCreatedBy() {
        return super.getCreatedBy();
    }


    @Override
    public void setCreatedBy(User createdBy) {
        super.setCreatedBy(createdBy);
    }


    @Override
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public DateTime getCreatedDate() {
        return super.getCreatedDate();
    }


    @Override
    public void setCreatedDate(DateTime createdDate) {
        super.setCreatedDate(createdDate);
    }


    @Override
    @JsonSerialize(using = CustomUserSerializer.class)
    public User getLastModifiedBy() {
        return super.getLastModifiedBy();
    }


    @Override
    public void setLastModifiedBy(User lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
    }


    @Override
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public DateTime getLastModifiedDate() {
        return super.getLastModifiedDate();
    }


    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        super.setLastModifiedDate(lastModifiedDate);
    }

    @Override
    public void setId(Long id){
        super.setId(id);
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted=deleted;
    }
}
