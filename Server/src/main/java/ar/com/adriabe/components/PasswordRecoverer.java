package ar.com.adriabe.components;


import ar.com.adriabe.services.ServiceException;

public interface PasswordRecoverer {

    public static final String INVALID_USERNAME_OR_EMAIL_ERROR = "Nombre de usuario o correo electronico invalido";
    public static final String EMAIL_ERROR = "No se pudo enviar el correo de notificación.";

    public void recover(String username, String email) throws ServiceException;
}
