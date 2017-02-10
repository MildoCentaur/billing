package ar.com.adriabe.web.controllers.validators;

import ar.com.adriabe.model.Supplier;
import org.springframework.stereotype.Component;

/**
 * Created by Mildo on 8/25/14.
 */
@Component("supplierFormValidator")
public class SupplierFormValidator extends AbstractFormValidator<Supplier>{

    @Override
    public Supplier validateForm(Supplier supplier) throws KendoValidationException {
        return supplier;
    }
}
