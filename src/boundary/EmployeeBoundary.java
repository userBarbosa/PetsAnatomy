package boundary;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class EmployeeBoundary extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane mainPane = new AnchorPane(); 
		AnchorPane menuPane = new AnchorPane();
		AnchorPane formPane = new AnchorPane(); 
		
		menuPane.prefHeight(768.0);
		menuPane.prefWidth(300.0);
		menuPane.setStyle("-fx-background-color: #000E44;");
		
		formPane.prefHeight(768.0);
		formPane.prefWidth(1066.0);
		
		HBox.setHgrow(menuPane, Priority.ALWAYS);
		HBox.setHgrow(formPane, Priority.ALWAYS);
		mainPane.getChildren().addAll(menuPane, formPane);
		Scene scene = new Scene(mainPane, 1366, 768);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}