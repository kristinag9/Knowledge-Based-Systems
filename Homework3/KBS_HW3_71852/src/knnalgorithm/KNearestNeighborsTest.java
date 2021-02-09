package knnalgorithm;

// this class tests the algorithm
public class KNearestNeighborsTest {
    public static void main(String[] args) {
        // create an object of type KNearestNeighbors with k = 3
        KNearestNeighbors knn = new KNearestNeighbors(3);

        // read the .csv file
        knn.fit("training_data.csv");
        System.out.println(knn);

        // testing examples which are not in the table with training data
        System.out.println(knn.predict(650, "Rarely")); // 3 nearest neighbors: 680, 691, 703 -> all of them are 0 => false
        System.out.println(knn.predict(740, "Often")); //  3 nearest neighbors: 703, 708, 725 -> all are 0 => false
        System.out.println(knn.predict(890, "Sometimes")); // 3 nearest neighbors: 925, 952, 959 -> common is 0 => false
        System.out.println(knn.predict(1600, "Rarely")); // 3 nearest neighbors: 1562, 1569, 1569 -> all are 1 => true
        System.out.println(knn.predict(1700, "Sometimes")); // 3 nearest neighbors: 1562, 1569, 1569 -> all are 1 => true
        System.out.println(knn.predict(500, "Often")); // 3 nearest neighbors: 600, 602, 604 -> all are 0 => false
        System.out.println(knn.predict(724, "Very often")); // 3 nearest neighbors: 703, 308, 725 -> all are 0 => false
        System.out.println(knn.predict(1218, "Rarely")); // 3 nearest neighbors: 1213, 1213, 1215 -> common is 0 => false
        System.out.println(knn.predict(1080, "Rarely")); // 3 nearest neighbors: 982, 1071, 1076 -> common is 0 => false
        System.out.println(knn.predict(1238, "Rarely")); // 3 nearest neighbors: 1256, 1256, 1256 -> all are 1 => true
        System.out.println(knn.predict(1360, "Sometimes")); // 3 nearest neighbors: 1315, 1377, 1404 -> all are 1 => true
        System.out.println(knn.predict(1570, "Very often")); // 3 nearest neighbors: 1562, 1569, 1569 -> all are 1 => true
    }
}
