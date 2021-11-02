package boundary;

import java.io.FileNotFoundException;

import control.OwnerControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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

public class OwnerBoundary extends Application {
	
	private TextField tfId = new TextField(); 
	private ComboBox cbPatients = new ComboBox();
	private TextField tfEmail = new TextField();
	private TextField tfFullname = new TextField();
	private TextField tfTelephoneNumber = new TextField();
	private TextField tfAddress = new TextField();
	private ComboBox cbIdentificationDoc = new ComboBox();
	private TextField tfIdentificationNumber = new TextField();
	private DatePicker dpLastVisit = new DatePicker();
	private DatePicker dpCreated = new DatePicker();
	private DatePicker dpUpdated = new DatePicker();

	private Label lblId = new Label("Id"); 
	private Label lblPatients = new Label("Pacientes");
	private Label lblEmail = new Label("Email");	
	private Label lblFullname = new Label("Nome");
	private Label lblTelephoneNumber = new Label("Telefone");
	private Label lblAddress = new Label("Endereço");
	private Label lblIdentificationDoc = new Label("Documento");
	private Label lblIdentificationNumber = new Label("Número Documento");
	private Label lblLastVisit = new Label("Última Consulta");
	private Label lblCreated = new Label("Criado Em");
	private Label lblUpdated = new Label("Última Atualização");
	
	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	private Button btnFind = new Button("Pesquisar");
	private Button btnCreate = new Button("Adicionar");
	private Button btnDelete = new Button("Remover");
	
	private static OwnerControl control = new OwnerControl();

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
		
		lblId.setLayoutX(15.0);
		lblId.setLayoutY(37.0);
		lblId.setPrefHeight(17.0);
		lblId.setPrefWidth(20.0);
		lblId.setFont(fontLbls);
		
		tfId.setDisable(true);
		tfId.setEditable(false);
		tfId.setLayoutX(95.0);
		tfId.setLayoutY(33.0);
		tfId.setPrefHeight(25.0);
		tfId.setPrefWidth(400.0);
		tfId.setFont(fontTf);
		
		lblPatients.setLayoutX(15.0);
		lblPatients.setLayoutY(75.0);
		lblPatients.setPrefHeight(17.0);
		lblPatients.setPrefWidth(100.0);
		lblPatients.setFont(fontLbls);
		
		cbPatients.setLayoutX(95.0);
		cbPatients.setLayoutY(71.0);
		cbPatients.setPrefHeight(25.0);
		cbPatients.setPrefWidth(400.0);
		
		lblEmail.setLayoutX(15.0);
		lblEmail.setLayoutY(112.0);
		lblEmail.setPrefHeight(17.0);
		lblEmail.setPrefWidth(46.0);
		lblEmail.setFont(fontLbls);
		
		tfEmail.setLayoutX(95.0);
		tfEmail.setLayoutY(109.0);
		tfEmail.setPrefHeight(25.0);
		tfEmail.setPrefWidth(400.0);
		
		lblFullname.setLayoutX(15.0);
		lblFullname.setLayoutY(151.0);
		lblFullname.setPrefHeight(17.0);
		lblFullname.setPrefWidth(100.0);
		lblFullname.setFont(fontLbls);
		
		tfFullname.setLayoutX(95.0);
		tfFullname.setLayoutY(148.0);
		tfFullname.setPrefHeight(25.0);
		tfFullname.setPrefWidth(400.0);
		
		lblTelephoneNumber.setLayoutX(15.0);
		lblTelephoneNumber.setLayoutY(189.0);
		lblTelephoneNumber.setPrefHeight(17.0);
		lblTelephoneNumber.setPrefWidth(100.0);
		lblTelephoneNumber.setFont(fontLbls);
		
		tfTelephoneNumber.setLayoutX(95.0);
		tfTelephoneNumber.setLayoutY(187.0);
		tfTelephoneNumber.setPrefHeight(25.0);
		tfTelephoneNumber.setPrefWidth(400.0);
		
		lblAddress.setLayoutX(15.0);
		lblAddress.setLayoutY(230.0);
		lblAddress.setPrefHeight(17.0);
		lblAddress.setPrefWidth(77.0);
		lblAddress.setFont(fontLbls);
		
		tfAddress.setLayoutX(95.0);
		tfAddress.setLayoutY(226.0);
		tfAddress.setPrefHeight(25.0);
		tfAddress.setPrefWidth(400.0);
		tfAddress.setFont(fontTf);
		
