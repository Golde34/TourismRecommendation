package tr.mobileapp.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Tour {
    private int id;

    private Date startDate;

    private Date endDate;

    private int numberOfDays;

    private List<DayOfTrip> listDays = new ArrayList<>();

    public Tour(int id, Date startDate, Date endDate, int numberOfDays) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public List<DayOfTrip> getListDays() {
        return listDays;
    }

    public void setListDays(List<DayOfTrip> listDays) {
        this.listDays = listDays;
    }
}
