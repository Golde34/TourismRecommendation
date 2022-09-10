package mvc.backend.backendserver.algorithms;

import mvc.backend.backendserver.dto.Data;
import mvc.backend.backendserver.dto.Solution;

import java.io.IOException;
import java.util.*;

public class GeneticAlgorithmsImplementer {
    public Data data;

    public GeneticAlgorithmsImplementer(Data data) {
        this.data = data;
    }
    static int getNum(ArrayList<Integer> v) {
        // Size of the vector
        int n = v.size();

        // Make sure the number is within
        // the index range
        int index = (int) (Math.random() * n);

        // Get random number from the vector
        int num = v.get(index);

        // Remove the number from the vector
        v.set(index, v.get(n - 1));
        v.remove(n - 1);

        // Return the removed number
        return num;
    }

    // Function to generate n
    // non-repeating random numbers
    static ArrayList<Integer> generateRandom(int n) {
        ArrayList<Integer> v = new ArrayList<>(n);
        ArrayList<Integer> vc = new ArrayList<>(n);
        // Fill the vector with the values
        // 1, 2, 3, ..., n
        for (int i = 1; i < n; i++) {
            v.add(i);
        }
        for (int i = 1; i < n; i++) {
            vc.add(getNum(v));
        }
        // While vector has elements
        // get a random number from the vector and print it

        return vc;
    }

