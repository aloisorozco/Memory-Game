/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 *
 * @author Ikiz48
 * not ours we got this class from Github
 */


public class ThemeToggleSwitch extends HBox {

	private final Label label = new Label("");
	public Text text = new Text();
	private final Button button = new Button();

	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

	private void init() {

		text.setText("WHITE");

		getChildren().addAll(label, button);
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		setStyle(100,5);
		bindProperties();
	}

	private void setStyle(int width, int height) {
		//Default Width
		setWidth(width);
		setHeight(height);
		label.setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: white; -fx-text-fill:white; -fx-background-radius: 4;");
		setAlignment(Pos.CENTER_LEFT);
	}

	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}

	public ThemeToggleSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
				text.setText("BLACK");
				GameScene.setTheme(text.getText());
				StartScene.setTheme(text.getText());
				SettingsScene.setTheme(text.getText());
				HelpScene.setTheme(text.getText());
				HighscoreScene.setTheme(text.getText());
				setStyle("-fx-background-color: black; -fx-text-fill:black;");
				label.toFront();
			}
			else {
				text.setText("WHITE");
				GameScene.setTheme(text.getText());
				StartScene.setTheme(text.getText());
				SettingsScene.setTheme(text.getText());
				HelpScene.setTheme(text.getText());
				HighscoreScene.setTheme(text.getText());
				setStyle("-fx-background-color: white; -fx-text-fill:white;");
				button.toFront();
			}
		});
	}

	public String getValue(){
		return text.getText();
	}
}