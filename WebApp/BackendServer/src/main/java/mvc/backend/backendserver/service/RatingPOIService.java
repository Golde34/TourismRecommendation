package mvc.backend.backendserver.service;


import mvc.backend.backendserver.entity.RatingPOI;
import mvc.backend.backendserver.repository.RatingPOIRepo;
import mvc.backend.backendserver.service.interfaces.IRatingPOIService;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingPOIService implements IRatingPOIService {

    @Autowired
     private final RatingPOIRepo ratingPOIRepo;

    public RatingPOIService(RatingPOIRepo ratingPOIRepo) {
        this.ratingPOIRepo = ratingPOIRepo;
    }

    /**
     * @param ratingId
     * @return
     */
    @Override
    public RatingPOI GetRatingPOIByID(int ratingId) {
        return null;
    }
}
