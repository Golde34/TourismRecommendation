package mvc.backend.backendserver.service.interfaces;

import mvc.backend.backendserver.entity.RatingPOI;

import java.util.ArrayList;
import java.util.List;

public interface IRatingPOIService {
    public RatingPOI GetRatingPOIByID(int ratingId);
    public List<RatingPOI> GetAllRatingPOI();
    public ArrayList<RatingPOI> GetRatingPoiByPoiId1(int poiId);
}
