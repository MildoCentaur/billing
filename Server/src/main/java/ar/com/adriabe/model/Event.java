package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "appointement")
public class Event extends AuditableDomainObject implements Comparable<Event> {

    /**
     *
     */
    private static final long serialVersionUID = -1961120892589066437L;

    private String title;
    private String detail;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private String place;
    private int alert;

    public void setId(Long id) {
        super.setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("kk");
        return Integer.parseInt(sdf.format(date));
    }

    public int getMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return Integer.parseInt(sdf.format(date));
    }

    /**
     * @return the alert
     */
    public int getAlert() {
        return alert;
    }

    /**
     * @param alert the alert to set
     */
    public void setAlert(int alert) {
        this.alert = alert;
    }

    @Override
    public int compareTo(Event other) {
        return (int) (this.getId() - other.getId());
    }

    @Transient
    public String getShowTitle() {
        return title.toUpperCase().charAt(0) + title.substring(1);
    }

    @Transient
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DATE);
    }

    @Transient
    public String getTime() {
        return getHour() + ":" + getMinute();
    }
}
