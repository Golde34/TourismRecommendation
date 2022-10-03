package mvc.backend.backendserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "rating_poi")
public class RatingPOI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_poi_id")
    private int ratingId;

    @Column(name = "rate")
    private int rate;

    private String comment;
    @Column(name = "time_create")
    private Date timeCreate;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    private MyPOI poi;
}
