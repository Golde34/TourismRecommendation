package mvc.backend.backendserver.service;


import mvc.backend.backendserver.dto.RatingPOIDTO;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.MyPOI;
import mvc.backend.backendserver.entity.RatingPOI;
import mvc.backend.backendserver.repository.AccountRepo;
import mvc.backend.backendserver.repository.POIRepo;
import mvc.backend.backendserver.repository.RatingPOIRepo;
import mvc.backend.backendserver.service.interfaces.IRatingPOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingPOIService implements IRatingPOIService {

    @Autowired
     private final RatingPOIRepo ratingPOIRepo;
    @Autowired
    private final AccountRepo accountRepo;
    @Autowired
    private final POIRepo poiRepo;

    public RatingPOIService(RatingPOIRepo ratingPOIRepo, AccountRepo accountRepo, POIRepo poiRepo) {
        this.ratingPOIRepo = ratingPOIRepo;
        this.accountRepo = accountRepo;
        this.poiRepo = poiRepo;
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
     * @param ratingPOIDTO
     */
    @Override
    public String CreateRating(RatingPOIDTO ratingPOIDTO) throws ParseException {
        RatingPOI ratingPOI = new RatingPOI();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(ratingPOIDTO.getTimeCreate());
        Date sDate = new Date(parsed.getTime());
//        LocalDateTime now = LocalDateTime.now();
        ratingPOI.setTimeCreate(sDate);
        ratingPOI.setRate(ratingPOIDTO.getRate());
        ratingPOI.setComment(ratingPOIDTO.getComment());
//        ratingPOI.setTimeCreate((Date) format.parse(String.valueOf(now)));
        ratingPOI.setTitle(ratingPOIDTO.getTitle());
        Account acc = accountRepo.findAccountById(ratingPOIDTO.getAccountId());
        ratingPOI.setAccount(acc);
        MyPOI poi = poiRepo.findMyPOIByPOIId(ratingPOIDTO.getPoiID());
        ratingPOI.setPoi(poi);
        ratingPOIRepo.save(ratingPOI);
        return "Success";
    }


}
