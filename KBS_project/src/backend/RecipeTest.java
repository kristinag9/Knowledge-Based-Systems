package backend;

import java.util.ArrayList;

// testing class for the class Recipe
public class RecipeTest {
    public static void main(String[] args) {
        // create an ArrayList with some products
        ArrayList<Product> products =  new ArrayList<>(){
            {
                add(new Product("egg", null, null, 2.0));
                add(new Product("sugar", null, null, 20.0));
            }
        };
        Recipe recipe = new Recipe("Kisses", new ArrayList<>(){
            {
                add(new Product("egg", 157.0, null, 1.0));
                add(new Product("sugar", 3.99, null, 10.0));
            }
        });
        System.out.println(recipe); // display the recipe
        System.out.print("Is there enough quantity to make the recipe? ");
        // test hasEnoughQuantity
        if(recipe.hasEnoughQuantity(products)) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
        // test calculateMaxPortions
        recipe.calculateMaxPortions(products);
        System.out.printf("\nMax portions: %s", recipe.getServing());
    }
}
