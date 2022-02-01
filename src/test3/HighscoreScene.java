/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
/**
 *
 * @author alois
 */
public class HighscoreScene {
    private static boolean interfaceSet = false;
    private static VBox outsideBox;
    private static VBox midBox;
    private static Scene scene;

    private static Text title;
    private static String font;
    private static HighscoreController controller = new HighscoreController();
    private static ArrayList<Highscore> highscoreList ;

    public static void setHighscoreScene(int level){
        Highscore newScore = new Highscore();
        newScore.setLevel(level);
        controller.setScore(newScore);
        highscoreList = controller.getHighscoreList();
        if (!interfaceSet){
            setInterface();
        }
        update();

        stage.setScene(scene);

    }
    private static void setInterface() {
        interfaceSet=true;
        Button backBtn = new Button();
        String backIconPath = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhpWkKwgrQH4Rk4EgjWYDfurRNAgbYp6tqQQ&usqp=CAU";
        Image backIcon = new Image(backIconPath);
        ImageView backIconView = new ImageView(backIcon);
        backIconView.setFitWidth(50);
        backIconView.setPreserveRatio(true);
        backBtn.setPrefSize(50,20);
        backBtn.setGraphic(backIconView);
        EventHandler<ActionEvent> backEvent = (ActionEvent e) -> {
            StartScene.setStartScene(stage);
        };
        backBtn.setOnAction(backEvent);

        VBox backBox = new VBox();
        backBox.setPadding(insets5);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.getChildren().addAll(backBtn);

        VBox titleBox = new VBox();
        title=new Text("High Scores");
        title.setFont(Font.font(font, 20));
        titleBox.setPadding(insets5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title);


        midBox = new VBox();
        midBox.setSpacing(5);
        midBox.setAlignment(Pos.CENTER);
        midBox.setPadding(new Insets(10,10,10,10));


        for(int i=0;i<highscoreList.size();i++){
            Label lb = new Label((i+1) + ". " + highscoreList.get(i).toString());
            lb.setFont(Font.font(font,12));
            midBox.getChildren().add(lb);
        }

        outsideBox=new VBox();
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


    private static void update(){
        midBox.getChildren().clear();
        for(int i=0;i<highscoreList.size();i++){
            Label lb = new Label((i+1) +". " + highscoreList.get(i).toString());
            lb.setFont(Font.font(font,12));
            midBox.getChildren().add(lb);
        }
        title.setFont(Font.font(font,20));
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        outsideBox.setBackground(background);
        if (theme=="BLACK"){

            outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,
                    CornerRadii.EMPTY, Insets.EMPTY)));

        }
    }


    public static void setFont(String font){

        HighscoreScene.font=font;

    }
    
    public static void setTheme(String theme){
        
        GameScene.theme=theme;


    }
}
