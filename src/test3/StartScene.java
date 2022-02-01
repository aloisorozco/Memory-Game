/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import test3.SettingsScene;
import test3.GameScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static test3.GameScene.theme;
import static test3.Main.insets5;

/**
 *
 * @author alois
 */
public class StartScene {
    public static Stage stage;
    private static Scene scene;
    private static Text title;
    private static VBox outsideBox;
    
    public static MenuItem settingsBtn;
    public static MenuItem helpBtn;
    public static VBox topBtnBox;
    
    private static Button startBtn;
    
    public static void setStartScene(Stage stage){

        topBtnBox = new VBox();
        MenuBar menuBar = new MenuBar();
        
        Menu gameMenu = new Menu("Game");
        settingsBtn = new MenuItem("Settings");
        gameMenu.getItems().add(settingsBtn);
        
        EventHandler<ActionEvent> settingsEvent = (ActionEvent e) -> {
            SettingsScene.setSettingsScene(stage);
        };
        settingsBtn.setOnAction(settingsEvent);
        
        Menu helpMenu = new Menu("Help");
        helpBtn = new MenuItem("Help");
        helpMenu.getItems().add(helpBtn);
        
        EventHandler<ActionEvent> helpEvent = (ActionEvent e) -> {
            HelpScene.setHelpScene();
        };
        helpBtn.setOnAction(helpEvent);
        
        menuBar.getMenus().addAll(gameMenu, helpMenu);
        topBtnBox.setSpacing(5);
        topBtnBox.setAlignment(Pos.TOP_LEFT);
        topBtnBox.getChildren().add(menuBar);
        
        VBox midBox = new VBox();
        
        title = new Text("Memory Game");
        title.setId("title");
        title.setFont(Font.font("Arial", 30));
        title.setStyle("#title {-fx-fill: gray; }");
        startBtn = new Button("Start");
        startBtn.setPrefSize(50, 30);
        EventHandler<ActionEvent> startEvent = (ActionEvent e) -> {
            
            GameScene.setGameScene();
        };
        startBtn.setOnAction(startEvent);
        
        midBox.setSpacing(30);
        midBox.setPadding(new Insets(30,10,10,10));
        midBox.setAlignment(Pos.CENTER);
        midBox.getChildren().addAll(title,startBtn);
        
        outsideBox = new VBox();
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE,  
                                          CornerRadii.EMPTY, Insets.EMPTY); 
        Background background = new Background(backgroundFill); 
        outsideBox.setBackground(background);
        if (theme=="BLACK"){
            
            outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,  
                                          CornerRadii.EMPTY, Insets.EMPTY)));
            
        }
        outsideBox.setSpacing(30);
        outsideBox.setAlignment(Pos.TOP_CENTER);
        outsideBox.getChildren().addAll(topBtnBox,midBox);
        
        scene = new Scene(outsideBox);
        
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();
        StartScene.stage = stage;
    }
    
    public static void setStartScene(){
        stage.setScene(scene);
    }
    
    public static void setFont(String font){
        title.setFont(Font.font(font,30));
    }
    public static void setTheme(String theme){
        if (theme=="BLACK"){
            
            outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,  
                                          CornerRadii.EMPTY, Insets.EMPTY)));
            
        }
        else if(theme=="WHITE"){
            outsideBox.setBackground(new Background(new BackgroundFill(Color.WHITE,  
                                          CornerRadii.EMPTY, Insets.EMPTY)));
            

        }
        
        
    }
    
}
