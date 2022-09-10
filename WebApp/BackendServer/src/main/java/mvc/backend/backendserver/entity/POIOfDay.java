package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "poi_of_day")
public class POIOfDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    private MyPOI poi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore

    @JoinColumn(name = "day_id", nullable = false)
    private DayOfTrip dayOfTrip;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

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

    public MyPOI getPoi() {
        return poi;
    }

    public void setPoi(MyPOI poi) {
        this.poi = poi;
    }

    public DayOfTrip getDayOfTrip() {
        return dayOfTrip;
    }

    public void setDayOfTrip(DayOfTrip dayOfTrip) {
        this.dayOfTrip = dayOfTrip;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