    // Driver code
    public Solution generatePopulation(Data data) throws IOException {
        ArrayList<Solution> generation = new ArrayList<>();
        ArrayList<Integer> fullTrip = generateRandom(data.numberOfPOI);
        Solution s = new Solution(data);
        for (int i = 0; i < data.dayOfTrip; i++) {
            ArrayList<Integer> dayTrip = new ArrayList<>();
            double time = Double.max(data.dailyStartTime[i], data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
            double cost = data.POIs[fullTrip.get(0)].getPrice();
            dayTrip.add(fullTrip.get(0));
            int current = fullTrip.get(0);
            fullTrip.remove(0);

            while (true) {
                double predict = Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                if (Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration() < data.dailyEndTime[i]
                        && cost + data.POIs[fullTrip.get(0)].getPrice() < data.dailyBudget[i]) {
                    time = Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                    cost += data.POIs[fullTrip.get(0)].getPrice();
                    current = fullTrip.get(0);
                    dayTrip.add(fullTrip.get(0));
                    fullTrip.remove(0);
                    continue;
                } else {
                    break;
                }
            }
            s.gene.add(dayTrip);
        }
        return s;

    }

    public Solution crossover(Solution parent1, Solution parent2, Data data) throws IOException {
        Solution child = new Solution(data);
        Set<Integer> set = new HashSet<Integer>();
        for (ArrayList<Integer> list : parent1.gene) {
            for (Integer i : list) {
                set.add(i);
            }

        }
        for (ArrayList<Integer> list : parent2.gene) {
            for (Integer i : list) {
                set.add(i);
            }

        }
        set = newShuffledSet(set);

        ArrayList<Integer> fullTrip = new ArrayList<>(set);

        for (int i = 0; i < data.dayOfTrip; i++) {
            ArrayList<Integer> dayTrip = new ArrayList<>();
            double time = Double.max(data.dailyStartTime[i], data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
            double cost = data.POIs[fullTrip.get(0)].getPrice();
            dayTrip.add(fullTrip.get(0));
            int current = fullTrip.get(0);
            fullTrip.remove(0);

            while (fullTrip.size() > 0) {
                //double predict = Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                if (Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration() < data.dailyEndTime[i]
                        && cost + data.POIs[fullTrip.get(0)].getPrice() < data.dailyBudget[i]) {
                    time = Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                    cost += data.POIs[fullTrip.get(0)].getPrice();
                    current = fullTrip.get(0);
                    dayTrip.add(fullTrip.get(0));
                    fullTrip.remove(0);
                    continue;
                } else {
                    break;
                }
            }
            child.gene.add(dayTrip);
        }
        return child;
    }

    public Solution mutation(Solution s, Data data) throws IOException {
        ArrayList<Integer> poiList = new ArrayList<>();
        for (int i = 0; i < data.numberOfPOI; i++) {
            poiList.add(i);
        }

        // random trip number and cutoff point
        Random rand = new Random();
        int tripNumber = rand.nextInt(data.dayOfTrip);
        int cutoffPoint = rand.nextInt(s.gene.get(tripNumber).size());
        //  System.out.println(tripNumber + "|" + cutoffPoint);
        Solution newS = new Solution(data);
        for (int i = 0; i < tripNumber; i++) {
            if (tripNumber != 0) {
                for (Integer poi : s.gene.get(i)) {
                    poiList.remove(poiList.indexOf(poi));
                }
                newS.gene.add(s.gene.get(i));
            }
        }
        ArrayList<Integer> newTrip = new ArrayList<>();
        double time = Double.max(data.dailyStartTime[tripNumber], data.POIs[s.gene.get(tripNumber).get(0)].getOpenTime()) + data.POIs[s.gene.get(tripNumber).get(0)].getDuration();;
        double cost = data.POIs[s.gene.get(tripNumber).get(0)].getPrice();
        for (int i = 0; i < cutoffPoint; i++) {
            int currentPOI = s.gene.get(tripNumber).get(i);
            newTrip.add(currentPOI);
            poiList.remove(poiList.indexOf(currentPOI));
            time = Double.max(time + data.distanceOfPOI[currentPOI][s.gene.get(tripNumber).get(i + 1)] * 90, data.POIs[s.gene.get(tripNumber).get(i + 1)].getOpenTime()) + data.POIs[s.gene.get(tripNumber).get(i + 1)].getDuration();
            cost += data.POIs[s.gene.get(tripNumber).get(i)].getPrice();
        }

        int current = s.gene.get(tripNumber).get(cutoffPoint);
        newTrip.add(current);
        poiList.remove(poiList.indexOf(current));

        ArrayList<Integer> fullTrip = new ArrayList<>();
        int size = poiList.size();
        for (int i = 0; i < size; i++) {
            fullTrip.add(getNum(poiList));
        }
        while (fullTrip.size() > 0) {
            //double predict = Double.max(time + data.D[current][fullTrip.get(0)] * 90, data.MyPOI[fullTrip.get(0)].getStart()) + data.MyPOI[fullTrip.get(0)].getDuration();
            if (Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration() < data.dailyEndTime[tripNumber]
                    && cost + data.POIs[fullTrip.get(0)].getPrice() < data.dailyBudget[tripNumber]) {
                time = Double.max(time + data.distanceOfPOI[current][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                cost += data.POIs[fullTrip.get(0)].getPrice();
                current = fullTrip.get(0);
                newTrip.add(fullTrip.get(0));
                fullTrip.remove(0);
                continue;
            } else {
                break;
            }
        }

        newS.gene.add(newTrip);

        for (int i = tripNumber + 1; i < data.dayOfTrip; i++) {
            ArrayList<Integer> dayTrip = new ArrayList<>();
            double newTime = Double.max(data.dailyStartTime[i], data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
            double newCost = data.POIs[fullTrip.get(0)].getPrice();
            dayTrip.add(fullTrip.get(0));
            int currentPOI = fullTrip.get(0);
            fullTrip.remove(0);
            while (fullTrip.size() > 0) {
                //double predict = Double.max(newTime + data.D[currentPOI][fullTrip.get(0)] * 90, data.MyPOI[fullTrip.get(0)].getStart()) + data.MyPOI[fullTrip.get(0)].getDuration();
                if (Double.max(newTime + data.distanceOfPOI[currentPOI][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration() < data.dailyEndTime[i]
                        && newCost + data.POIs[fullTrip.get(0)].getPrice() < data.dailyBudget[i]) {
                    newTime = Double.max(newTime + data.distanceOfPOI[currentPOI][fullTrip.get(0)] * 90, data.POIs[fullTrip.get(0)].getOpenTime()) + data.POIs[fullTrip.get(0)].getDuration();
                    newCost += data.POIs[fullTrip.get(0)].getPrice();
                    currentPOI = fullTrip.get(0);
                    dayTrip.add(fullTrip.get(0));
                    fullTrip.remove(0);
                    continue;
                } else {
                    break;
                }
            }
            newS.gene.add(dayTrip);
        }
        return newS;
    }

    public Solution mutation2(Solution s, Data data) throws IOException {
        Solution newS = new Solution(data);

        return generatePopulation(data);
    }

    public Solution implementGA(Data data) throws IOException {
        ArrayList<Solution> results = new ArrayList<>();
        ArrayList<Solution> population = new ArrayList<>();
        //Generation
        for (int i = 0; i < 500; i++) {
            population.add(generatePopulation(data));
        }
        Collections.sort(population, new Comparator<Solution>() {
            @Override
            public int compare(Solution o1, Solution o2) {

                return Double.compare(o1.cal_fitness(), o2.cal_fitness());
            }
        });
        for (int j = 0; j < 100; j++) {
            //Selection

            ArrayList<Solution> nextPopulation = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                nextPopulation.add(population.get(i));
            }
            //Crossover
            for (int i = 0; i < 450; i++) {
                Random rand = new Random();
                int mom = rand.nextInt(500);
                int dad = rand.nextInt(500);
                while (mom == dad) {
                    dad = rand.nextInt(500);
                }
                //nextPopulation.add(crossover(population.get(dad), population.get(mom), data));
                nextPopulation.add(mutation(population.get(mom), data));
            }
            Collections.sort(nextPopulation, new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {

                    return Double.compare(o1.cal_fitness(), o2.cal_fitness());
                }
            });

            //mutation
            for (int i = 0; i < 50; i++) {
                Random rand = new Random();
                int choosen = rand.nextInt(450);
                choosen += 25;
                nextPopulation.set(choosen, mutation2(nextPopulation.get(choosen), data));
            }
            Collections.sort(nextPopulation, new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {

                    return Double.compare(o1.cal_fitness(), o2.cal_fitness());
                }
            });
            results.add(nextPopulation.get(0));
            population.clear();
            population = new ArrayList<>(nextPopulation);
        }

        return results.get(results.size()-1);
    }

    public static <T> Set<T> newShuffledSet(Collection<T> collection) {
        List<T> shuffleMe = new ArrayList<T>(collection);
        Collections.shuffle(shuffleMe);
        return new HashSet<T>(shuffleMe);
    }

}
