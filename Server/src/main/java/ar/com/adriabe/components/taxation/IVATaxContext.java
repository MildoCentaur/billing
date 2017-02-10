package ar.com.adriabe.components.taxation;

import java.util.Date;

/**
 * Created by AJMILD1 on 19/10/2015.
 */
public class IVATaxContext {

    private Date startPeriod;
    private Date endPeriod;

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }
}
