package mvc.backend.backendserver.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import mvc.backend.backendserver.algorithms.GeneticAlgorithmsImplementer;
import mvc.backend.backendserver.dto.Data;
import mvc.backend.backendserver.dto.Solution;

import mvc.backend.backendserver.dto.TripGenerateDTO;
import mvc.backend.backendserver.entity.*;
import mvc.backend.backendserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/trip")

public class TripController {
    @Autowired
    POIRepo poiRepo;
    @Autowired
    DistanceRepo distanceRepo;

    @Autowired
    BusinessTourRepo tourRepo;
    @Autowired
    DayOfTripRepo dayOfTripRepo;
    @Autowired
    POIOfDayRepo poiOfDayRepo;

    @PostMapping("/generate")
    public Tour generateour(@RequestBody TripGenerateDTO tripGenerateDTO) throws IOException, ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("----------");
        System.out.println(tripGenerateDTO.getStartDate());
        System.out.println(tripGenerateDTO.getEndDate());
        System.out.println(tripGenerateDTO.getBudget());
        System.out.println(tripGenerateDTO.getDestination());
        System.out.println(tripGenerateDTO.getWalletId());

        java.util.Date parsed = format.parse(tripGenerateDTO.getStartDate());
        Date sDate = new Date(parsed.getTime());
        parsed = format.parse(tripGenerateDTO.getEndDate());
        Date eDate = new Date(parsed.getTime());
        ArrayList<MyPOI> listPoi = (ArrayList<MyPOI>) poiRepo.findAll();
        int numberOfPOI = listPoi.size();
        MyPOI[] POIs = new MyPOI[numberOfPOI];
        for (int i = 0; i < listPoi.size(); i++) {
            POIs[i] = listPoi.get(i);
        }
        ArrayList<Distance> listDistance = (ArrayList<Distance>) distanceRepo.findAll();
        double[][] distanceOfPOI = new double[numberOfPOI][numberOfPOI];
        for (int i = 0; i < listDistance.size(); i++) {
            distanceOfPOI[listDistance.get(i).getStartStation().getPOIId()][listDistance.get(i).getEndStation().getPOIId()] = listDistance.get(i).getDistance();
        }
        Data data = new Data(sDate, eDate, distanceOfPOI, POIs, numberOfPOI);

        GeneticAlgorithmsImplementer ga = new GeneticAlgorithmsImplementer(data);
        Solution s = ga.implementGA(data);

        Tour tour = s.toTour(data);
        //tourRepo.save(tour);
        Tour add = new Tour();
        add.setStartDate(sDate);
        add.setEndDate(eDate);
        add.setNumberOfDays(tour.getNumberOfDays());
        add.setAccount(tripGenerateDTO.getWalletId());

        int tourid = tourRepo.save(add).getId();
        tour.setId(tourid);
        tour.setStartDate(sDate);
        tour.setEndDate(eDate);
        tour.setAccount(tripGenerateDTO.getWalletId());
        for (DayOfTrip day : tour.getListDays()) {

            DayOfTrip daysAdded = new DayOfTrip();
            daysAdded.setTour(tour);
            daysAdded.setDate(day.getDate());
            daysAdded.setNumber(day.getNumber());
            int dayid = dayOfTripRepo.save(daysAdded).getId();
            day.setId(dayid);
            for (POIOfDay poi : day.getListPOIs()) {
                poi.setDayOfTrip(daysAdded);

                poiOfDayRepo.save(poi);
            }
        }


        return tour;


    }

    @GetMapping("/getByAccount/{accountId}")
    public ResponseEntity<ArrayList<Tour>> getAllTourByAccount(@PathVariable int accountId){

                ArrayList<Tour> optTour = tourRepo.getToursByAccount(accountId);

                return new ResponseEntity<>(optTour, HttpStatus.OK);

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Tour> getTourByAccount(@PathVariable int id){
        Tour optTour = tourRepo.getTourById(id);
        return new ResponseEntity<>(optTour, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTour(@PathVariable int id) {

        try {
            tourRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()

    public ResponseEntity<Tour> updateTour(@RequestBody Tour tour){
            int id = tour.getId();
            Tour oldTour = tourRepo.getTourById(id);
            int n =5;
            for (DayOfTrip day:oldTour.getListDays()) {


                day.getListPOIs().clear();
                tourRepo.save(oldTour);
            }



            for (DayOfTrip day : tour.getListDays()) {
                for (POIOfDay poi : day.getListPOIs()) {

                    POIOfDay ePoi = poiOfDayRepo.findPOIOfDayById(poi.getId());
                    ePoi.setStartTime(poi.getStartTime());
                    ePoi.setEndTime(poi.getEndTime());
                    ePoi.setNumber(poi.getNumber());
                    ePoi.setPoi(poi.getPoi());
                    ePoi.setDayOfTrip(poi.getDayOfTrip());
                    poiOfDayRepo.save(ePoi);
                }
            }

            return new ResponseEntity<>(tour, HttpStatus.OK);

    }
}
