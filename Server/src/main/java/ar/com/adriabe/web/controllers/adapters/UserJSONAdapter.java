package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;
import ar.com.adriabe.web.model.json.UserPermissionJSON;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserJSONAdapter {

    public List<User> adaptUserPermissionJSONToUser(List<UserPermissionJSON> userPermissions, List<User> users, List<RolePermission> permissions) throws KendoAdapterException {
        Collections.sort(userPermissions, new Comparator<UserPermissionJSON>() {
            @Override
            public int compare(UserPermissionJSON userPermission1, UserPermissionJSON userPermission2) {
                long user = userPermission1.getIdUser() - userPermission2.getIdUser();
                long perm = userPermission1.getIdPermission() - userPermission2.getIdPermission();
                return (int) ((user == 0) ? perm : user);
            }
        });

        for (User user : users) {
            user.setRolePermissions(null);
        }
        User user;
        int iu = 0;
        for (UserPermissionJSON userPermission : userPermissions) {
            //Search for proper user
            user = users.get(iu);
            while (userPermission.getIdUser() > user.getId()) {
                user = users.get(iu++);
            }
            if (userPermission.getIdUser() != user.getId()) {
                throw new KendoAdapterException("Se desea asignar permisos a un usuario no reconocido.");
            }
            //Search for proper permission
            for (int j = 0; j < permissions.size(); j++) {
                if (userPermission.getIdPermission() == permissions.get(j).getId()) {
                    user.addRolePermission(permissions.get(j));
                    j = permissions.size();
                }
            }


        }
        return null;
    }
}
