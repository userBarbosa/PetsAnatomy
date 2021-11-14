package boundary;

import java.io.FileNotFoundException;

import control.ConfigurationControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfigurationBoundary implements StrategyBoundary  {
	
	private TextField tfId = new TextField(); 
	private TextField tfEmail = new TextField();
	private TextField tfUsername = new TextField();
	private TextField tfPassword = new TextField();
	private ComboBox cbRole = new ComboBox();
	
	private Label lblId = new Label("Id"); 
	private Label lblEmail = new Label("Email");	
	private Label lblUsername = new Label("Username");
	private Label lblPassword = new Label("Senha");	
	private Label lblRole = new Label("Role");
	
	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	
	private static ConfigurationControl control = new ConfigurationControl();
	
	@Override	
	public Pane generateBoundaryStrategy() {
		AnchorPane formPane = new AnchorPane(); 
		
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		Font fontLbls = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		Font fontTf = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		
		binding();
		
		formPane.setPrefHeight(768.0);
		formPane.setPrefWidth(1066.0);
		formPane.setLayoutX(300.0);
		formPane.setStyle("-fx-background-color: #ffffff;");
		
		lblId.setLayoutX(15.0);
		lblId.setLayoutY(37.0);
		lblId.setPrefHeight(17.0);
		lblId.setPrefWidth(20.0);
		lblId.setFont(fontLbls);

		tfId.setDisable(true);
		tfId.setLayoutX(95.0);
		tfId.setLayoutY(33.0);
		tfId.setPrefHeight(25.0);
		tfId.setPrefWidth(400.0);
		tfId.setFont(fontTf);

		lblEmail.setLayoutX(15.0);
		lblEmail.setLayoutY(75.0);
		lblEmail.setPrefHeight(17.0);
		lblEmail.setPrefWidth(40.0);
		lblEmail.setFont(fontLbls);

		tfEmail.setLayoutX(95.0);
		tfEmail.setLayoutY(71.0);
		tfEmail.setPrefHeight(25.0);
		tfEmail.setPrefWidth(400.0);
		tfEmail.setFont(fontTf);
		tfEmail.setDisable(true);

		lblUsername.setLayoutX(15.0);
		lblUsername.setLayoutY(112.0);
		lblUsername.setPrefHeight(17.0);
		lblUsername.setPrefWidth(100.0);
		lblUsername.setFont(fontLbls);

		tfUsername.setLayoutX(95.0);
		tfUsername.setLayoutY(109.0);
		tfUsername.setPrefHeight(25.0);
		tfUsername.setPrefWidth(400.0);
		tfUsername.setFont(fontTf);
		tfUsername.setDisable(true);
		
		lblPassword.setLayoutX(510.0);
		lblPassword.setLayoutY(37.0);
		lblPassword.setPrefHeight(17.0);
		lblPassword.setPrefWidth(100.0);
		lblPassword.setFont(fontLbls);

		tfPassword.setLayoutX(600.0);
		tfPassword.setLayoutY(33.0);
		tfPassword.setPrefHeight(25.0);
		tfPassword.setPrefWidth(400.0);
		tfPassword.setFont(fontTf);
		tfPassword.setDisable(true);
		
		lblRole.setLayoutX(510.0);
		lblRole.setLayoutY(75.0);
		lblRole.setPrefHeight(17.0);
		lblRole.setPrefWidth(150.0);
		lblRole.setFont(fontLbls);

		cbRole.setLayoutX(600.0);
		cbRole.setLayoutY(71.0);
		cbRole.setPrefHeight(25.0);
		cbRole.setPrefWidth(400.0);

        if (control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        
        Node table = control.getTable();

		formPane.getChildren().addAll(lblId, tfId, lblEmail, tfEmail, lblUsername, tfUsername, 
				lblPassword, tfPassword, lblRole, cbRole, 
				btnUpdate, btnClear
				, table
				);
		
		btnUpdate.setOnAction((e) -> {
			control.updateById();
		});
		btnUpdate.setLayoutX(15.0);
		btnUpdate.setLayoutY(151.0);
		btnUpdate.setFont(fontBtns);
		
		btnClear.setOnAction((e) -> {
			control.clearFields();
		});
		btnClear.setLayoutX(440.0);
		btnClear.setLayoutY(151.0);
		btnClear.setFont(fontBtns);
		
		return formPane;
	}
	
	private void binding() {
		Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
		Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(tfUsername.textProperty(), control.usernameProperty());
		Bindings.bindBidirectional(tfPassword.textProperty(), control.passwordProperty());
		Bindings.bindBidirectional(cbRole.valueProperty(), control.roleProperty());
	}
	
}