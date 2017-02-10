package ar.com.adriabe.web.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by AJMILD1 on 07/08/14.
 *
 * This object takes care of the data sent to the page.
 *      @errorMessage for a single line Error Message.
 *      @errorListDetailList for a List of errors.
 *      @date   today's date.
 *      @ajaxResponse  response returned after an ajax process.
 */


public class WebPageResponse {

    private List<String> errorListDetailList = new ArrayList<String>();

    private Date date = new Date();

    private Object ajaxResponse;


    public List<String> getErrorListDetailList() {
        return errorListDetailList;
    }

    public void setErrorListDetailList(List<String> errorListDetailList) {
        this.errorListDetailList = errorListDetailList;
    }

    public boolean addAllErrors(Collection<? extends String> c) {
        return errorListDetailList.addAll(c);
    }

    public boolean addError(String s) {
        return errorListDetailList.add(s);
    }

    @JsonProperty("isEmptyErrorList")
    public boolean isEmptyErrorList() {
        return errorListDetailList.isEmpty();
    }

    public int sizeErrorList() {
        return errorListDetailList.size();
    }

    @JsonProperty("hasErrors")
    public boolean getHasErrors() {
        return !isEmptyErrorList();
    }

    public String getErrorItem(int index) {
        return errorListDetailList.get(index);
    }

    public Date getDate() {
        if (date==null){
            date = new Date();
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getAjaxResponse() {
        return ajaxResponse;
    }

    public void setAjaxResponse(Object ajaxResponse) {
        this.ajaxResponse = ajaxResponse;
    }
}
