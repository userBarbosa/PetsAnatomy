package boundary;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import control.LoginControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.MongoConnect;

public class MainBoundary extends Application {

	static StrategyBoundary login = new LoginBoundary();
	static StrategyBoundary image = new ImageBoundary();
	
	public static AnchorPane mainPane = new AnchorPane();
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(mainPane, 1366, 768);

		mainPane.setLeftAnchor(login.generateBoundaryStrategy(), 0.0);
		mainPane.setRightAnchor(image.generateBoundaryStrategy(), 0.0);
		mainPane.getChildren().addAll(login.generateBoundaryStrategy(), image.generateBoundaryStrategy());
		
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);

		stage.setTitle("Clínica Veterinária PetsAnatomy");	
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		MongoConnect mc = new MongoConnect();
		mc.connection();
		Application.launch(MainBoundary.class, args);
	}

	public void setPaneLeftAnchor(Pane left, Pane right) {
		mainPane.getChildren().clear();
		mainPane.setLeftAnchor(left, 0.0);
		mainPane.setRightAnchor(right, 0.0);
		mainPane.getChildren().addAll(left, right);
	}
}
