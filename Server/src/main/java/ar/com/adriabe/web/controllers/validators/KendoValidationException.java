package ar.com.adriabe.web.controllers.validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */
public class KendoValidationException extends Exception {
    private List<String> errorMessages;

    public KendoValidationException(){
        super();
        errorMessages = new ArrayList<String>();
    }

    public KendoValidationException(List<String> errorMessages ){
        super();
        this.errorMessages = errorMessages ;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String error){
        this.errorMessages.add(error);
    }
}
