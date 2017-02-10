package ar.com.adriabe.daos;

import ar.com.adriabe.model.Role;
import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;

import java.util.List;


public interface UserDao {


    public User findByUsername(String username);

    public User getByUsername(String username);

    public User add(User u) throws Exception;

    public List<User> findAllActive();

    public User findUserById(Long idUser) throws Exception;

    public void update(User u) throws Exception;

    List<Role> findAllRoles();

    List<RolePermission> findAllPermissions();

    void save(List<User> users);

    void save(User user);

    void delete(Long id);

    User findAnonymousUser();
}
