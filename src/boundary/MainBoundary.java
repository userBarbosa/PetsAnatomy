package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.MongoConnect;

public class MainBoundary extends Application {

	StrategyBoundary login = new LoginBoundary();
	StrategyBoundary signUp = new SignUpBoundary();
	StrategyBoundary dash = new DashboardBoundary();
	StrategyBoundary sideMenu = new SideMenuBoundary();
	StrategyBoundary image = new ImageBoundary();
	
	public static AnchorPane mainPane = new AnchorPane();

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(mainPane, 1366, 768);
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 18);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
		
		mainPane.setLeftAnchor(sideMenu.generateBoundaryStrategy(), 0.0);
		mainPane.setRightAnchor(dash.generateBoundaryStrategy(), 0.0);
		mainPane.getChildren().addAll(sideMenu.generateBoundaryStrategy(), dash.generateBoundaryStrategy());
		
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

	public void setRightPane(Pane boundary) {
		mainPane.getChildren().clear();
		mainPane.setRightAnchor(boundary, 0.0);
		mainPane.getChildren().add(boundary);
	}
	
	public void setLeftPane(Pane boundary) {
		mainPane.getChildren().clear();
		mainPane.setLeftAnchor(boundary, 0.0);
		mainPane.getChildren().add(boundary);
	}

}
