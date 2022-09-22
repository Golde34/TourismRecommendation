package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity

@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private int id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

//    //@ManyToOne
//    @Column(name = "account_id", nullable = false)
//    private String account;

    @Column(name = "numberOfDays")
    private  int numberOfDays;

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tour", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<DayOfTrip> listDays;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
