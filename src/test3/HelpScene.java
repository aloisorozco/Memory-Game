/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static test3.GameScene.theme;
import static test3.Main.insets5;

import static test3.StartScene.stage;
import test3.StartScene;
/**
 *
 * @author alois
 */
public class HelpScene {
    private static boolean interfaceSet=false;
    private static Scene scene;
    private static VBox outsideBox;
    private static Text title;
    private static Button backBtn;
    private static String font="Arial";
    private static Label lb;

    public static void setHelpScene(){
        if (!interfaceSet){
            setInterface();
        }
        setBackBtn();
        stage.setScene(scene);
    }
    public static void setHelpScene(int level) {
        if (!interfaceSet){
            setInterface();
        }
        setBackBtn(level);
        stage.setScene(scene);
    }

    private static void setInterface() {
        interfaceSet=true;
        backBtn = new Button();
        String backIconPath = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhpWkKwgrQH4Rk4EgjWYDfurRNAgbYp6tqQQ&usqp=CAU";
        Image backIcon = new Image(backIconPath);
        ImageView backIconView = new ImageView(backIcon);
        backIconView.setFitWidth(50);
        backIconView.setPreserveRatio(true);
        backBtn.setPrefSize(50,20);
        backBtn.setGraphic(backIconView);
        EventHandler<ActionEvent> backEvent = (ActionEvent e) -> {
            StartScene.setStartScene();
        };
        backBtn.setOnAction(backEvent);

        VBox backBox = new VBox();
        backBox.setPadding(insets5);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.getChildren().addAll(backBtn);

        VBox titleBox = new VBox();
        title=new Text("Help");
        title.setFont(Font.font(font,20));
        titleBox.setPadding(insets5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i<3;i++){
            for (int j=0; j<3;j++){
                if (i==1 & j==2){
                    Rectangle r = new Rectangle(0,0,30,30);
                    r.setFill(Color.GREEN);
                    r.setStroke(Color.TRANSPARENT);
                    Rectangle r2 = new Rectangle(0,0,30,30);
                    r2.setFill(Color.GREEN);
                    r2.setStroke(Color.TRANSPARENT);
                    gridPane.add(r, j, i, 1, 1);
                    gridPane2.add(r2, j, i, 1, 1);
                }
                else{
                    gridPane.add(new Group(new Rectangle(0,0,30,30)), j, i, 1, 1);
                    gridPane2.add(new Group(new Rectangle(0,0,30,30)), j, i, 1, 1);
                }
            }
        }
        HBox gridPaneBox = new HBox(10);
        gridPaneBox.setAlignment(Pos.CENTER);
        gridPaneBox.getChildren().addAll(gridPane,gridPane2);

        VBox midBox = new VBox();
        midBox.setSpacing(5);
        midBox.setAlignment(Pos.CENTER);
        midBox.setPadding(new Insets(10,10,10,10));
        lb = new Label("Replicate the pattern from the left grid to the right grid");
        lb.setFont(Font.font(font,12));
        midBox.getChildren().addAll(gridPaneBox,lb);


        outsideBox = new VBox();
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        outsideBox.setBackground(background);
        if (theme=="BLACK"){

            outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,
                    CornerRadii.EMPTY, Insets.EMPTY)));

        }
        outsideBox.setSpacing(10);
        outsideBox.setAlignment(Pos.TOP_CENTER);
        outsideBox.getChildren().addAll(backBox,titleBox,midBox);

        scene = new Scene(outsideBox);
        stage.setScene(scene);
    }

    private static void setBackBtn(){
        EventHandler<ActionEvent> backEvent = (ActionEvent e) -> {
            StartScene.setStartScene();
        };
        backBtn.setOnAction(backEvent);

    }
    private static void setBackBtn(int level){
        EventHandler<ActionEvent> backEvent = (ActionEvent e) -> {
            
            GameScene.backToGame(level);
            
        };
        backBtn.setOnAction(backEvent);
    }

    public static void setFont(String font){
        if (outsideBox!=null){
            title.setFont(Font.font(font,20));
            lb.setFont(Font.font(font));
        }
        else{
            HelpScene.font=font;
        }
    }
    
    public static void setTheme(String theme){
        
        if (outsideBox!=null){
            if (theme=="BLACK"){
                outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,
                        CornerRadii.EMPTY, Insets.EMPTY)));

            }
            else if(theme=="WHITE"){
                outsideBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
                        CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else{
            
            GameScene.theme=theme;
        }

    }

}
