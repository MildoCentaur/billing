package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.generic.FailFastProcessor;
import ar.com.adriabe.processors.delivery.printprocess.PrintBillPostProcess;
import ar.com.adriabe.processors.delivery.printprocess.PrintTicketPostProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderPrintProcessor extends FailFastProcessor<DeliveryOrderContext> {

    @Autowired
    public void setPrintTicketPostProcess(PrintTicketPostProcess printTicketPostProcess) {
        this.executables.add(printTicketPostProcess);
    }

    @Autowired
    public void setPrintBillPostProcess(PrintBillPostProcess printBillPostProcess) {
        this.executables.add(printBillPostProcess);
    }
}
