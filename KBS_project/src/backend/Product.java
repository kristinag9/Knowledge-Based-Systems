package backend;
import java.util.Objects;

// class Product represents a product which has name, calories, price and unit =>
// unit shows the information about the product in grams and per one piece
public class Product {
    private String name;      // name of product
    private Double calories;  // calories per unit
    private Double price;     // price per one product
    private Double unit;      // unit - grams or per piece

    // constructor which creates a product by given information
    public Product(String name, Double calories, Double price, Double unit) {
        setName(name);
        setUnit(unit);
        setCalories(calories);
        setPrice(price);
    }

    // constructor which creates a product by default values for the data
    public Product() {
        this("no name", 0.1, 0.1, 0.1);
    }

    // two products differ from each other by the name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // get method which returns the product name
    public String getName() {
        return name;
    }

    // set method which set the name of product
    public void setName(String name) {
        this.name = name == null ? "no name" : name;
    }

    // get method which get the calories of a product
    public Double getCalories() {
        return calories;
    }

    // method for setting the calories => they are equal to the calories * unit
    public void setCalories(Double calories) {
        this.calories = (calories == null || calories < 0) ? 0 : calories * unit;
    }

    // get method which returns the price
    public Double getPrice() {
        return price;
    }

    // set method which set the price => it is equal to price * unit else it is 0.0 by default
    public void setPrice(Double price) {
        this.price = (price == null || price <= 0) ? 0.1 : price * unit;
    }

    // get method for the unit
    public Double getUnit() {
        return unit;
    }

    // set method for the unit
    public void setUnit(Double unit) {
        this.unit = (unit == null || unit <= 0) ? 0.1 : unit;
    }

    // method toString for displaying the information about the product
    @Override
    public String toString() {
        return String.format(" Product: %s, Calories for one: %.2f kcal", name, calories / unit);
    }
} // end of class Product
