/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
public class SettingsScene {

    private static ThemeToggleSwitch themeSwitch = new ThemeToggleSwitch();
    final private static String[] fontList = {"Arial","Courier","Comic Sans MS"};
    final private static ComboBox fontComboBox = new ComboBox(FXCollections.observableArrayList(fontList));

    
    private static Scene scene;
    private static VBox outsideBox;
    private static boolean interfaceSet=false;
    private static Text title;
    private static ArrayList<Text> titles=new ArrayList<Text>();


    public static Button backBtn;
    

    private static Slider rectSizeSlider;
    private static Slider flashDurationSlider;

    public static void setSettingsScene(Stage stage) {
        
        if (!interfaceSet){
            setInterface();
        }
        setBackBtn();
        stage.setScene(scene);
    }

    public static void setSettingsScene(int level){

        if (!interfaceSet){
            setInterface();
        }
        setBackBtn(level);
        stage.setScene(scene);
    }

    private static void setInterface(){
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
        title=new Text("Settings");
        title.setFont(Font.font(20));
        titleBox.setPadding(insets5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title);

        VBox themeBox = new VBox();
        titles.add(new Text("Theme"));
        themeBox.setAlignment(Pos.CENTER);
        themeBox.setSpacing(5);
        themeBox.setPadding(insets5);
        themeBox.getChildren().addAll(titles.get(0),themeSwitch);

        VBox fontBox = new VBox();
        titles.add(new Text("Font"));
        fontBox.setAlignment(Pos.CENTER);
        fontBox.setSpacing(5);
        fontBox.setPadding(insets5);
        fontComboBox.setPrefWidth(100);
        fontComboBox.getSelectionModel().selectFirst();
        fontBox.getChildren().addAll(titles.get(1),fontComboBox);

        VBox rectSizeBox = new VBox();
        titles.add(new Text("Rectangle Size"));
        rectSizeBox.setAlignment(Pos.CENTER);
        rectSizeBox.setSpacing(5);
        rectSizeBox.setPadding(insets5);
        rectSizeSlider = new Slider();
        rectSizeSlider.setMin(30);
        rectSizeSlider.setMax(50);
        rectSizeSlider.setMajorTickUnit(10f);
        rectSizeSlider.setValue(30);
        
        //rectSizeSlider.setShowTickLabels(true);
        rectSizeSlider.setPrefWidth(50);
        rectSizeBox.getChildren().addAll(titles.get(2),rectSizeSlider);

        VBox flashDurationBox = new VBox();
        titles.add(new Text("Flash Duration"));
        flashDurationBox.setAlignment(Pos.CENTER);
        flashDurationBox.setSpacing(5);
        flashDurationBox.setPadding(insets5);
        flashDurationSlider = new Slider();
        flashDurationSlider.setMin(200);
        flashDurationSlider.setMax(500);
        flashDurationSlider.setMajorTickUnit(100f);
        flashDurationSlider.setValue(500);
        
        //flashDurationSlider.setShowTickLabels(true);
        flashDurationSlider.setPrefWidth(50);
        flashDurationBox.getChildren().addAll(titles.get(3),flashDurationSlider);

        setListeners();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(insets5);
        gridPane.add(themeBox,1,1,1,1);
        gridPane.add(fontBox,2,1,1,1);
        gridPane.add(rectSizeBox,1,2,1,1);
        gridPane.add(flashDurationBox,2,2,1,1);

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
        outsideBox.getChildren().addAll(backBox,titleBox,gridPane);

        scene = new Scene(outsideBox);
        
    }

    private static void setListeners(){
        
        fontComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String a, String font) {
                GameScene.setFont(font);
                StartScene.setFont(font);
                HelpScene.setFont(font);
                HighscoreScene.setFont(font);
                title.setFont(Font.font(font,20));
                for (Text t:titles){
                    t.setFont(Font.font(font));
                }
            }
        });
        rectSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number a, Number rectSize) {
                GameScene.setRectSize((int)rectSize.doubleValue());
            }
        });
        flashDurationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number a, Number flashDuration) {
                GameScene.setFlashDuration((int)flashDuration.doubleValue());
            }
        });
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
