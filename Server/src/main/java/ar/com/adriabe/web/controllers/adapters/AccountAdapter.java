package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.web.model.json.AccountJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 2/19/15.
 */
public class AccountAdapter {

    public static List<AccountJSON> buildAccountJsonListFromAccountLinst(List<Account> list) {
        List<AccountJSON> result = new ArrayList<AccountJSON>();
        AccountJSON element;
        for (Account account : list) {
            element=new AccountJSON();
            element.setAccountabletDate(account.getDate());
            element.setBalance(account.getBalance());
            element.setConcept(account.getConcept());
            element.setCredit(account.getCredit());
            element.setDebit(account.getDebit());
            element.setId(account.getId());
            result.add(element);
        }
        return result;
    }
}
