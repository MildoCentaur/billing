package ar.com.adriabe.web.model.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 12/3/14.
 */
public class ListPriceForm {

    private List<PriceForm> list =  new ArrayList<PriceForm>();

    public List<PriceForm> getList() {
        return list;
    }

    public void setList(List<PriceForm> list) {
        this.list = list;
    }
}
