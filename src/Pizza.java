import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class Pizza {
    //creating variables to set values to them later.
    private String strSize;
    public int intSize;
    public String strCrust;
    public String strSauce;
    public String strCheese;
    public boolean isSeasoned;
    //created an arraylist for the toppings to be stored into
    public ArrayList<String> top;
    ImageView imgTopping;
    public Pizza() {
        //Setting defaults to the pizza class to call later in the
        //main when the form loads
        top = new ArrayList<>();
        this.intSize = 400;
        //the default toppings
        top.add("mushroom");
        top.add("pepperoni");
        top.add("pepper");
        top.add("seasoning");
        //default sauce
        this.strSauce = "Marinara";
        //default size
        this.strSize = "Medium";
        //default style
        this.strCrust = "Chicago Style Deep Dish";
        //default cheese
        this.strCheese = "Normal";
        //default seasoning
        this.isSeasoned = true;
    }

    //getter and setter for size to configure the size of
    //the pizza once selected
    public void setSize(String x) {
        this.strSize = x;
    }
    public String getSize() {
        return this.strSize;
    }

    //this return the file path of the cheese in which the user selects
    // and sets the string to the photo, returning the file path to show the image
    public String getCheesePath() {
        if (this.strCheese.equals("Normal")) {
            return "file:src/assets/images/cheese_normal.png";
        } else if (this.strCheese.equals("Extra")) {
            return ("file:src/assets/images/cheese_extra.png");
        } else
            return "";
    }

    //this return the file path of the crust in which the user selects
    // and sets the string to the photo, returning the file path to show the image
    public String getCrustPath() {
        if (this.strCrust.equals("Chicago Style Deep Dish")) {
            return "file:src/assets/images/crust_chicago.png";
        } else {
            return "file:src/assets/images/crust_nyc.png";
        }
    }

    //this return the file path of the sauce in which the user selects
    // and sets the string to the photo, returning the file path to show the image
    public String getSaucePath() {
        if (this.strSauce.equals("Marinara"))
            return "file:src/assets/images/sauce_marinara.png";
        else if (this.strSauce.equals("Barbeque"))
            return "file:src/assets/images/sauce_barbeque.png";
        else if (this.strSauce.equals("Alfredo"))
            return "file:src/assets/images/sauce_alfredo.png";
        else if (this.strSauce.equals("Chipotle"))
            return "file:src/assets/images/sauce_chipotle.png";
        else
            return "";
    }

    //this return the file path of the seasoning in which the user selects
    // and sets the string to the photo, returning the file path to show the image
    public String getSeasonedPath() {
        if (this.isSeasoned) {
            return "file:src/assets/images/seasoning.png";
        } else {
            return "";
        }
    }

    //this return the file path of the topping in which the user selects
    // and sets the string to the photo, returning the file path to show the image
    public String getToppingPath(String topping){
        if(topping.equals("mushroom")){
            return "file:src/assets/images/topping_mushroom.png";
        } else if(topping.equals("onion")) {
            return "file:src/assets/images/topping_onion.png";
        } else if(topping.equals("olive")) {
            return "file:src/assets/images/topping_olive.png";
        } else if(topping.equals("pepper")) {
             return "file:src/assets/images/topping_pepper.png";
        } else if(topping.equals("tomato")) {
            return "file:src/assets/images/topping_tomato.png";
        } else if(topping.equals("jalapeno")) {
            return "file:src/assets/images/topping_jalapeno.png";
        } else if(topping.equals("ham")) {
            return "file:src/assets/images/topping_ham.png";
        } else if(topping.equals("chicken")) {
            return "file:src/assets/images/topping_chicken.png";
        } else if(topping.equals("pepperoni")) {
            return  "file:src/assets/images/topping_pepperoni.png";
        }  else if(topping.equals("seasoning")) {
            return "file:src/assets/images/seasoning.png";
        }else {
            return "";
        }
    }

    //loading the paths of the toppings into an image variable
    public ImageView loadImage(String loadImg){
        Image topping = new Image(getToppingPath(loadImg));
        imgTopping = new ImageView();
        //retaining the size of the pizza
        imgTopping.setImage(topping);
        imgTopping.setFitHeight(this.intSize);
        imgTopping.setPreserveRatio(true);
        //returning the image
        return imgTopping;
    }

    //creating the array of image views that will loop through of the paths to the
    // and add them to the array list and increasing the size of the array once one is added
    public ArrayList<ImageView> getImages(){
        ArrayList <ImageView> arrImg = new ArrayList<>();
        for(int i =0; this.top.size() > i; i++){
            arrImg.add(loadImage(this.top.get(i)));
        }
        //returning the new array
        return arrImg;
    }

    //the words at the bottom of the program that show the niceness before the file path
    //I added the file path in the main because I could not figure out how to get it here without passing
    //anything
    public String toString(){
        return ("Order ready! Your pizza has been delivered to\n" );
    }

}