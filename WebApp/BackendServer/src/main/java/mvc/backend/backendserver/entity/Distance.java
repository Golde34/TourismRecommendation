package mvc.backend.backendserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "distance")
@IdClass(RelationshipPK.class)
public class Distance implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "start_station", nullable = false)
    private MyPOI startStation;

    @Id
    @ManyToOne
    @JoinColumn(name = "end_station", nullable = false)
    private MyPOI endStation;

    @Column(name = "distance")
    private double distance;

    public MyPOI getStartStation() {
        return startStation;
    }

    public void setStartStation(MyPOI startStation) {
        this.startStation = startStation;
    }

    public MyPOI getEndStation() {
        return endStation;
    }

    public void setEndStation(MyPOI endStation) {
        this.endStation = endStation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
