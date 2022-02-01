/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import test3.HighscoreScene;
import test3.Position;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.util.Duration;
import static test3.Main.insets5;
import static test3.StartScene.topBtnBox;
import static test3.StartScene.stage;

/**
 *
 * @author alois
 */

public class GameScene {
    static ArrayList<Position> orderOfRectsFlashed = new ArrayList<Position>();
    static ArrayList<Position> orderOfBtnsPressed = new ArrayList<Position>();
    static GridPane rectPane = new GridPane();
    static GridPane gridPane = new GridPane();
    static Rectangle[][] rectList= new Rectangle[3][3];;
    static Rectangle[][] tempRectList= new Rectangle[3][3];
    static Button[][] btnList = new Button[3][3];;
    static int level;

    static ArrayList<Timeline> timelines = new ArrayList<Timeline>();
    
    static Scene scene;
    static VBox outsideBox;
    static Text title;

    static Color rectColor=Color.GREY;
    static String btnColor="grey";
    static String font;
    static int rectSize;
    static int flashDuration = 500;
    static String theme;
    static boolean interfaceSet=false;

    public static void setGameScene(){
        if(!interfaceSet){
            setInterface();
        }
        else{
            stage.setScene(scene);
        }
        startLevel(1);
    }

    //called at the start of every level
    private static void startLevel(int level){
        GameScene.level = level;
        orderOfRectsFlashed.clear();
        orderOfBtnsPressed.clear();

        setStartLevel(level);

        displayRectsFlashed();

    }

    //prints the list of the order of the rectangles flashed
    private static void printOrderOfRectsFlashed(){
        for (Position p: orderOfRectsFlashed){
            System.out.println(p);
        }
    }
    //sets the GUI when the setgamescene is first called
    private static void setInterface(){
        interfaceSet=true;
        EventHandler<ActionEvent> settingsEvent = (ActionEvent e) -> {
            SettingsScene.setSettingsScene(level);
            pauseTimelines();
        };
        StartScene.settingsBtn.setOnAction(settingsEvent);
        EventHandler<ActionEvent> helpEvent = (ActionEvent e) -> {
            HelpScene.setHelpScene(level);
            pauseTimelines();
        };
        StartScene.helpBtn.setOnAction(helpEvent);

        
        if (rectSize==0){
            rectSize=30;
        }
        
        if (theme=="BLACK"){
            rectColor=Color.BLACK;
            btnColor="black";
        }

        title = new Text();
        
        if (font!=null){
            title.setFont(Font.font(font,20));
        }
        else{
            title.setFont(Font.font("Arial",20));
        }
        VBox topBox = new VBox();
        outsideBox = new VBox();
        HBox midBox =  new HBox(10);
        for (int i = 0; i<3;i++){
            for (int j=0; j<3;j++){
                Rectangle r = new Rectangle(0,0,rectSize,rectSize);
                Button b = new Button();
                b.setPrefSize(rectSize,rectSize);
                b.setStyle("-fx-background-color: grey");

                Rectangle r2 = new Rectangle(0,0,rectSize,rectSize);
                r2.setFill(rectColor);
                r2.setStroke(Color.TRANSPARENT);
                tempRectList[i][j]=r2;

                btnList[i][j]=b;
                rectList[i][j]=r;
                r.setFill(rectColor);
                r.setStroke(Color.TRANSPARENT);
                rectPane.add(new Group(rectList[i][j]),j,i,1,1);
                gridPane.add(tempRectList[i][j], j, i, 1, 1);
            }
        }

        topBox.getChildren().addAll(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(30);
        topBox.setPadding(new Insets(20,5,5,5));

        rectPane.setAlignment(Pos.CENTER);
        rectPane.setHgap(10);
        rectPane.setVgap(10);
        rectPane.setPadding(new Insets(10, 10, 30, 10));

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 30, 10));
        midBox.setAlignment(Pos.CENTER);
        midBox.getChildren().addAll(rectPane,gridPane);

        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        outsideBox.setBackground(background);
        if (theme=="BLACK"){

            outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            setRectColor(Color.BLACK, "black");
        }
        outsideBox.setAlignment(Pos.TOP_CENTER);
        
