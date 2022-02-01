/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import java.util.ArrayList;
import java.util.Random;
import test3.StartScene;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alois
 */
public class Main extends Application {
    //global variables to optimize code and make it run faster
    //dont know if worth it work on it maybe
    public static Stage stage;
    
    public static final Insets insets5=new Insets(5,5,5,5);
    
    public static HBox topBtnBox;
    public static Button settingsBtn;
    public static Button helpBtn;
    
    public static VBox backBox;
    public static Button backBtn;
    
    //public static ArrayList<Position> orderOfRectsFlashed=new ArrayList<>();
    public static ArrayList<ArrayList<Position>> orders = new ArrayList<>();
    
    @Override
    public void start(Stage stage) {
        stage.setWidth(500);
        stage.setHeight(320);
        
        setOrders(10);
        StartScene.setStartScene(stage);
        Main.stage = stage;

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    //events still need to be setup in individual classes
    public static void setTopBtnBox(){
        topBtnBox = new HBox();
        
        settingsBtn = new Button();
        String settingsIconPath = "https://icons8.com/iconizer/files/Brightmix/orig/monotone_cog_settings_gear.png";
        Image settingsIcon = new Image(settingsIconPath);
        ImageView settingsIconView = new ImageView(settingsIcon);
        settingsIconView.setFitHeight(25);
        settingsIconView.setPreserveRatio(true);
        settingsBtn.setPrefSize(30, 30);
        settingsBtn.setGraphic(settingsIconView);
        
        helpBtn = new Button();
        String helpIconPath = "https://www.clipartmax.com/png/small/185-1853715_man-with-question-mark-flat-icon-pictogram-vector-image-black-question-mark.png";
        Image helpIcon = new Image(helpIconPath);
        ImageView helpIconView = new ImageView(helpIcon);
        helpIconView.setFitHeight(25);
        helpIconView.setPreserveRatio(true);
        helpBtn.setPrefSize(30, 30);
        helpBtn.setGraphic(helpIconView);
        
        topBtnBox.setSpacing(5);
        topBtnBox.setAlignment(Pos.TOP_LEFT);
        topBtnBox.getChildren().addAll(settingsBtn,helpBtn);
    }
    public static void setBackBox(){
        backBtn = new Button();
        String backIconPath = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhpWkKwgrQH4Rk4EgjWYDfurRNAgbYp6tqQQ&usqp=CAU";
        Image backIcon = new Image(backIconPath);
        ImageView backIconView = new ImageView(backIcon);
        backIconView.setFitWidth(50);
        //backIconView.setFitHeight(20);
        backIconView.setPreserveRatio(true);
        backBtn.setPrefSize(50,20);
        backBtn.setGraphic(backIconView);
        EventHandler<ActionEvent> backEvent = (ActionEvent e) -> {
            StartScene.setStartScene();
        };
        backBtn.setOnAction(backEvent);
        
        backBox = new VBox();
        backBox.setPadding(insets5);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.getChildren().addAll(backBtn);
    }
    
    
    public static void setOrders(int level){
        for (int i=1;i<=level;i++){
            orders.add(setOrder(i));
        }
    }
    public static ArrayList<Position> setOrder(int level){
        ArrayList<Position> orderOfRectsFlashed=new ArrayList<>();
        for (int i=0;i<level;i++){
            Random randRow = new Random();
            Random randCol = new Random();
            orderOfRectsFlashed.add(new Position(randRow.nextInt(3),randCol.nextInt(3)));
        }
        return orderOfRectsFlashed;
    }
    
}