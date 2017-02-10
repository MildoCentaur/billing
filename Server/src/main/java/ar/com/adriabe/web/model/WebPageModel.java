package ar.com.adriabe.web.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by AJMILD1 on 07/08/14.
 *
 * This object takes care of the data sent to the page.
 *      @pageTitle for the page title.
 *      @errorMessage for a single line Error Message.
 *      @errorListDetailList for a List of errors.
 *      @moduleMenu to set the active menu.
 *      @optionMenu to set the active option in the menu.
 *      @date   today's date.
 *      @user   username.
 */


public class WebPageModel {

    private String title;

    private MODULE_NAME moduleName;

    private List<String> errorListDetailList = new ArrayList<String>();

    private Date date;

    private String optionMenu;

    private String username;

    public String getTitle() {
        return title;
    }

    public void setTitle(String pageTitle) {
        this.title = pageTitle;
    }

    public void addErrorMessage(String errorMessage) {
        errorListDetailList.add(errorMessage);
    }

    public List<String> getErrorListDetailList() {
        return errorListDetailList;
    }

    public void setErrorListDetailList(List<String> errorListDetailList) {
        this.errorListDetailList = errorListDetailList;
    }

    public boolean addAllErrors(int index, Collection<? extends String> c) {
        return errorListDetailList.addAll(index, c);
    }

    public boolean addAllErrors(Collection<? extends String> c) {
        return errorListDetailList.addAll(c);
    }



    @JsonProperty("isEmptyErrorList")
    public boolean isEmptyErrorList() {
        return errorListDetailList.isEmpty();
    }

    public int sizeErrorList() {
        return errorListDetailList.size();
    }

    @JsonProperty("hasErrors")
    public boolean hasErrors() {
        return !isEmptyErrorList();
    }

    public String getErrorItem(int index) {
        return errorListDetailList.get(index);
    }

    public MODULE_NAME getModuleName() {
        return moduleName;
    }

    public void setModuleName(MODULE_NAME moduleName) {
        this.moduleName = moduleName;
    }

    @JsonIgnore
    public Date getDate() {
        if (date==null){
            date = new Date();
        }
        return date;
    }
    public String getFormatedDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(date);
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getOptionMenu() {
        return optionMenu;
    }

    public void setOptionMenu(String optionMenu) {
        this.optionMenu = optionMenu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
