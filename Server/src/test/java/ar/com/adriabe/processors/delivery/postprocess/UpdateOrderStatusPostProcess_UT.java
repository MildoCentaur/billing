package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateOrderStatusPostProcess_UT extends DeliveryOrderTestSuite {
    DeliveryOrderUpdateOrderStatusPostProcess process;

    @Before
    public void setUp() throws Exception {
        //process = new DeliveryOrderUpdateOrderStatusPostProcess();
    }

    @Test
    public void testExecute() throws Exception {
        DeliveryOrderContext context = createDeliveryOrderContext();
        process.execute(context);
    }
}
