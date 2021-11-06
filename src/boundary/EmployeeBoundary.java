package boundary;

import java.io.FileNotFoundException;
import control.EmployeeControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EmployeeBoundary extends Application {

	private TextField tfId = new TextField(); 
	private ComboBox cbActive = new ComboBox();
	private TextField tfEmail = new TextField();
	private TextField tfUsername = new TextField();
	private TextField tfFullname = new TextField();
	private TextField tfTelephoneNumber = new TextField();
	private TextField tfBankDetails = new TextField();
	private TextField tfSpecialty = new TextField();
	private DatePicker dpBirthDate = new DatePicker();
	private ComboBox cbRole = new ComboBox();
	private DatePicker dpCreated = new DatePicker();

	private Label lblId = new Label("Id"); 
	private Label lblActive = new Label("Ativo");
	private Label lblEmail = new Label("Email");	
	private Label lblUsername = new Label("Username");
	private Label lblFullname = new Label("Nome");
	private Label lblTelephoneNumber = new Label("Telefone");
	private Label lblBankDetails = new Label("Dados Bancários");
	private Label lblSpecialty = new Label("Especialidade");
	private Label lblBirthDate = new Label("Data de Nascimento");
	private Label lblRole = new Label("Role");
	private Label lblCreated = new Label("Criado Em");

	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	private Button btnFind = new Button("Pesquisar");
	private Button btnCreate = new Button("Adicionar");
	private Button btnDelete = new Button("Remover");

	private static EmployeeControl control = new EmployeeControl();

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane mainPane = new AnchorPane(); 
		AnchorPane menuPane = new AnchorPane();
		AnchorPane formPane = new AnchorPane(); 

		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		Font fontLbls = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		Font fontTf = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);

		binding();

		menuPane.setPrefHeight(768.0);
		menuPane.setPrefWidth(300.0);
		menuPane.setStyle("-fx-background-color: #000E44;");

		formPane.setPrefHeight(768.0);
		formPane.setPrefWidth(1066.0);
		formPane.setStyle("-fx-background-color: #ffffff;");
		formPane.setLayoutX(300.0);

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

		lblActive.setLayoutX(15.0);
		lblActive.setLayoutY(75.0);
		lblActive.setPrefHeight(17.0);
		lblActive.setPrefWidth(40.0);
		lblActive.setFont(fontLbls);

		cbActive.setLayoutX(95.0);
		cbActive.setLayoutY(71.0);
		cbActive.setPrefHeight(25.0);
		cbActive.setPrefWidth(400.0);

		lblEmail.setLayoutX(15.0);
		lblEmail.setLayoutY(112.0);
		lblEmail.setPrefHeight(17.0);
		lblEmail.setPrefWidth(46.0);
		lblEmail.setFont(fontLbls);

		tfEmail.setLayoutX(95.0);
		tfEmail.setLayoutY(109.0);
		tfEmail.setPrefHeight(25.0);
		tfEmail.setPrefWidth(400.0);
		tfEmail.setFont(fontTf);

		lblUsername.setLayoutX(15.0);
		lblUsername.setLayoutY(151.0);
		lblUsername.setPrefHeight(17.0);
		lblUsername.setPrefWidth(70.0);
		lblUsername.setFont(fontLbls);

		tfUsername.setLayoutX(95.0);
		tfUsername.setLayoutY(148.0);
		tfUsername.setPrefHeight(25.0);
		tfUsername.setPrefWidth(400.0);
		tfUsername.setFont(fontTf);

		lblFullname.setLayoutX(15.0);
		lblFullname.setLayoutY(189.0);
		lblFullname.setPrefHeight(17.0);
		lblFullname.setPrefWidth(46.0);
		lblFullname.setFont(fontLbls);

		tfFullname.setLayoutX(95.0);
		tfFullname.setLayoutY(187.0);
		tfFullname.setPrefHeight(25.0);
		tfFullname.setPrefWidth(400.0);
		tfFullname.setFont(fontTf);

		lblTelephoneNumber.setLayoutX(15.0);
		lblTelephoneNumber.setLayoutY(230.0);
		lblTelephoneNumber.setPrefHeight(17.0);
		lblTelephoneNumber.setPrefWidth(77.0);
		lblTelephoneNumber.setFont(fontLbls);

		tfTelephoneNumber.setLayoutX(95.0);
		tfTelephoneNumber.setLayoutY(226.0);
		tfTelephoneNumber.setPrefHeight(25.0);
		tfTelephoneNumber.setPrefWidth(400.0);
		tfTelephoneNumber.setFont(fontTf);

		lblBankDetails.setLayoutX(510.0);
		lblBankDetails.setLayoutY(37.0);
		lblBankDetails.setPrefHeight(17.0);
		lblBankDetails.setPrefWidth(130.0);
		lblBankDetails.setFont(fontLbls);

		tfBankDetails.setLayoutX(645.0);
		tfBankDetails.setLayoutY(33.0);
		tfBankDetails.setPrefHeight(25.0);
		tfBankDetails.setPrefWidth(400.0);
		tfBankDetails.setFont(fontTf);

		lblSpecialty.setLayoutX(510.0);
		lblSpecialty.setLayoutY(75.0);
		lblSpecialty.setPrefHeight(17.0);
		lblSpecialty.setPrefWidth(150.0);
		lblSpecialty.setFont(fontLbls);

		tfSpecialty.setLayoutX(645.0);
		tfSpecialty.setLayoutY(71.0);
		tfSpecialty.setPrefHeight(25.0);
		tfSpecialty.setPrefWidth(400.0);
		tfSpecialty.setFont(fontTf);

		lblBirthDate.setLayoutX(510.0);
		lblBirthDate.setLayoutY(112.0);
		lblBirthDate.setPrefHeight(17.0);
		lblBirthDate.setPrefWidth(150.0);
		lblBirthDate.setFont(fontLbls);

		dpBirthDate.setLayoutX(645.0);
		dpBirthDate.setLayoutY(108.0);
		dpBirthDate.setPrefHeight(25.0);
		dpBirthDate.setPrefWidth(400.0);

		lblRole.setLayoutX(510.0);
		lblRole.setLayoutY(151.0);
		lblRole.setPrefHeight(17.0);
		lblRole.setPrefWidth(100.0);
		lblRole.setFont(fontLbls);

		cbRole.setLayoutX(645.0);
		cbRole.setLayoutY(147.0);
		cbRole.setPrefHeight(25.0);
		cbRole.setPrefWidth(400.0);
		cbRole.setDisable(true);

		lblCreated.setLayoutX(510.0);
		lblCreated.setLayoutY(189.0);
		lblCreated.setPrefHeight(17.0);
		lblCreated.setPrefWidth(100.0);
		lblCreated.setFont(fontLbls);

		dpCreated.setLayoutX(645.0);
		dpCreated.setLayoutY(187.0);
		dpCreated.setPrefHeight(25.0);
		dpCreated.setPrefWidth(400.0);
		dpCreated.setEditable(false);
		dpCreated.setDisable(true);

        if (control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        
        Node table = control.getTable();

		formPane.getChildren().addAll(lblId, tfId, lblActive, cbActive, lblEmail, tfEmail, lblUsername, tfUsername, 
				lblFullname, tfFullname, lblTelephoneNumber, tfTelephoneNumber, lblBankDetails, tfBankDetails, lblSpecialty, 
				tfSpecialty, lblBirthDate, dpBirthDate, lblRole, cbRole, lblCreated, dpCreated,
				btnCreate, btnFind, btnUpdate, btnDelete, btnClear
				, table
				);

		btnCreate.setOnAction((e) -> {
			control.create();
		});
		btnCreate.setLayoutX(15.0);
		btnCreate.setLayoutY(269.0);
		btnCreate.setFont(fontBtns);

		btnFind.setOnAction((e) -> {
			control.findByEmail();
		});
		btnFind.setLayoutX(281.0);
		btnFind.setLayoutY(269.0);
		btnFind.setFont(fontBtns);

		btnUpdate.setOnAction((e) -> {
			control.updateById();
		});
		btnUpdate.setLayoutX(196.0);
		btnUpdate.setLayoutY(269.0);
		btnUpdate.setFont(fontBtns);

		btnDelete.setOnAction((e) -> {
			control.deleteById();
		});
		btnDelete.setLayoutX(105.0);
		btnDelete.setLayoutY(269.0);
		btnDelete.setFont(fontBtns);

		btnClear.setOnAction((e) -> {
			control.clearFields();
		});
		btnClear.setLayoutX(450.0);
		btnClear.setLayoutY(269.0);
		btnClear.setFont(fontBtns);

		mainPane.setLeftAnchor(menuPane, 0.0);
		mainPane.setRightAnchor(formPane, 0.0);
		mainPane.getChildren().addAll(menuPane, formPane);
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);

		Scene scene = new Scene(mainPane, 1366, 768);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
	}

	private void binding() {
		Bindings.bindBidirectional(tfId.idProperty(), control.idProperty());
		Bindings.bindBidirectional(cbActive.valueProperty(), control.activeProperty());
		Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(tfUsername.textProperty(), control.usernameProperty());
		Bindings.bindBidirectional(tfFullname.textProperty(), control.fullnameProperty());
		Bindings.bindBidirectional(tfTelephoneNumber.textProperty(), control.telephoneNumberProperty());
		Bindings.bindBidirectional(tfBankDetails.textProperty(), control.bankDetailsProperty());
		Bindings.bindBidirectional(tfSpecialty.textProperty(), control.specialtyProperty());
		Bindings.bindBidirectional(dpBirthDate.valueProperty(), control.birthDateProperty());
		Bindings.bindBidirectional(cbRole.valueProperty(), control.roleProperty());
		Bindings.bindBidirectional(dpCreated.valueProperty(), control.createdProperty());
	}

	public static void main(String[] args) {
		launch(args);
	}

}