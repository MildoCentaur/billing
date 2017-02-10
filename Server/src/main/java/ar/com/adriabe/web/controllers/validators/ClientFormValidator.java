package ar.com.adriabe.web.controllers.validators;

import ar.com.adriabe.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mildo on 8/24/14.
 */
@Component("clientFormValidator")
public class ClientFormValidator extends AbstractFormValidator<Client>{


    @Override
    public Client validateForm(Client client) throws KendoValidationException {
        KendoValidationException errors = new KendoValidationException();

        if(!validateCuit(client.getCuit())){
            errors.addErrorMessage("El CUIT ingresado no es valido.");
        }

        if(errors.getErrorMessages().size() >0 ){
            throw errors;
        }

        return client;
    }

    protected boolean validateCuit(String cuit) {
        String regex = "[a-zA-Z]";

        cuit = cuit.replace("-", "");
        if (cuit.matches(regex)) {
            return false;
        }
        if (cuit.length() != 11) {
            return false;
        }

        double sum = 0;
        int bint = 0;
        int j = 7;
        int value = 0;
        for (int i = 5, c = 0; c != 10; i--, c++) {
            value = cuit.charAt(c) - '0';
            if (i >= 2){
                sum += (value * i);
            }else{
                bint = 1;
            }
            if (bint == 1 && j >= 2) {
                sum += (value * j);
                j--;
            }
        }


        double resto = (sum % 11);

        resto = resto == 0 ? 0 : resto == 1 ? 9 : 11 - resto;


        if (resto == (cuit.charAt(cuit.length() - 1) - '0')) {
            return true;
        }
        return false;
    }
}
