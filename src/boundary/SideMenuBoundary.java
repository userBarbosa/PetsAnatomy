package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SideMenuBoundary implements StrategyBoundary, EventHandler<ActionEvent> {
	
	@Override
	public Pane generateBoundaryStrategy() {
		AnchorPane sideMenu = new AnchorPane();
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);

		sideMenu.prefHeight(768.0);
		sideMenu.prefWidth(300.0);
		sideMenu.setStyle("-fx-background-color: #000E44;");

		return sideMenu;
	}

	@Override
	public void handle(ActionEvent e) {
		EventTarget target = e.getTarget();
	}
}
