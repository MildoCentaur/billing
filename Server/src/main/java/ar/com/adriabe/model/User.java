// Generated by DB Solo 4.1.2 on 23/05/2011 at 19:14:43
package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends DomainObject implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = -4154918648749185830L;
    private String username;
    private String password;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_permission",
            joinColumns = {@JoinColumn(table = "users", name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(table = "role_permission", name = "role_permission_id")})
    @OrderBy("name")
    private List<RolePermission> permissions;
    @Transient
    private boolean enabled = true;
    @Transient
    private boolean accountExpired = false;
    @Transient
    private boolean accountLocked = false;
    @Transient
    private boolean credentialsExpired = false;

    //
    // constructors
    //
    public User() {
    }

    public User(Long iduser) {
        setId(iduser);
    }

    public User(Long iduser, String name, String password, String email) {
        setId(iduser);
        this.username = name;
        this.password = password;
        this.email = email;

    }

    //
    // getters/setters
    //

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the accountExpired
     */
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired the accountExpired to set
     */
    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @return the accountLocked
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @param accountLocked the accountLocked to set
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @return the credentialsExpired
     */
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @param credentialsExpired the credentialsExpired to set
     */
    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * @return the roles
     */
    public List<RolePermission> getRolePermission() {
        return permissions;
    }

    /**
     * @param rolePermissions the roles to set
     */
    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.permissions = rolePermissions;
    }


    public void addRolePermission(RolePermission rolePermission) {
        if (rolePermission == null) {
            return;
        }
        if (permissions == null) {
            permissions = new ArrayList<RolePermission>();
        }
        permissions.add(rolePermission);
    }

    public boolean hasPermission(Long id) {
        if (id == null || id < 0) {
            return false;
        }
        for (RolePermission permission : permissions) {
            if (permission.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
