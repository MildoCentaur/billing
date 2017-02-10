package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.UserDao;
import ar.com.adriabe.model.Role;
import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;
import ar.com.adriabe.repositories.RolePermissionRepository;
import ar.com.adriabe.repositories.RoleRepository;
import ar.com.adriabe.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDao")
public class UserDaoImpl implements UserDao {

    private Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

	/* (non-Javadoc)
     * @see ar.com.eureka.common.services.impl.UserService#loadUserByUsername(java.lang.String)
	 */

    @Override
    public User findByUsername(String username)
            throws UsernameNotFoundException {

        List<User> users = userRepository.findByUsername(username);

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        throw new UsernameNotFoundException("User not found");

    }

    @Override
    public User getByUsername(String username) {

        List<User> users = userRepository.findByUsername(username);

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;

    }

    @Override
    public User add(User u) throws Exception {
        return userRepository.save(u);

    }

    @Override
    public List<User> findAllActive() {

        return userRepository.findByDeleted(false);
    }

	/* (non-Javadoc)
     * @see ar.com.eureka.common.services.impl.UserService#getUserById(int)
	 */

    @Override
    public User findUserById(Long idUser) throws Exception {
        return userRepository.findOne(idUser);
    }


    @Override
    public void update(User u) throws Exception {
        User u2 = findUserById(u.getId());
        u2.setPassword(u.getPassword());
        u2.setEmail(u.getEmail());
        u2.setUsername(u.getUsername());
        userRepository.save(u2);
    }

    @Override
    public List<Role> findAllRoles() {

        return roleRepository.findAll();
    }


    @Override
    public List<RolePermission> findAllPermissions() {
        return rolePermissionRepository.findAll();
    }

    @Override
    public void save(List<User> users) {
        userRepository.save(users);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findAnonymousUser() {
        List<User> users = userRepository.findByUsername("__anonymous__");

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        User user = new User();
        user.setUsername("__anonymous__");

        return user;

    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}