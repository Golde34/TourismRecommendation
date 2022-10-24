package mvc.backend.backendserver.repository;


import mvc.backend.backendserver.entity.RatingPOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingPOIRepo extends JpaRepository<RatingPOI,String>{

RatingPOI findRatingPOIByRatingId(int ratingPOIId);
}
