package ar.com.adriabe.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.beans.Transient;

@Entity
@Table(name = "role_permission")
public class RolePermission extends AbstractPersistable<Long> implements GrantedAuthority, Comparable<RolePermission> {


    private String name;
    private String description;

    public RolePermission() {

    }

    public RolePermission(Long id, String name,String description) {
        super();
        this.setId(id);
        this.description = description;
        this.name = name;
    }

    // Interfaces Methods
    @Override
    public int compareTo(RolePermission other) {
        if (other == null) {
            return 1;
        }
        return (int) (this.getId() - other.getId());
    }

    @Override
    @Transient
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
