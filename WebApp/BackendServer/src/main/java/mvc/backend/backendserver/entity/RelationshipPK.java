package mvc.backend.backendserver.entity;

import java.io.Serializable;
import java.util.Objects;

public class RelationshipPK implements Serializable {

    private MyPOI startStation;
    private  MyPOI endStation;

    public RelationshipPK(MyPOI startStation, MyPOI endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public RelationshipPK() {
    }

    //Getters and setters are omitted for brevity

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        RelationshipPK pk = (RelationshipPK) o;
        return Objects.equals( startStation, pk.startStation ) &&
                Objects.equals( endStation, pk.endStation );
    }

    @Override
    public int hashCode() {
        return Objects.hash(startStation, endStation );
    }
    }