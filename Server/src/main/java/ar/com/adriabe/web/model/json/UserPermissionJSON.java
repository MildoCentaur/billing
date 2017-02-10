package ar.com.adriabe.web.model.json;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Created by ajmild1 on 12/03/2015.
 */
@JsonRootName("userPermission")
public class UserPermissionJSON {
    private long idUser;
    private long idPermission;

    private String permission;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(long idPermission) {
        this.idPermission = idPermission;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


}
