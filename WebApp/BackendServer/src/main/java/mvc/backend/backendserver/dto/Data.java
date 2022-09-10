package mvc.backend.backendserver.dto;

import mvc.backend.backendserver.entity.MyPOI;
import mvc.backend.backendserver.entity.MyPOI;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Data {


    public int numberOfPOI;
    public int dayOfTrip;
    public MyPOI[] POIs;
    public double distanceOfPOI[][];
    public double maxHappiness;
    public double minHappiness;
    public double maxNumberOfDestinations;
    public double minNumberOfDestinations;
    public double maxWaitingTime;
    public double minWaitingTime;
    public double maxDistance;
    public double minDistance;
    public double[] dailyBudget;
    public double[] dailyStartTime;
    public double[] dailyEndTime;
    public Date startDate;
    public Date endDate;
    public double w1;
    public double w2;
    public double w3;
    public double w4;

    public Data(Date _startDate, Date _endDate, double _distanceOfPOI[][], MyPOI[] _POIs,int _numberOfPOI){
        this.startDate=_startDate;
        this.endDate =_endDate;
        this.numberOfPOI = _numberOfPOI;
        this.POIs = _POIs;
        this.distanceOfPOI = _distanceOfPOI;
        long diff = endDate.getTime() - startDate.getTime();
        dayOfTrip  = (int) TimeUnit.DAYS.convert(diff/(24*3600*1000), TimeUnit.DAYS);

        dailyBudget = new double[dayOfTrip];
        dailyStartTime = new double[dayOfTrip];
        dailyEndTime = new double[dayOfTrip];
        for(int i=0;i<dayOfTrip;i++){
            dailyBudget[i] = 1000000;
            dailyStartTime[i] = 27000;
            dailyEndTime[i] = 86400;
        }

        maxNumberOfDestinations=calcMaxNumberOfDestination();
        maxDistance=calcMaxDistance();
        maxHappiness=calcMaxHappiness();
        maxWaitingTime = calcMaxWaitingTime();
        minDistance=0;
        minHappiness=0;
        minWaitingTime=0;
        minNumberOfDestinations=0;

    }
    public double calcMaxDistance(){
        double maxElement = Double.MIN_VALUE;
        for (int i = 0; i < numberOfPOI; i++) {
            for (int j = 0; j < numberOfPOI; j++) {
                if (distanceOfPOI[i][j] > maxElement) {
                    maxElement = distanceOfPOI[i][j];
                }
            }
        }
        return maxElement * (maxNumberOfDestinations - 1);
    }

    public int calcMaxNumberOfDestination(){
        double[] costArray = new double[numberOfPOI];
        for (int i = 0; i < this.numberOfPOI; i++){
            costArray[i] = this.POIs[i].getPrice() ;
        }
        Arrays.sort(costArray);
        double totalBudget = Arrays.stream(this.dailyBudget).sum();
        int countCost = 0;
        double currentBudget = 0;
        for (int i = 0; i < costArray.length; i++){
            currentBudget += costArray[i];
            if (currentBudget >= totalBudget){

                break;
            }
            countCost = i;
        }
        int countTime = 0;
        double totalTimeBudget = Arrays.stream(this.dailyEndTime).sum();
        double currentTimeBudget = 0;
        double[] durationArray = new double[numberOfPOI];
        for (int i = 0; i < this.numberOfPOI; i++){
            durationArray[i] = this.POIs[i].getDuration();
        }
        Arrays.sort(durationArray);
        for (int i = 0; i < durationArray.length; i++){
            currentTimeBudget += durationArray[i];
            if (currentTimeBudget >= totalTimeBudget){
                break;
            }
            countTime = i;
        }
        return Math.min(countCost, countTime) - 1;
    }
    public double calcMaxHappiness(){
        double maxElement = Double.MIN_VALUE;
        for (int i = 0; i < numberOfPOI; i++) {
            if (POIs[i].getTotalRating() > maxElement) {
                maxElement = POIs[i].getTotalRating();
            }
        }
        return maxElement * maxNumberOfDestinations;
    }

    public double calcMaxWaitingTime(){
        double[] startTimeArray = new double[numberOfPOI];
        for (int i = 0; i < this.numberOfPOI; i++){
            startTimeArray[i] = this.POIs[i].getOpenTime();
        }
        Arrays.sort(startTimeArray);
        double waitingTime = 0;
        for (int i = numberOfPOI - 1; i >= numberOfPOI - 1; i--){
            waitingTime += startTimeArray[i] - dailyStartTime[0];
        }
        return waitingTime;
    }

}
