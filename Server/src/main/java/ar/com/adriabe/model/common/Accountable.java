package ar.com.adriabe.model.common;

import javax.persistence.Transient;

/**
 * Created by AJMILD1 on 11/06/14.
 */
public interface Accountable {

    @Transient
    public Double getAccountableBalance();
    @Transient
    public Double getAccountableActivityBalance();

}
