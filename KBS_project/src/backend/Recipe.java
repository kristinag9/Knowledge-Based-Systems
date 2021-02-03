package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

// class Recipe represents one recipe which consists of products
public class Recipe {
    private String name;        // name of recipe;
    private Integer serving;    // number of max servings which can be made from the products
    private Double price;       // price per portion
    private Double calories;    // calories per recipe
    private ArrayList<Product> products; // each recipe consists of list with products

    // constructor which creates a recipe by given: name of recipe, serving and some products
    public Recipe(String name, ArrayList<Product> products) {
        setName(name);
        setServing(1);
        setProducts(products);
        setCalories();
        setPrice();
    }

    // constructor which creates a recipe with default values
    public Recipe() {
        this(null, null);
    }

    // get method for the name of a recipe
    public String getName() {
        return name;
    }

    // set method for the name => if it is null, it receives a default value - "no name"
    public void setName(String name) {
        this.name = name == null ? "no name" : name;
    }

    // get method for the serving
    public Integer getServing() {
        return serving;
    }

    // set method for the serving
    public void setServing(Integer serving) {
        this.serving = (serving == null || serving <= 0) ? 1 : serving;
    }

    // get method for the price
    public Double getPrice() {
        return price;
    }

    // set method for the price => the price of a recipe is formed from the price of all products which are in the recipe
    public void setPrice() {
        this.price = 0.0;

        for (Product p : products) {
            this.price += p.getPrice();
        }
    }

    // get method for the calories => it is formed from the calories of all products which are in the recipe
    public Double getCalories() {
        return calories;
    }

    // set method for the calories of a recipe =>
    public void setCalories() {
        this.calories = 0.0;

        for (Product p : products) {
            this.calories += p.getCalories();
        }
    }

    // get method for the products in a recipe
    public ArrayList<Product> getProducts() {
        return products;
    }

    // set method for the products in a recipe
    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>();
        this.products.addAll(products);
    }

    // method which checks if there is enough quantity of the products, so a recipe can be made up of them
    public boolean hasEnoughQuantity(ArrayList<Product> userProducts) {
        // intersection is an ArrayList which contains only the products entered by the user which are available in the .csv file
        ArrayList<Product> intersection = (ArrayList<Product>) userProducts.stream().filter((x) -> products.contains(x)).collect(Collectors.toList());

        // if there are not enough products to make one recipe, we return false
        if (intersection.size() != this.products.size()) {
            return false;
        }

        // check if there is the minimal quantity for each product
        for (Product userProduct : intersection) {
            for (Product p : products) {
                if (p.getName().equals(userProduct.getName())) {
                    // if there is a product entered by the user which unit is smaller than the unit of the product in the file, return false
                    if (userProduct.getUnit() < p.getUnit()) {
                        return false;
                    }
                }
            }
        }
        // else, there is enough quantity to make a recipe and we return true
        return true;
    }

    // method which calculates the max portions that can be made up of the user products
    public void calculateMaxPortions(ArrayList<Product> input) {
        // this list contains the minimal quantity /unit/ of a product
        // and it shows the maximum servings that can be made up of the given products
        ArrayList<Integer> maxQuantity = new ArrayList<>();

        // for each entered product from the user list -> input, the minimal quantity can be work out as userUnit / recipeUnit
        for (Product userProduct : input) {
            for (Product recipeProduct : products) {
                if (userProduct.getName().equals(recipeProduct.getName())) {
                    Double userUnit = userProduct.getUnit();        // this is the unit which is entered by the user for a product
                    Double recipeUnit = recipeProduct.getUnit();    // this is the unit which is entered for the product in the .csv file
                    // add the minimal quantity to the ArrayList of integers
                    maxQuantity.add((int) (userUnit / recipeUnit));
                }
            }
        }
        // the number of servings is actually the minimal quantity from the ArrayList maxQuantity
        serving = Collections.min(maxQuantity);
    }

    // method toString which displays the information about the recipe
    @Override
    public String toString() {
        StringBuilder productsList = new StringBuilder();
        for (Product p : products) {
            productsList.append(p.toString());
        }

        return String.format("Recipe name: %s, Maximum portions: %d, Price per portion: %.2f lv., " +
                "Calories per portion: %.0f kcal\nProducts:\n%s\n", name, serving, price, calories, productsList);
    }
}
