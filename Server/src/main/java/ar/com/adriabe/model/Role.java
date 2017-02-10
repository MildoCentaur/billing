package ar.com.adriabe.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
public class Role extends AbstractPersistable<Long> implements Comparable<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5442016525734889769L;
	private String name;
    private String description;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @OrderBy("id")
    private List<RolePermission> permissions;

    public Role() {
    }

    /**
     * Create a new instance and set the name.
     *
     * @param name name of the role.
     */
    public Role(final String name) {
        this.name = name;
    }
 

    @Column(length = 20, unique = true)
    public String getName() {
        return this.name;
    }

    @Column(length = 64)
    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int compareTo(Role other) {
        if(other== null){
            return 1;
        }
        return (int) (this.getId()- other.getId());
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
