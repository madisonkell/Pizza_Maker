//import statements to use libraries
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//main class
public class TestMain extends Application{
    /**
     * create all the variables that were used
     *  multiple times throughout.
     */
    //pizza variable
    Pizza pizza;

    //scene variable
    Scene scene;
    //root holds all content and title
    VBox root;
    //holds all content on the left
    VBox contentLeft;
    //holds all content on the right
    HBox allContent;
    //stack pane for the pizza
    StackPane contentRight;

    //setting all labels to the information on the left that
    //needed a label
    Label lblTitle;
    Label lblFile;
    Label lblSize;
    Label lblCrust;
    Label lblSauce;
    Label lblCheese;
    Label lblToppings;

    //radiobuttons for the selection of the sizes and crust
    RadioButton rdbSmall;
    RadioButton rdbMedium;
    RadioButton rdbLarge;
    RadioButton rdbDeepDish;
    RadioButton rdbNYThin;

    //toggle groups for the sizes, sauce, and the crust to hang in
    ToggleGroup tgSize;
    ToggleGroup tgCrust;
    ToggleGroup tgSauce;

    //toggle buttons for the sauce
    ToggleButton tbMarinara;
    ToggleButton tbBarbeque;
    ToggleButton tbAlfredo;
    ToggleButton tbChipotle;

    //image view variables that hold the selected image
    ImageView ivCrust;
    ImageView ivSauce;
    ImageView ivCheese;

    //combo box for the cheeses
    ComboBox<String> cboCheese;

    //checkboxes for all of the toppings
    CheckBox chkMushroom;
    CheckBox chkOnion;
    CheckBox chkOlive;
    CheckBox chkPepper;
    CheckBox chkTomato;
    CheckBox chkJalapeno;
    CheckBox chkHam;
    CheckBox chkChicken;
    CheckBox chkPepperoni;
    CheckBox chkSeasoning;

    //buttons for the reset and submit at the bottom
    Button btnSubmit;
    Button btnReset;

    //a main function to just launch
    public static void main(String args[]){
        launch(args);
    }

    //override to make sure this implementation is being used
    @Override
    public void start(Stage stage) throws Exception {
        //Setting the title to the stage
        stage.setTitle("Procedural Object Oriented Programming (POOP) Pizza Store");
        //title at the top of the form
        lblTitle = new Label("Build Your Own Pizza!");
        //instantiating the variables
        pizza = new Pizza();
        contentRight = new StackPane();
        lblFile = new Label();
        allContent = new HBox();
        contentLeft = new VBox();
        root = new VBox();
        ivCrust = new ImageView();

        //setting a style to the title to mess with the styling later
        lblTitle.getStyleClass().add("title");
        //setting some styling the content on both sides
        root.setAlignment(Pos.TOP_CENTER);
        contentRight.setPadding(new Insets(0,60, 0, 60));
        contentLeft.setPadding(new Insets(10));
        allContent.setSpacing(10);
        contentLeft.setAlignment(Pos.CENTER);

        //adding both halves of the content to the all content
        allContent.getChildren().addAll(contentLeft, contentRight);
        //adding all content to the root with the title
        root.getChildren().addAll(lblTitle,allContent);

        //setting the size of the window
        scene = new Scene(root,1300,850);

        //setting the scene to the stage
        stage.setScene(scene);

        //showing the stage
        stage.show();

        //running all the functions to  build the actual pizza based
        //on user selection
        findSize();
        findCrust();
        findSauce();
        findCheese();
        findToppings();
        findSeasoning();
        createButtons();
        create();

        //adding a style sheet to the program
        scene.getStylesheets().add("assets/stylesheets/styles.css");

        //when the button is clicked on call the reset function
        btnReset.setOnAction(e ->{
            reset();
        });

        //when the button is clicked, call the capture function on the right hand side
        btnSubmit.setOnAction(e -> {
            captureAndSaveDisplay(contentRight);
        });
    }

