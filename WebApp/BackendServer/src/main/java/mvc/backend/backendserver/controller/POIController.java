package mvc.backend.backendserver.controller;

import mvc.backend.backendserver.entity.MyPOI;

import mvc.backend.backendserver.repository.POIRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/poi")
public class POIController {
    @Autowired
    private POIRepo poiRepo;
    @GetMapping("/{id}")
    public ResponseEntity<MyPOI> getPOIById(@PathVariable int id){
        try {
            Optional<MyPOI> poi = poiRepo.findById(id);
            if (!poi.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(poi.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
