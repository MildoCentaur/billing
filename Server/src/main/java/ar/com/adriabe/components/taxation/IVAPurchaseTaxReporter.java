package ar.com.adriabe.components.taxation;

import ar.com.adriabe.model.PurchaseTaxation;
import ar.com.adriabe.repositories.IVARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IVAPurchaseTaxReporter implements TaxReporter<PurchaseTaxation,IVATaxContext>{

    private IVARepository ivaRepository;

    @Autowired
    public IVAPurchaseTaxReporter(IVARepository ivaRepository) {
        this.ivaRepository = ivaRepository;
    }


    @Override
    public List<PurchaseTaxation> report(IVATaxContext context) {
        return ivaRepository.findPurchaseTaxationInPeriod(context.getStartPeriod(),context.getEndPeriod());
    }
}
