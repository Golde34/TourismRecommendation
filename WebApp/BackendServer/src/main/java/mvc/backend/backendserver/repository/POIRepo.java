package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.MyPOI;
import mvc.backend.backendserver.entity.RatingPOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface POIRepo extends JpaRepository<MyPOI, Integer> {
    public MyPOI findMyPOIByPOIId(int POIId);

}
