package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.UserDao;
import ar.com.adriabe.model.Role;
import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAllActive();
    }

    @Override
    public List<Role> findAllRoles() {
        return userDao.findAllRoles();
    }

    @Override
    public List<RolePermission> findAllPermissions() {
        return userDao.findAllPermissions();
    }

    @Override
    @ILogableOperation(type = ACTION_TYPE.UPDATE, desc = "Actualizar permisos de acceso.")
    public void assignPermissions(List<User> users) {
        userDao.save(users);
    }

    @Override
    @ILogableOperation(type = ACTION_TYPE.UPDATE, desc = "Actualizar datos del usuario")
    public void saveOrUpdate(User user) throws ServiceException, Exception {
        if (user.getId() == 0 && userDao.getByUsername(user.getUsername()) != null) {
            throw new ServiceException("Nombre de usuario ya registrado.");
        }

        if (user.getId() > 0) {
            User stored = findUserById(user.getId());
            stored.setEmail(user.getEmail());
            stored.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            stored.setUsername(user.getUsername());
            user = stored;
        }
        userDao.save(user);
    }

    @Override
    @ILogableOperation(type = ACTION_TYPE.UPDATE, desc = "Actualizar datos del usuario")
    public void delete(Long id) {
        if (id != null && id > 0) {
            userDao.delete(id);
        }
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
