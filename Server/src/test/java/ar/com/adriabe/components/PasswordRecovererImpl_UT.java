package ar.com.adriabe.components;

import ar.com.adriabe.model.User;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PasswordRecovererImpl_UT {

    public static final String DUMMY_USERNAME = "DUMMY_USERNAME";
    public static final String DUMMY_EMAIL = "DUMMY_EMAIL";
    public static final String DUMMY_OLD_PASSWORD = "DUMMY_OLD_PASSWORD";
    public static final long DUMMY_ID = 10l;


    PasswordRecoverer recoverer;
    @Mock
    private UserService userService;
    @Mock
    private MailSender mailSender;
    @Mock
    private SimpleMailMessage mailForgotPasswordMessage;

    @Before
    public void setUp() throws Exception {
        recoverer = new PasswordRecovererImpl(userService, mailSender, mailForgotPasswordMessage);


    }

    @Test
    public void emailInvalidWhenPasswordIsRecovered() throws Exception {

        User invalidUser = new User(DUMMY_ID, DUMMY_EMAIL, DUMMY_OLD_PASSWORD, DUMMY_USERNAME);
        when(userService.loadUserByUsername(DUMMY_EMAIL)).thenReturn(invalidUser);

        try {
            recoverer.recover(DUMMY_EMAIL, DUMMY_EMAIL);
            fail("should have failed given invalid user");
        } catch (ServiceException e) {
            assertThat(e).hasMessage(PasswordRecoverer.INVALID_USERNAME_OR_EMAIL_ERROR);
        }

        verify(userService, never()).saveOrUpdate(any(User.class));

    }

    @Test
    public void usernameInvalidWhenPasswordIsRecovered() throws Exception {

        when(userService.loadUserByUsername(DUMMY_USERNAME)).thenThrow(new UsernameNotFoundException(""));

        try {
            recoverer.recover(DUMMY_USERNAME, DUMMY_EMAIL);
            fail("should have failed given invalid user");
        } catch (ServiceException e) {
            assertThat(e).hasMessage(PasswordRecoverer.INVALID_USERNAME_OR_EMAIL_ERROR);
        }

        verify(userService, never()).saveOrUpdate(any(User.class));

    }

    @Test
    public void emailIsSentWhenPasswordIsRecovered() throws Exception {
        User user = new User(DUMMY_ID, DUMMY_USERNAME, DUMMY_OLD_PASSWORD, DUMMY_EMAIL);
        when(userService.loadUserByUsername(DUMMY_USERNAME)).thenReturn(user);


        recoverer.recover(DUMMY_USERNAME, DUMMY_EMAIL);


        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void newPasswordIsGeneratedWhenPasswordIsRecovered() throws Exception {
        User user = new User(DUMMY_ID, DUMMY_USERNAME, DUMMY_OLD_PASSWORD, DUMMY_EMAIL);
        when(userService.loadUserByUsername(DUMMY_USERNAME)).thenReturn(user);


        recoverer.recover(DUMMY_USERNAME, DUMMY_EMAIL);

        verify(userService).saveOrUpdate(any(User.class));
    }

}