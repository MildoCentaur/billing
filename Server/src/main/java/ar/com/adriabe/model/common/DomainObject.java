package ar.com.adriabe.model.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by AJMILD1 on 03/06/14.
 */
@MappedSuperclass
public class DomainObject extends AbstractPersistable<Long> implements IDeleteable,Serializable {
    private boolean deleted=false;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
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