        outsideBox.getChildren().addAll(StartScene.topBtnBox,topBox,midBox);
        scene = new Scene(outsideBox, 240, 100);

        stage.setScene(scene);
        stage.show();
    }
    //calls functions at the start of every level
    private static void setStartLevel(int level){
        title.setText("Level "+level);
        
        for (int i = 0; i<3;i++){
            for (int j=0; j<3;j++){
                gridPane.add(new Group(tempRectList[i][j]), j, i, 1, 1);
            }
        }

        setOrderOfRectsFlashed(level);
        setTimelines(level);

        stage.setScene(scene);
    }
    //sets random positions in the list orderOfRectsFlashed with size n
    private static void setOrderOfRectsFlashed(int n){
        
        Random randRow = new Random();
        Random randCol = new Random();

        orderOfRectsFlashed.add(new Position(randRow.nextInt(3),randCol.nextInt(3)));

        if (n>1){
            setOrderOfRectsFlashed(n-1);
        }
        

    }
    //sets the orderOfRectsFlashed to the GUI
    private static void setTimelines(int level){
        count = 0;
        e=0;
        for (Position p: orderOfRectsFlashed){
            setTimeline(p, level);

        }
    }

    
    static int count;
    static int e;
    //sets each individual flashing; called multiple times from setTimelines
    private static void setTimeline(Position p, int level){

        Timeline t = new Timeline();
        t.getKeyFrames().addAll(new KeyFrame(Duration.millis(flashDuration),e -> rectList[p.getRow()][p.getCol()].setFill(Color.GREEN)),new KeyFrame(Duration.millis(flashDuration), e -> addCount()));

        t.setDelay(Duration.millis(e));
        t.setOnFinished(e -> timelines.remove(t));
        
        e+=flashDuration;
        Timeline t2 = new Timeline();
        t2.getKeyFrames().addAll(new KeyFrame(Duration.millis(flashDuration),e -> rectList[p.getRow()][p.getCol()].setFill(rectColor)),new KeyFrame(Duration.millis(flashDuration),e -> shouldUpdateGridPane(level)));
        t2.setDelay(Duration.millis(e));
        t2.setOnFinished(e -> timelines.remove(t2));
        
        e+=flashDuration;
        timelines.add(t);
        timelines.add(t2);
    }
    //calls the function that plays the animation of the flashing rectangles
    private static void displayRectsFlashed(){
        count = 0;
        playTimelines();
    }
    //plays the animation of the flashing rectangles
    private static void playTimelines(){
        
        for (Timeline t: timelines){
            t.play();

        }
    }
    //pauses the animation of the flashing rectangles
    private static void pauseTimelines(){
        for (Timeline t: timelines){
            t.pause();

        }
    }
    //sets animation for a clicked btn to make it seem as if its being pressed down
    private static void setClickedBtn(Position p){
        ColorAdjust colorAdjust;
        if (btnColor=="grey"){
            colorAdjust = new ColorAdjust(0,0.1,-0.2,0);
        }
        else{
            colorAdjust = new ColorAdjust(0,-0.2,0.1,0);
        }
        btnList[p.getRow()][p.getCol()].setEffect(colorAdjust);
    }
    //resets the button to its original color
    private static void setUnclickedBtn(Position p){
        btnList[p.getRow()][p.getCol()].setEffect(null);
    }
    //dont worry about this
    private static void addCount(){
        count++;
    }
    //checks if the buttons should be updated to the right side gridpane
    private static void shouldUpdateGridPane(int level){
        if (count==level){
            updateGridPane(level);
        }
    }
    //called from helpscene and settingsscene when the back button is pressed
    public static void backToGame(int level){
        stage.setScene(scene);
        playTimelines();
    }
    //updates the right side gridpane
    //gridpane is changed to buttons that can be pressed instead of rectangles that cant
    private static void updateGridPane(int level) {

        gridPane.getChildren().clear();
        for (int i = 0; i<3;i++){
            for (int j=0; j<3;j++){
                gridPane.add(btnList[i][j], j, i, 1, 1);
            }
        }
        recordBtnsPressed(level);
    }
    //keeps the order in which buttons are pressed to check if the pattern is the same
    //as the rectangles flashing
    private static void recordBtnsPressed(int level){
        for (int i=0;i<btnList.length;i++){
            for (int j=0;j<btnList[i].length;j++){
                Position p = new Position(i,j);

                btnList[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                setClickedBtn(p);
                            }
                        });
                btnList[i][j].addEventHandler(MouseEvent.MOUSE_EXITED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                setUnclickedBtn(p);
                            }
                        });
                EventHandler<ActionEvent> event = (ActionEvent e) -> {

                    if (orderOfBtnsPressed.size()<orderOfRectsFlashed.size()){

                        orderOfBtnsPressed.add(p);
                        if (!p.equals(orderOfRectsFlashed.get(orderOfBtnsPressed.size()-1))){
                            lose(level);
                        }
                    }

                    if (orderOfBtnsPressed.size()==orderOfRectsFlashed.size()){
                        boolean areSame = compareOrders();
                        if (areSame){
                            win(level);
                        }
                    }

                };
                btnList[i][j].setOnAction(event);
            }
        }

    }
    //compares the orders of rects flashed and btns pressed
    private static boolean compareOrders(){


        for (int i=0; i<orderOfRectsFlashed.size();i++){
            if (!orderOfRectsFlashed.get(i).equals(orderOfBtnsPressed.get(i))){

                return false;
            }
        }
        return true;
    }
    //called if the orders are the same
    //calls the level function for the next level
    private static void win(int level){
        
        startLevel(level+1);
    }
    //called if orders are not the same
    //calls the sethighscorescene
    private static void lose(int level){
        HighscoreScene.setHighscoreScene(level);
    }
    //called from settingsscene when font is changed in settings
    public static void setFont(String font){
        if (outsideBox!=null){
            title.setFont(Font.font(font,20));
        }
        else{
            GameScene.font=font;
        }
    }
    //called from themetoggleswitch when theme is changed in settings
    public static void setTheme(String theme){
        if (outsideBox!=null){
            if (theme=="BLACK"){

                outsideBox.setBackground(new Background(new BackgroundFill(Color.GREY,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                setRectColor(Color.BLACK, "black");
            }
            else if(theme=="WHITE"){
                outsideBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                setRectColor(Color.GREY, "grey");

            }
        }
        else{
            
            GameScene.theme=theme;
        }

    }
    //called from previous function to change the rectangle color with the theme
    private static void setRectColor(Color color, String colour){

        rectColor=color;
        btnColor=colour;
        for (int i=0;i<rectList.length;i++){
            for (int j=0;j<rectList[i].length;j++){
                rectList[i][j].setFill(color);
                tempRectList[i][j].setFill(color);
                btnList[i][j].setStyle("-fx-background-color: "+colour);
            }
        }
    }
    //called from settingsscene when rectangle size is changed in settings
    public static void setRectSize(int rectSize){
        if (outsideBox!=null){
            for (int i =0;i<rectList.length;i++){
                for (int j=0;j<rectList[i].length;j++){
                    rectList[i][j].setWidth(rectSize);
                    rectList[i][j].setHeight(rectSize);
                    tempRectList[i][j].setWidth(rectSize);
                    tempRectList[i][j].setHeight(rectSize);
                    btnList[i][j].setPrefSize(rectSize, rectSize);
                }
            }
        }
        else{
            GameScene.rectSize=50;
        }

    }
    //called from settingsscene when flash duration is changed in settings
    public static void setFlashDuration(int flashDuration){
        GameScene.flashDuration=flashDuration;
        
    }


}
