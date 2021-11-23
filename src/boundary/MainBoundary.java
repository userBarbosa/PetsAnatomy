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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.MongoConnect;

public class MainBoundary extends Application implements EventHandler<ActionEvent> {

	//  private ScreenStrategy screenSignUp = new SignUpBoundary();
	//  private ScreenStrategy screenLogin = new LoginBoundary();
	StrategyBoundary dash = new DashboardBoundary();
	Map<String, StrategyBoundary> boundaries = new HashMap<>();
	
	public static AnchorPane mainPane = new AnchorPane();
	
	public MainBoundary() {
		boundaries.put("Login", new LoginBoundary());
	}

	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(mainPane, 1366, 768);
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 18);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);

		
		mainPane.setLeftAnchor(sideMenu, 0.0);
		mainPane.setRightAnchor(dash.generateBoundaryStrategy(), 0.0);
		mainPane.getChildren().addAll(sideMenu, dash.generateBoundaryStrategy());
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);

		sideMenu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());;
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		EventTarget target = e.getTarget();
		if (target instanceof RadioButton) {
			RadioButton radioButton = (RadioButton) target;
			String text = radioButton.getText();
			StrategyBoundary boundary = boundaries.get(text);
			mainPane.getChildren().clear();
			mainPane.setRightAnchor(boundary.generateBoundaryStrategy(), 0.0);
			mainPane.setLeftAnchor(sideMenu, 0.0);
			mainPane.getChildren().addAll(sideMenu, boundary.generateBoundaryStrategy());
			if(!radioButton.isSelected()) {
				radioButton.setSelected(true);    
			}
		}
	}

	public static void main(String[] args) {
		MongoConnect mc = new MongoConnect();
		mc.connection();
		Application.launch(MainBoundary.class, args);
	}

}
