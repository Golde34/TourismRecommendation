package mvc.backend.backendserver.service.interfaces;

import mvc.backend.backendserver.dto.RatingPOIDTO;
import mvc.backend.backendserver.entity.RatingPOI;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public interface IRatingPOIService {
    public RatingPOI GetRatingPOIByID(int ratingId);
    public List<RatingPOI> GetAllRatingPOI();
    public ArrayList<RatingPOI> GetRatingPoiByPoiId1(int poiId);
    public String CreateRating(RatingPOIDTO ratingPOIDTO);
}
