package mvc.backend.backendserver.controller;

import mvc.backend.backendserver.dto.RatingPOIDTO;
import mvc.backend.backendserver.entity.RatingPOI;
import mvc.backend.backendserver.repository.RatingPOIRepo;
import mvc.backend.backendserver.service.interfaces.IRatingPOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ReviewPOI")
public class ReviewPOIController {
    @Autowired
    private IRatingPOIService ratingPOIService;
    @Autowired
    private RatingPOIRepo ratingPOIRepo;

    @GetMapping("/getall")
    public ResponseEntity<List<RatingPOI>> GetAllRatingPOI(){
        return new ResponseEntity<>(ratingPOIService.GetAllRatingPOI(), HttpStatus.OK);
    }
    @PostMapping("/get/{idName}")
    public ResponseEntity<RatingPOI> GetAllRatingPOI(@PathVariable int idName){
        return new ResponseEntity<>(ratingPOIService.GetRatingPOIByID(idName), HttpStatus.OK);
    }
    @PostMapping("/getRating1/{poiId}")
    public ResponseEntity<ArrayList<RatingPOI>> GetAllRatingPOIbyPoiID(@PathVariable int poiId){
        return new ResponseEntity<>(ratingPOIService.GetRatingPoiByPoiId1(poiId), HttpStatus.OK);
    }
    @PostMapping(value = "/Review")
    public HttpStatus CrateReview(@RequestBody RatingPOIDTO ratingPOIDTO) {
         ratingPOIService.CreateRating(ratingPOIDTO);
        return HttpStatus.OK;
    }


}
