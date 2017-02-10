package ar.com.adriabe.components;

import ar.com.adriabe.daos.OperationLogDao;
import ar.com.adriabe.model.OperationLog;
import ar.com.adriabe.model.User;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationLogRegistrar_UT {

    public static final String OPERATION_INTENTION = "Operation Intention";
    public static final String OPERATION_TITLE = "Operation Title";
    public static final String ERROR_MESSAGE = "Error Message";
    public static final String DUMMY_USERNAME = "DUMMY_USERNAME";
    public static final long DUMMY_USER_ID = 10l;
    OperationLogProvider provider;

    OperationLogRegistrar registrar;

    @Mock
    private OperationLogDao operationlaLogDao;

    @Mock
    private AuditorUserLocator auditorUserLocator;

    @Before
    public void setUp() throws Exception {
        registrar = new OperationLogRegistrar(auditorUserLocator, operationlaLogDao);
    }


    @Test
    public void registerSuccesfullLogOperation() throws Exception {
        User user = createUser();
        when(auditorUserLocator.locateLoggedUser()).thenReturn(user);

        provider = createOperationLogProvider(OperationLog.ACTION_RESULT.SUCCESS, new Date());

        OperationLog log = registrar.register(provider);

        assertNull(log.getError());
        assertNotNull(log.getCreatedDate());
        assertEquals(log.getAction(), ACTION_TYPE.CREATE);
        assertEquals(log.getTitle(), OPERATION_TITLE);
        assertEquals(log.getIntention(), OPERATION_INTENTION);
        assertNotNull(log.getOperationEnded());
        assertNotNull(log.getOperationStart());
        assertTrue(log.getOperationEnded().after(log.getOperationStart()));

        verify(operationlaLogDao).save(any(OperationLog.class));
    }

    @Test
    public void registerFailureLogOperation() throws Exception {
        User user = createUser();
        when(auditorUserLocator.locateLoggedUser()).thenReturn(user);

        provider = createOperationLogProvider(OperationLog.ACTION_RESULT.FAILURE, new Date());
        provider.setMessage(ERROR_MESSAGE);
        OperationLog log = registrar.register(provider);

        assertNotNull(log.getError());
        assertNotNull(log.getCreatedDate());
        assertEquals(log.getAction(), ACTION_TYPE.CREATE);
        assertEquals(log.getTitle(), OPERATION_TITLE);
        assertEquals(log.getIntention(), OPERATION_INTENTION);
        assertNotNull(log.getOperationEnded());
        assertNotNull(log.getOperationStart());
        assertTrue(log.getOperationEnded().getTime() >= log.getOperationStart().getTime());

        verify(operationlaLogDao).save(any(OperationLog.class));
    }

    private User createUser() {
        User user = new User();
        user.setUsername(DUMMY_USERNAME);
        user.setId(DUMMY_USER_ID);
        return user;
    }

    private OperationLogProvider createOperationLogProvider(final OperationLog.ACTION_RESULT result, final Date date) {

        return new OperationLogProvider() {
            public String errorMessage;

            @Override
            public boolean isCompleted() {
                return true;
            }

            @Override
            public String getOperationIntention() {
                return OPERATION_INTENTION;
            }

            @Override
            public String getOperationTitle() {
                return OPERATION_TITLE;
            }

            @Override
            public Date getOperationStart() {
                return date;
            }

            @Override
            public String getStringResult() {
                return "Operation Result";
            }

            @Override
            public String getMessage() {
                return errorMessage;
            }

            @Override
            public OperationLog.ACTION_RESULT getActionResult() {
                return result;
            }

            @Override
            public ACTION_TYPE getActionType() {
                return ACTION_TYPE.CREATE;
            }


            @Override
            public void setMessage(String message) {
                this.errorMessage = message;
            }
        };
    }
}
