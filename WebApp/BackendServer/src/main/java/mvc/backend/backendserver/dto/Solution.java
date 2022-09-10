package mvc.backend.backendserver.dto;

import mvc.backend.backendserver.entity.DayOfTrip;
import mvc.backend.backendserver.entity.POIOfDay;
import mvc.backend.backendserver.entity.Tour;
import mvc.backend.backendserver.repository.POIRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class Solution {

    public Date startDate;
    public Date endDate;
    public Data data;
    public ArrayList<ArrayList<Integer>> gene;

    public Solution(Data data) throws IOException {
        this.data = data;
        gene = new ArrayList<>();


    }



    public boolean isValid(ArrayList<ArrayList<Integer>> gene) {
        boolean isStartEndTimeValid = true;
        double[][] A = new double[500][data.numberOfPOI];
        for (int i = 0; i < data.numberOfPOI; i++) {
            ArrayList<Integer> trip = gene.get(i);
            for (int j = 0; j < trip.size(); j++) {
                int poi = trip.get(j);
                if (j == 0) {
                    A[j][i] = data.dailyStartTime[i] + data.POIs[poi].getDuration();
                } else {
                    A[j][i] = A[j - 1][i] + data.POIs[poi].getDuration();
                }
                if (A[j][i] >= data.POIs[poi].getCloseTime() || A[j][i] - data.POIs[poi].getDuration() <= data.POIs[poi].getOpenTime()) {
                    isStartEndTimeValid = false;
                }
            }
        }

        return isStartEndTimeValid;
    }

    public double cal_hapiness_obj() {
        double happiness = 0;
        for (ArrayList<Integer> arrayList : gene) {
            for (Integer index : arrayList) {
                happiness += data.POIs[index].getTotalRating();
            }
        }
        return happiness;
    }

    public double cal_distance_obj() {
        double distance = 0;
        for (int i = 0; i < data.dayOfTrip; i++) {
            for (int j = 0; j < this.gene.get(i).size() - 1; j++) {
                distance += data.distanceOfPOI[this.gene.get(i).get(j)][this.gene.get(i).get(j + 1)];
            }
        }
        return distance;
    }

    public double cal_number_of_destination_obj() {
        double number = 0;
        for (ArrayList<Integer> arrayList : gene) {
            number += arrayList.size();
        }
        return number;
    }

    public double cal_waiting_time_obj() {
        double waiting_time = 0;
        for (int i = 0; i < data.dayOfTrip; i++) {
            double current_time = data.dailyStartTime[i] + data.POIs[this.gene.get(i).get(0)].getDuration();
            for (int j = 1; j < this.gene.get(i).size(); j++) {
                if (current_time + data.distanceOfPOI[this.gene.get(i).get(j - 1)][this.gene.get(i).get(j)] * 90 < data.POIs[this.gene.get(i).get(j)].getOpenTime()) {
                    waiting_time += data.POIs[this.gene.get(i).get(j)].getOpenTime() - current_time + data.distanceOfPOI[this.gene.get(i).get(j - 1)][this.gene.get(i).get(j)] * 90;

                }
                current_time = Double.max(current_time + data.distanceOfPOI[this.gene.get(i).get(j - 1)][this.gene.get(i).get(j)] * 90, data.POIs[this.gene.get(i).get(j)].getOpenTime()) + data.POIs[this.gene.get(i).get(j)].getDuration();
            }
        }
        return waiting_time;
    }


    public double cal_fitness() {



        double fitness = 0;
        fitness += Math.pow((cal_distance_obj() - data.minDistance) / (data.maxDistance - data.minDistance), 2) * data.w1;
        fitness += Math.pow((data.minWaitingTime - cal_waiting_time_obj()) / (data.maxWaitingTime - data.minWaitingTime), 2) * data.w2;
        fitness += Math.pow((cal_hapiness_obj() - data.maxHappiness) / (data.maxHappiness - data.minHappiness), 2) * data.w3;
        fitness += Math.pow((cal_number_of_destination_obj() - data.maxNumberOfDestinations) / (data.minNumberOfDestinations - data.minNumberOfDestinations), 2) * data.w4;
        fitness = Math.sqrt(fitness);
        return fitness;
    }

    public Tour toTour(Data data){
        Tour tour = new Tour();
        Date currentDay = data.startDate;
        int index =1;
        ArrayList<DayOfTrip> days = new ArrayList<>();
        for (ArrayList<Integer> list:gene) {
            DayOfTrip day = new DayOfTrip();
            ArrayList<POIOfDay> pois = new ArrayList<>();
            day.setDate(currentDay);
            currentDay = new Date(currentDay.getTime() + (1000 * 60 * 60 * 24));
            day.setNumber(index);
            index++;
            int currentTime = (int) data.dailyStartTime[index-2];
            for(int i=0;i<list.size();i++){
                POIOfDay poi = new POIOfDay();
                poi.setPoi(data.POIs[list.get(i)]);
                poi.setStartTime((int) Math.max(currentTime,poi.getPoi().getOpenTime()));
                currentTime = (int) ((int) Math.max(currentTime,poi.getPoi().getOpenTime()) + poi.getPoi().getDuration());
                poi.setEndTime(currentTime);
                poi.setNumber(i+1);
                if(i!=list.size()-1){
                    currentTime+= data.distanceOfPOI[list.get(i)][list.get(i+1)] * 90;
                }
                pois.add(poi);
            }
            day.setListPOIs(pois);
            days.add(day);

        }
        tour.setNumberOfDays(data.dayOfTrip);

        tour.setListDays(days);

        return tour;

    }
}
