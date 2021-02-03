package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InvalidObjectException;
import java.util.ArrayList;

// class KnowledgeBase which contains all products and recipes
public class KnowledgeBase {
    private ArrayList<Product> products;
    private ArrayList<Recipe> recipes;

    // constructor which reads the products and the recipes from the files
    public KnowledgeBase(String productsFile, String recipesFile) {
        products = new ArrayList<>();
        recipes = new ArrayList<>();
        readProducts(productsFile);
        readRecipes(recipesFile);
    }

    // helper method for reading the products for each recipe from the .csv file
    private ArrayList<Product> parseProducts(String input) throws InvalidObjectException {
        ArrayList<Product> result = new ArrayList<>();     // the result will be saved in this ArrayList
        Product product = null;                            // declare a product
        String[] parsed = input.split("[)][ ]");     // remove the ')' and the ' ' between the unit and the name of product

        String tmp = parsed[parsed.length - 1];           // take the last char from the input line (which is ')')
        tmp = tmp.substring(0, tmp.length() - 1);          // remove the last char - ')' from the input
        parsed[parsed.length - 1] = tmp;                   // replace the tmp which was ')' with ''

        // for each string from parsed
        for (String s : parsed) {
            String[] nameAndQuantity = s.split("[ ][(]");  // remove the ' ' and the '('
            boolean found = false;                                // boolean flag which shows if a product name is found
            // for each product from the ArrayList of products
            for (Product p : products) {
                // nameAndQuantity[0] -> the name of a product
                if (p.getName().equals(nameAndQuantity[0])) {
                    found = true; // we found a name of a product
                    // initialize the product with the values in the .csv file for recipes using its constructor
                    // nameAndQuantity[1] -> shows the unit
                    product = new Product(nameAndQuantity[0], p.getCalories(), p.getPrice(),
                            Double.parseDouble(nameAndQuantity[1]));
                    break;
                }
            }
            // check if the product is entered correctly in the recipe file
            if (!found) {
                throw new InvalidObjectException("The product " + nameAndQuantity[0] + " was not found!\n");
            }
            // finally, add the products to the result
            result.add(product);
        }
        // and return result
        return result;
    }

    // method which reads the products from the .csv file
    private void readProducts(String filename) {
        BufferedReader openFile; // open the file for reading
        try {
            // get the file
            openFile = new BufferedReader(new FileReader(filename));
            //Read to skip the header
            openFile.readLine();

            String line = "";
            //Reading from the second line
            while ((line = openFile.readLine()) != null) {
                String[] lineData = line.split(",");

                if (lineData.length > 0) {
                    //Save the values in the training data
                    products.add(new Product(
                            lineData[0], // read the name of a product
                            Double.parseDouble(lineData[1]),  // read the calories
                            Double.parseDouble(lineData[2]),  // read the price
                            Double.parseDouble(lineData[3])  // read the unit (quantity)
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method which reads the recipes from the .csv file
    private void readRecipes(String filename) {
        BufferedReader openFile; // open the file for reading
        try {
            // get the file
            openFile = new BufferedReader(new FileReader(filename));
            //Read to skip the header
            openFile.readLine();

            String line = "";
            //Reading from the second line
            while ((line = openFile.readLine()) != null) {
                String[] lineData = line.split(",");

                if (lineData.length > 0) {
                    recipes.add(new Recipe(
                            lineData[0], // read the name of a recipe
                            parseProducts(lineData[1]) // read the list of products
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method which returns an ArrayList of recipes that can be made up of the given ArrayList with products
    public ArrayList<Recipe> getRecipes(ArrayList<Product> input) {
        ArrayList<Recipe> result = new ArrayList<>();
        // for each product from the ones which the user enter
        // search for it in the list of all products and set the calories and the price for it
        for (Product p : products) {
            for (Product product : input) {
                if (p.getName().equals(product.getName())) {
                    product.setCalories(p.getCalories());
                    product.setPrice(p.getPrice());
                }
            }
        }
        // for each recipe in the list of all recipes
        // if there is enough quantity of the user products, calculate the max portions for the recipe
        // that can be made of these products and add it to the result
        for (Recipe r : recipes) {
            if (r.hasEnoughQuantity(input)) {
                r.calculateMaxPortions(input);
                result.add(r);
            }
        }
        // finally, return all recipes which are in the result
        return result;
    }

    // method which get all products
    public ArrayList<Product> getProducts() {
        return products;
    }

    // method toString which displays an information about the products and the recipes
    @Override
    public String toString() {
        StringBuilder productsList = new StringBuilder();
        for (Product p : products) {
            productsList.append(p.toString());
        }
        StringBuilder recipesList = new StringBuilder();
        for (Recipe r : recipes) {
            recipesList.append(r.toString());
        }
        return String.format("Products: %s\nRecipes: %s\n", products, recipes);
    }
} // end of class KnowledgeBase