    public void findSize(){
        //instantiating the label adn giving it text
        lblSize = new Label("Choose Your Size");

        //radio buttons to correlate with the size
        rdbSmall = new RadioButton("Small");
        rdbMedium = new RadioButton("Medium");
        rdbLarge = new RadioButton("Large");

        //instantiating the toggle group and creating hbox
        tgSize = new ToggleGroup();
        HBox sizeToggles = new HBox();
        //adding a separator for niceness
        Separator sep = new Separator();

        //setting the radiobuttons to the toggle group
        rdbSmall.setToggleGroup(tgSize);
        rdbMedium.setToggleGroup(tgSize);
        rdbLarge.setToggleGroup(tgSize);

        //setting the medium toggle to true because that is the e default
        rdbMedium.setSelected(true);

        //adding some styling to the toggles
        sizeToggles.setSpacing(10);
        sizeToggles.setAlignment(Pos.CENTER);
        sizeToggles.setPadding(new Insets(20));

        //adding all of the elements to the left side
        contentLeft.getChildren().addAll(lblSize, sizeToggles, sep);
        //adding the radio buttons to the hbox
        sizeToggles.getChildren().addAll(rdbSmall, rdbMedium, rdbLarge);

        // when one is clicked, set the new value to the the new radio button
        //get the value of the text to run it into the pizza function
        tgSize.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            RadioButton sel = (RadioButton) newValue;
            String size = sel.getText();
            pizza.setSize(size);
            // create the pizza
            create();
        });
    }

    public void findCrust(){
        //instantiating the label adn giving it text
        lblCrust = new Label("Choose Your Crust");

        //radio buttons to correlate with the crust
        rdbDeepDish = new RadioButton("Chicago Style Deep Dish");
        rdbNYThin = new RadioButton("New York Thin Crust");

        //instantiating the toggle group and creating hbox
        tgCrust = new ToggleGroup();
        HBox temp = new HBox();
        //adding a separator for niceness
        Separator sep = new Separator();

        //setting the radiobuttons to the toggle group
        rdbDeepDish.setToggleGroup(tgCrust);
        rdbNYThin.setToggleGroup(tgCrust);
        //setting the deepdish toggle to true because that is the e default
        rdbDeepDish.setSelected(true);

        //adding the radio buttons to the hbox
        temp.getChildren().addAll(rdbDeepDish, rdbNYThin);
        //adding all of the elements to the left side
        contentLeft.getChildren().addAll(lblCrust,temp, sep);

        //adding some styling to the toggles
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(20));
        temp.setSpacing(10);

        // when one is clicked, set the new value to the the new radio button
        //get the value of the text to run it into the pizza function
        tgCrust.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            RadioButton sel = (RadioButton) newValue;
            String crust = sel.getText();
            pizza.strCrust = crust;
            create();
        });

    }

    public void findSauce(){
        //instantiating the label adn giving it text
        lblSauce = new Label("Choose Your Sauce");

        //toggle buttons to correlate with the sauce
        tbMarinara = new ToggleButton("Marinara");
        tbBarbeque = new ToggleButton("Barbeque");
        tbAlfredo = new ToggleButton("Alfredo");
        tbChipotle = new ToggleButton("Chipotle");

        //instantiating the toggle group and creating hbox
        tgSauce = new ToggleGroup();
        HBox temp = new HBox();
        //adding a separator for niceness
        Separator sep = new Separator();

        //setting the toggle to the toggle group
        tbMarinara.setToggleGroup(tgSauce);
        tbBarbeque.setToggleGroup(tgSauce);
        tbAlfredo.setToggleGroup(tgSauce);
        tbChipotle.setToggleGroup(tgSauce);
        //setting the marinara toggle to true because that is the default
        tbMarinara.setSelected(true);

        //adding the toggles to the hbox
        temp.getChildren().addAll(lblSauce, tbMarinara,tbBarbeque,tbAlfredo,tbChipotle);
        //adding all of the elements to the left side
        contentLeft.getChildren().addAll(temp, sep);

        //adding some styling to the toggles
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(20));
        lblSauce.setPadding(new Insets(10));

        // when one is clicked, set the new value to the the new radio button
        //get the value of the text to run it into the pizza function
        //added the no space just in case the user does not want sauce
        tgSauce.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            ToggleButton sel = (ToggleButton) newValue;
            if(newValue == null) {
                pizza.strSauce = "No Sauce";
            }
            else {
                String sauce = sel.getText();
                pizza.strSauce = sauce;
            }
            create();
        });
    }

    public void findCheese(){
        //instantiating the label adn giving it text
        lblCheese = new Label("Choose Cheese Level");

        //instantiating a combox array
        cboCheese = new ComboBox<>();
        //adding a separator for niceness
        Separator sep = new Separator();
        //creating hbox
        HBox temp = new HBox();

        //addign all of the cheese option to the combo, selecting normal as default
        cboCheese.getItems().addAll("None", "Normal", "Extra");
        cboCheese.getSelectionModel().select(1);

        //adding the items to the temp
        temp.getChildren().addAll(lblCheese, cboCheese);
        //addign the temp and the separator on the left
        contentLeft.getChildren().addAll(temp, sep);

        //adding some styling to the temp that is inside the bigger hbox
        lblCheese.setPadding(new Insets(10));
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(20));
        temp.setSpacing(20);

        //when the new value is selected set the string to the newvalue and create the pizza
        cboCheese.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            pizza.strCheese = newValue;
            create();
        });
    }

    public void findToppings(){
        //instantiating the label and giving it text
        lblToppings = new Label("Choose Your Toppings");
        //creating and instantiating a new grid to hold all the checkboxes
        GridPane checksGrid = new GridPane();

        //setting value to each checkbox
        chkMushroom = new CheckBox("mushroom");
        chkOnion = new CheckBox("onion");
        chkOlive = new CheckBox("Olive");
        chkPepper = new CheckBox("Pepper");
        chkTomato = new CheckBox("Tomato");
        chkJalapeno = new CheckBox("Jalapeno");
        chkHam = new CheckBox("Ham");
        chkChicken = new CheckBox("Chicken");
        chkPepperoni = new CheckBox("Pepperoni");

        //placing each checkbox into the grid where they go
        checksGrid.add(chkMushroom, 0, 0);
        checksGrid.add(chkOnion, 0, 1);
        checksGrid.add(chkOlive, 0, 2);
        checksGrid.add(chkPepper, 0, 3);
        checksGrid.add(chkTomato, 0, 4);
        checksGrid.add(chkJalapeno, 1, 0);
        checksGrid.add(chkHam, 1, 1);
        checksGrid.add(chkChicken, 1, 2);
        checksGrid.add(chkPepperoni, 1, 3);

        //selecting the defaults
        chkMushroom.setSelected(true);
        chkPepperoni.setSelected(true);
        chkPepper.setSelected(true);

        //adding a separator for niceness
        Separator sep = new Separator();

        //some styling to the grid and labels
        checksGrid.setAlignment(Pos.CENTER);
        checksGrid.setPadding(new Insets(5));
        lblToppings.setPadding(new Insets(10));

        //adding the elements to the left side
        contentLeft.getChildren().addAll(lblToppings, checksGrid, sep);

        /**
         * all of these do the same thing. If the checkbox that is attached to a string is
         * selected, add the item to the toppings array list. If it is unselected,
         * remove it from the array list
         */
        chkMushroom.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkMushroom.isSelected()){
                pizza.top.add("mushroom");
            } else{
                pizza.top.remove("mushroom");
            }
            create();
        });

        chkOnion.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkOnion.isSelected()){
                pizza.top.add("onion");
            } else{
                pizza.top.remove("onion");
            }
            create();
        });

        chkOlive.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkOlive.isSelected()){
                pizza.top.add("olive");
            } else{
                pizza.top.remove("olive");
            }
            create();
        });

        chkPepper.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkPepper.isSelected()){
                pizza.top.add("pepper");
            } else{
                pizza.top.remove("pepper");
            }
            create();
        });

        chkChicken.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkChicken.isSelected()){
                pizza.top.add("chicken");
            } else{
                pizza.top.remove("chicken");
            }
            create();
        });

        chkHam.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkHam.isSelected()){
                pizza.top.add("ham");
            } else{
                pizza.top.remove("ham");
            }
            create();
        });

        chkJalapeno.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkJalapeno.isSelected()){
                pizza.top.add("jalapeno");
            } else{
                pizza.top.remove("jalapeno");
            }
            create();
        });

        chkTomato.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkTomato.isSelected()){
                pizza.top.add("tomato");
            } else{
                pizza.top.remove("tomato");
            }
            create();
        });

        chkPepperoni.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(chkPepperoni.isSelected()){
                pizza.top.add("pepperoni");
            } else{
                pizza.top.remove("pepperoni");
            }
            create();
        });
    }

    public void findSeasoning(){
        //instantiating the label adn giving it text
        chkSeasoning = new CheckBox("Add Seasoning?");

        //setting it to true as the default
        chkSeasoning.setSelected(true);

        //adding a separator
        Separator sep = new Separator();

        //adding all elements to the left
        contentLeft.getChildren().addAll(chkSeasoning, sep);

        //setting some styling
        chkSeasoning.setPadding(new Insets(5));

        //if the checked seasoning is added, add it to the top of the stack, if unselected, remove it
        chkSeasoning.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(chkSeasoning.isSelected()){
                 pizza.top.add("seasoning");
            } else{
                pizza.top.remove("seasoning");
            }
            create();
        });
    }

    public void createButtons(){
        //creating an box to hold the buttons inside the left
        HBox temp = new HBox();

        //instantiating the buttons with text
        btnSubmit = new Button("Submit Order");
        btnReset = new Button("Reset Order");

        //setting attributes to the buttons to mess with in the stylesheet
        btnSubmit.getStyleClass().add("btnSubmit");
        btnReset.getStyleClass().add("btnReset");

        //adding all elemets to the left after adding them to the temp
        temp.getChildren().addAll(btnSubmit, btnReset);
        contentLeft.getChildren().addAll(temp, lblFile);

        //setting some style attributes
        temp.setSpacing(20);
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(5));
    }

    public String captureAndSaveDisplay(StackPane pizzaLayers){
        FileChooser fileChooser = new FileChooser();

        // Adapted from: https://stackoverflow.com/questions/38028825/javafx-
        //save-view-of-pane-to-image/38028893

        //Set extension filter
        fileChooser.getExtensionFilters().add(new
                FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        //Prompt user to select a file

        if(file != null){
            try {
                //Set the capture area
                WritableImage writableImage = new
                        WritableImage((int)pizzaLayers.getWidth(),(int)pizzaLayers.getHeight());
                pizzaLayers.snapshot(null, writableImage);
                RenderedImage renderedImage =
                        SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);

                // the only thing that i added was this to get the file path to print out
                lblFile.setText(pizza.toString() + file);

                return file.getAbsolutePath();
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return null;
    }

    public void reset(){
        //setting all boxes, buttons, and checks to the default
        cboCheese.getSelectionModel().select(1);

        rdbMedium.setSelected(true);
        rdbDeepDish.setSelected(true);
        tbMarinara.setSelected(true);

        chkMushroom.setSelected(true);
        chkOnion.setSelected(false);
        chkOlive.setSelected(false);
        chkPepper.setSelected(true);
        chkTomato.setSelected(false);
        chkJalapeno.setSelected(false);
        chkHam.setSelected(false);
        chkChicken.setSelected(false);
        chkPepperoni.setSelected(true);

        chkSeasoning.setSelected(true);
    }

    public void create(){

        //clearing all content each time this is run to rerender the stacked image
        contentRight.getChildren().clear();

        //instantiating the imageviews
        ivSauce = new ImageView();
        ivCheese = new ImageView();

        //creating a crust image with the crust path in it
        Image crust = new Image(pizza.getCrustPath());

        //looping through the selected strign to change the size of the image
        if(pizza.getSize().equals("Small")) {
            pizza.intSize = 350;
        } else if (pizza.getSize().equals("Medium")) {
            pizza.intSize = 400;
        } else {
            pizza.intSize = 450;
        }

        //setting the crust to the size the user selects
        ivCrust.setImage(crust);
        ivCrust.setFitHeight(pizza.intSize);
        ivCrust.setPreserveRatio(true);

        //if the user unselects a sauce
        if(pizza.strSauce.equals("No Sauce")){
            //do nothing
        } else {
            //create a new image and keep size
            Image sauce = new Image(pizza.getSaucePath());
            ivSauce.setImage(sauce);
            ivSauce.setFitHeight(pizza.intSize);
            ivSauce.setPreserveRatio(true);
        }

        //if the user selects none
        if(pizza.strCheese.equals("None")){
            //do nothing
        } else {
            //create a new image and keep size
            Image cheese = new Image(pizza.getCheesePath());
            ivCheese.setImage(cheese);
            ivCheese.setFitHeight(pizza.intSize);
            ivCheese.setPreserveRatio(true);
        }

        //add all the content to the stack
        contentRight.getChildren().addAll(ivCrust, ivSauce, ivCheese);
        contentRight.getChildren().addAll(pizza.getImages());
    }

}

