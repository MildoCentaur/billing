package ar.com.adriabe.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mildo on 5/10/15.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount extends AbstractPersistable<Long> {
    private String name;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
