package knnalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

// this class represents the k-NN algorithm using the class IndividualPoint which is already created
public class KNearestNeighbors {
    private Integer k; // k represents the number of nearest neighbours that we need to make the classification
    private ArrayList<IndividualPoint> trainingData; // create an ArrayList with the training examples

    // constructor
    public KNearestNeighbors(Integer k) {
        setK(k);
        trainingData = new ArrayList<>();
    }

    // setter fo the coefficient
    public void setK(Integer k) {
        this.k = k == null || k < 1 ? 3 : k; // if k is null the default value is 3
    }

    // create a method which transforms the Frequency from the table with given examples in Integer
    private Integer enumerate(String frequency) {
        switch (frequency.strip()) {
            case "Rarely": {
                return 0;
            }
            case "Sometimes": {
                return 1;
            }
            case "Often": {
                return 2;
            }
            case "Very often": {
                return 3;
            }
            default: {
                return null;
            }
        }
    }

    // method which calculate the euclidean distance between two individual points
    private Double euclideanDistance(IndividualPoint firstIndividual, IndividualPoint secondIndividual) {
        return Math.sqrt(Math.pow((firstIndividual.getX() - secondIndividual.getX()), 2)
                + Math.pow((firstIndividual.getY() - secondIndividual.getY()), 2));
    }

    // this method fills the training data and locate them in the space (reading the .csv file)
    public void fit(String filename) {
        BufferedReader openFile; // open the file for reading
        try {
            // get the file
            openFile = new BufferedReader(new FileReader(filename));
            //Read to skip the header
            openFile.readLine();

            String line = "";
            //Reading from the second line
            while ((line = openFile.readLine()) != null) {
                String[] lineData = line.split(";");

                if (lineData.length > 0) {
                    //Save the values in the training data
                    trainingData.add(new IndividualPoint(
                                        Integer.parseInt(lineData[0]),
                                        enumerate(lineData[1]),
                            Integer.parseInt(lineData[2]) != 0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method make the classification of the testing example
    public Boolean predict(Integer amountPayed, String frequency) {
        IndividualPoint testingExample = new IndividualPoint(amountPayed, enumerate(frequency), false);

        // collect all euclidean distances of all classified individuals in a priority queue,
        // so then we can choose only the best k of them
        PriorityQueue<IndividualPoint> allDistances = new PriorityQueue<>((p1, p2) -> {
            Double eucDist1 = euclideanDistance(testingExample, p1); // find one euclidean distance
            Double eucDist2 = euclideanDistance(testingExample, p2); // find second euclidean distance

            // compare both of the distances
            if (eucDist1 < eucDist2) {
                return -1; // if the first is smaller than the second
            } else if (eucDist1.equals(eucDist2)) {
                return 0; // return 0 if they are equal
            }
            return 1; // if the second is smaller than the first distance
        });

        // collect the sorted distances in the queue
        allDistances.addAll(trainingData);

        // make an ArrayList of the first k classified individuals
        ArrayList<Boolean> classifications = new ArrayList<>();

        // iterate from 0 to k and add the classifications of the polled individual
        for (int i = 0; i < k; i++) {
            classifications.add(Objects.requireNonNull(allDistances.poll()).getClassification());
        }

        // finally, compare the classifications - true and false,  and return true if it predominates
        return classifications.stream().filter((x) -> x).count() > classifications.stream().filter((x) -> !x).count();
    }

    // displaying the results using the method toString
    @Override
    public String toString() {
        return String.format("Displaying results for k = %d nearest neighbors\nTraining data:\n%s", k, trainingData);
    }
}
