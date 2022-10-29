package mvc.backend.backendserver.repository;


import mvc.backend.backendserver.entity.RatingPOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RatingPOIRepo extends JpaRepository<RatingPOI,String>{
    @Query(value = "SELECT * FROM tourismrecommendation.rating_poi t WHERE t.poi_id  = ?1",
            nativeQuery = true)
     ArrayList<RatingPOI> GetRatingPoibyPoiId(int poiId);

    RatingPOI findRatingPOIByRatingId (int ratingPOIId);

    List<RatingPOI> findAllBy();
}
