package backend;

import java.util.ArrayList;

// class KnowledgeBaseTest which tests the functionality of the class KnowledgeBase
public class KnowledgeBaseTest {
    public static void main(String[] args) {
        // read the information from the files
        KnowledgeBase knowledgeBase = new KnowledgeBase("products.csv", "recipes.csv");
        System.out.println(knowledgeBase); // display all products and all recipes

        // randomly entered products from the user with which can create recipes
        ArrayList<Product> products = new ArrayList<>() {
            {
                add(new Product("pastry sheets", null, null, 800.0));
                add(new Product("egg", null, null, 8.0));
                add(new Product("white cheese", null, null, 600.0));
                add(new Product("yoghurt", null, null, 800.0));
                add(new Product("sunflower oil", null, null, 300.0));
                add(new Product("baking soda", null, null, 16.0));
                add(new Product("flour", null, null, 1000.0));
                add(new Product("yeast", null, null, 40.0));
                add(new Product("salt", null, null, 16.0));
                add(new Product("water", null, null, 600.0));
                add(new Product("garlic", null, null, 24.0));
                add(new Product("rosemary", null, null, 10.0));
                add(new Product("olive oil", null, null, 80.0));
                add(new Product("sea salt", null, null, 20.0));
                add(new Product("butter", null, null, 125.0));
                add(new Product("sugar", null, null, 60.0));
                add(new Product("vanilla", null, null, 1.0));
                add(new Product("baking powder", null, null, 5.0));
                add(new Product("cherries", null, null, 125.0));
            }
        };

        // get all recipes which contains the above products
        ArrayList<Recipe> recipes = knowledgeBase.getRecipes(products);
        //System.out.println(recipes); // display these recipes
    }
} // end of class KnowledgeBaseTest
