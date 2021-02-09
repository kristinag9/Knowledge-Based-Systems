package knnalgorithm;

// working in the two-dimensional space => we need two coordinates
// to determine the placement of the individual
public class IndividualPoint {
    private Integer x;              // determine the point according to the abscissa
    private Integer y;              // determine the point according to the ordinate
    private Boolean classification; // determine if the individual is classified

    // constructor to create a point by given coordinates
    public IndividualPoint(Integer x, Integer y, Boolean classification) {
        setX(x);
        setY(y);
        setClassification(classification);
    }

    // getters fo the point coordinates
    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Boolean getClassification() {
        return classification;
    }

    // setters for the coordinates and the classification
    public void setX(Integer x) {
        // by default the x coordinate accepts value = 1000, because it is the is the approximate average of all values
        this.x = x == null ? 1000 : x;
    }

    public void setY(Integer y) {
        // by default the y coordinate accepts value = 3, because it is the most common value
        this.y = y == null ? 3 : y;
    }

    public void setClassification(Boolean classification) {
        // accept that by default the classification is true
        this.classification = classification == null ? true : classification;
    }

    // displaying the result using method toString
    @Override
    public String toString() {
        return String.format("(Amount_payed: %d, Frequency: %d, Classification: %b)\n", x, y, classification);
    }
}
