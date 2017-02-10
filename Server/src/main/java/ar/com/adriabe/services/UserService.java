package ar.com.adriabe.services;

import ar.com.adriabe.model.Role;
import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findUserById(Long id) throws Exception;

    public List<User> findAll();

    List<Role> findAllRoles();

    List<RolePermission> findAllPermissions();

    void assignPermissions(List<User> users);

    void saveOrUpdate(User user) throws Exception;

    void delete(Long id);

}