		lblIdentificationDoc.setLayoutX(510.0);
		lblIdentificationDoc.setLayoutY(37.0);
		lblIdentificationDoc.setPrefHeight(17.0);
		lblIdentificationDoc.setPrefWidth(100.0);
		lblIdentificationDoc.setFont(fontLbls);
		
		cbIdentificationDoc.setLayoutX(645.0);
		cbIdentificationDoc.setLayoutY(33.0);
		cbIdentificationDoc.setPrefHeight(25.0);
		cbIdentificationDoc.setPrefWidth(400.0);
		
		lblIdentificationNumber.setLayoutX(510.0);
		lblIdentificationNumber.setLayoutY(75.0);
		lblIdentificationNumber.setPrefHeight(17.0);
		lblIdentificationNumber.setPrefWidth(150.0);
		lblIdentificationNumber.setFont(fontLbls);
		
		tfIdentificationNumber.setLayoutX(645.0);
		tfIdentificationNumber.setLayoutY(71.0);
		tfIdentificationNumber.setPrefHeight(25.0);
		tfIdentificationNumber.setPrefWidth(400.0);
		
		lblLastVisit.setLayoutX(510.0);
		lblLastVisit.setLayoutY(112.0);
		lblLastVisit.setPrefHeight(17.0);
		lblLastVisit.setPrefWidth(100.0);
		lblLastVisit.setFont(fontLbls);
		
		dpLastVisit.setLayoutX(645.0);
		dpLastVisit.setLayoutY(108.0);
		dpLastVisit.setPrefHeight(25.0);
		dpLastVisit.setPrefWidth(400.0);
		
		lblCreated.setLayoutX(510.0);
		lblCreated.setLayoutY(151.0);
		lblCreated.setPrefHeight(17.0);
		lblCreated.setPrefWidth(100.0);
		lblCreated.setFont(fontLbls);
		
		dpCreated.setLayoutX(645.0);
		dpCreated.setLayoutY(147.0);
		dpCreated.setPrefHeight(25.0);
		dpCreated.setPrefWidth(400.0);
		dpCreated.setEditable(false);
		dpCreated.setDisable(true);
		
		lblUpdated.setLayoutX(510.0);
		lblUpdated.setLayoutY(189.0);
		lblUpdated.setPrefHeight(17.0);
		lblUpdated.setPrefWidth(150.0);
		lblUpdated.setFont(fontLbls);
		
		dpUpdated.setLayoutX(645.0);
		dpUpdated.setLayoutY(187.0);
		dpUpdated.setPrefHeight(25.0);
		dpUpdated.setPrefWidth(400.0);
		dpUpdated.setEditable(false);
		dpUpdated.setDisable(true);
		
//        if (control.getTable().getColumns().size() == 0) {
//            control.generatedTable();
//        }
//        
//        Node table = control.getTable();
//        table.setLayoutY(299.0);
//        table.prefHeight(469.0);
//        table.prefWidth(1066.0);
        
        formPane.getChildren().addAll(lblId, tfId, lblPatients, cbPatients, lblEmail, tfEmail, lblFullname, tfFullname, 
        		lblTelephoneNumber, tfTelephoneNumber, lblAddress, tfAddress, lblIdentificationDoc, cbIdentificationDoc, 
        		lblIdentificationNumber, tfIdentificationNumber, lblLastVisit, dpLastVisit, lblCreated, dpCreated, lblUpdated, dpUpdated, 
        		btnCreate, btnFind, btnUpdate, btnDelete, btnClear
//        		, table
        		);

        btnCreate.setOnAction((e) -> {
            control.create();
        });
        btnCreate.setLayoutX(15.0);
        btnCreate.setLayoutY(269.0);
        btnCreate.setFont(fontBtns);
		
        btnFind.setOnAction((e) -> {
            control.findByIdentificationNumber();
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

	public static void main(String[] args) {
		launch(args);
	}

	private void binding() {
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(cbPatients.valueProperty(), control.patientsIdProperty());
        Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(tfFullname.textProperty(), control.fullnameProperty());
        Bindings.bindBidirectional(tfTelephoneNumber.textProperty(), control.telephoneNumberProperty());
        Bindings.bindBidirectional(tfAddress.textProperty(), control.addressProperty());
        Bindings.bindBidirectional(cbIdentificationDoc.valueProperty(), control.identificationDocProperty());
        Bindings.bindBidirectional(tfIdentificationNumber.textProperty(), control.identificationNumberProperty());
        Bindings.bindBidirectional(dpLastVisit.valueProperty(), control.lastVisitProperty());
        Bindings.bindBidirectional(dpCreated.valueProperty(), control.createdProperty());
        Bindings.bindBidirectional(dpUpdated.valueProperty(), control.updatedProperty());
	}

}