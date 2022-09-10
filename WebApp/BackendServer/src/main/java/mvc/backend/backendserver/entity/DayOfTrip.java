package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "day_of_trip")
public class DayOfTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private int id;

    @Column(name="number")
    private int number;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)    @JsonIgnore

    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dayOfTrip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})

    private List<POIOfDay> listPOIs;

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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<POIOfDay> getListPOIs() {
        return listPOIs;
    }

    public void setListPOIs(List<POIOfDay> listPOIs) {
        this.listPOIs = listPOIs;
    }


}
