package ar.com.adriabe.components;

import ar.com.adriabe.model.User;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.services.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Component("passwordRecoverer")
public class PasswordRecovererImpl implements PasswordRecoverer {

    private static final int AMOUNT_LETTERS = 26;

    private UserService userService;

    private MailSender mailSender;

    private SimpleMailMessage mailForgotPasswordMessage;

    @Autowired
    public PasswordRecovererImpl(UserService userService, MailSender mailSender, SimpleMailMessage mailForgotPasswordMessage) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.mailForgotPasswordMessage = mailForgotPasswordMessage;
    }

    @Override
    @Transactional
    public void recover(String username, String email) throws ServiceException {

        User user = getUser(username, email);
        SimpleMailMessage smm = new SimpleMailMessage(mailForgotPasswordMessage);
        smm.setTo(email);
        String text = smm.getText();
        String newPassword = generatePassword();

        user.setPassword(newPassword);
        text = StringUtils.replace(text, "%PASSWORD%", newPassword);
        smm.setText(text);

        try {
            userService.saveOrUpdate(user);
            mailSender.send(smm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(EMAIL_ERROR);
        }


    }

    protected User getUser(String username, String email) throws ServiceException {
        if (username.isEmpty() || email.isEmpty()) {
            throw new ServiceException(INVALID_USERNAME_OR_EMAIL_ERROR);
        }
        User user;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (Exception e) {
            throw new ServiceException(INVALID_USERNAME_OR_EMAIL_ERROR);
        }
        if (user == null || user.getId() == 0 || StringUtils.isEmpty(user.getEmail()) || email.compareToIgnoreCase(user.getEmail()) != 0) {
            throw new ServiceException(INVALID_USERNAME_OR_EMAIL_ERROR);
        }
        return user;
    }

    private String generatePassword() {
        String pass = "";
        long pos;
        char character;
        Random randnum = new Random();
        Date date = new Date();
        randnum.setSeed(date.getTime());
        for (int i = 0; i < 8; i++) {

            switch (i) {
                case 0:
                case 3:
                case 7:
                    pos = Math.round(randnum.nextInt(9));
                    character = (char) ('0' + pos);
                    pass = pass + character;
                    break;
                case 2:
                    pos = Math.round(randnum.nextInt(AMOUNT_LETTERS));
                    character = (char) ('A' + pos);
                    pass = pass + character;
                    break;
                default:
                    pos = Math.round(randnum.nextInt(AMOUNT_LETTERS));
                    character = (char) ('a' + pos);
                    pass = pass + character;
                    break;
            }
        }
        return pass;
    }
}
