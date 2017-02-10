package ar.com.adriabe.web.controllers.validators;


import ar.com.adriabe.model.common.DomainObject;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */

public abstract class AbstractFormValidator<T> {

    public  T validateForm(T domainObject) throws KendoValidationException{
        return domainObject;
    }

}
