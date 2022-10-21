package tr.mobileapp.Entity;

import java.sql.Date;
import java.util.ArrayList;

public class DayOfTrip {
    private int id;
    private int number;
    private Date date;
    private ArrayList<POIOfDay> poiOfDays = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<POIOfDay> getPoiOfDays() {
        return poiOfDays;
    }

    public void setPoiOfDays(ArrayList<POIOfDay> poiOfDays) {
        this.poiOfDays = poiOfDays;
    }
}
