package mvc.backend.backendserver.service;


import mvc.backend.backendserver.entity.RatingPOI;
import mvc.backend.backendserver.repository.RatingPOIRepo;
import mvc.backend.backendserver.service.interfaces.IRatingPOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        return ratingPOIRepo.findRatingPOIByRatingId(ratingId);
    }

    /**
     * @return
     */
    @Override
    public List<RatingPOI> GetAllRatingPOI() {
        return ratingPOIRepo.findAll();
    }

    /**
     * @param poiId
     * @return
     */
    @Override
    public ArrayList<RatingPOI> GetRatingPoiByPoiId1(int poiId) {
        return ratingPOIRepo.GetRatingPoibyPoiId(poiId);
    }

    /**
     * @param poiId
     * @return
     */

}
