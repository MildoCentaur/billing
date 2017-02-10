package ar.com.adriabe.web.controllers.adapters;

import java.util.ArrayList;
import java.util.List;

public class KendoAdapterException extends Exception {
    private List<String> errorMessages;

    public KendoAdapterException(){
        super();
        errorMessages = new ArrayList<String>();
    }
    public KendoAdapterException(String errorMessage ){
        super();
        this.errorMessages = new ArrayList<String>();
        this.addErrorMessage(errorMessage);
    }

    public KendoAdapterException(List<String> errorMessages ){
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
