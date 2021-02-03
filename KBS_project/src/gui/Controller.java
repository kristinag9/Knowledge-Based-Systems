package gui;

import backend.KnowledgeBase;
import backend.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txaRecipes;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnRemoveLast;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnQuit;

    @FXML
    private ComboBox<String> cboChooseProduct;

    @FXML
    private TextArea txaProducts;

    // declare and ArrayList with products
    private ArrayList<Product> productArrayList;

    // declare an ArrayList with products and recipes
    private KnowledgeBase knowledgeBase;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        txaRecipes.setText("");

        // validate that the user has entered a quantity
        // if not then show an alert with message for error and return
        if (txtQuantity.getText() == null || txtQuantity.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid input");
            alert.setHeaderText("Please, enter a quantity!");
            alert.showAndWait();
            return;
        }
        // validate that the user has entered a valid name of a product
        // if not then show an alert with message for error and return
        if (!(cboChooseProduct.getItems().contains(cboChooseProduct.getValue()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid product");
            alert.setHeaderText("Please, choose a valid product!");
            alert.showAndWait();
            txtQuantity.setText("");
            cboChooseProduct.setValue(null);
            return;
        }

        // validate that the user cannot enter a product twice
        if (txaProducts.getText().contains(cboChooseProduct.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The product is already entered!");
            alert.setHeaderText("Please, choose another product!");
            alert.showAndWait();
            txtQuantity.setText("");
            cboChooseProduct.setValue(null);
            return;
        }

        // else, add each product in the text area
        for (Product p : productArrayList) {
            if (p.getName().equals(cboChooseProduct.getValue())) {
                txaProducts.setText(txaProducts.getText() + p.toString() + ", Entered quantity: " + txtQuantity.getText() + '\n');
            }
        }
        // finally, make the text null, so the user can enter e new product
        txtQuantity.setText("");
        cboChooseProduct.setValue(null);
    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {
        // validate that the user has entered some products
        // if not then show an alert with message for error and return
        if (txaProducts.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The product list is empty!");
            alert.setHeaderText("Please, enter at least two products!");
            alert.showAndWait();
            return;
        }
        ArrayList<Product> input = new ArrayList<>();
        String[] products = txaProducts.getText().split("[\n]"); // show each product in a new line
        for (String p : products) { // for each string in the list of products
            String[] subProducts = p.split("[,][ ]"); // remove the ',' and the space
            input.add(new Product(
                    subProducts[0].split("[:][ ]")[1], // add get the name of a product
                    null,
                    null,
                    Double.parseDouble(subProducts[subProducts.length - 1].split("[:][ ]")[1]) // and its quantity
            ));
        }
        // finally, show all recipes in the text area
        txaRecipes.setText(knowledgeBase.getRecipes(input).toString());
        if (txaRecipes.getText().equals("[]")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No recipes for these products!");
            alert.setHeaderText("Try with new products!");
            alert.showAndWait();
            return;
        }
        txaProducts.setText("");
    }

    @FXML
    void btnQuitOnAction(ActionEvent event) {
        Platform.exit(); // quit the program
    }

    @FXML
    void btnRemoveLastOnAction(ActionEvent event) {
        // validate that the user has entered at least one product in the text area for the products
        // if not then show an alert with message for error and return
        if (txaProducts.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid operation");
            alert.setHeaderText("The list of products is empty!");
            alert.showAndWait();
            return;
        }

        StringBuilder result = new StringBuilder();
        String[] tmp = txaProducts.getText().split("[\n]"); // contains all products from the text area for products
        // get the last entered product with tmp.length - 1 and remove it
        for (int i = 0; i < tmp.length - 1; i++) {
            result.append(tmp[i]).append('\n');
        }
        txaProducts.setText(result.toString());
    }

    @FXML
    void initialize() {
        assert txaRecipes != null : "fx:id=\"txaRecipes\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnRemoveLast != null : "fx:id=\"btnRemoveLast\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtQuantity != null : "fx:id=\"txtQuantity\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'sample.fxml'.";
        assert cboChooseProduct != null : "fx:id=\"cboChooseProduct\" was not injected: check your FXML file 'sample.fxml'.";
        assert txaProducts != null : "fx:id=\"txaProducts\" was not injected: check your FXML file 'sample.fxml'.";

        // read the files with products and recipes
        knowledgeBase = new KnowledgeBase("products.csv", "recipes.csv");
        productArrayList = knowledgeBase.getProducts(); // get all products from the knowledge base

        // add all names of products in the combo box so the user can choose from them
        for (Product product : productArrayList) {
            cboChooseProduct.getItems().add(product.getName());
        }

        // some additional formatting of the text areas and the combo box
        txaProducts.setWrapText(Boolean.TRUE);
        txaRecipes.setWrapText(Boolean.TRUE);
        cboChooseProduct.setEditable(Boolean.TRUE);
    }
} // end of class Controller