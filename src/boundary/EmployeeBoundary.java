package boundary;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import control.EmployeeControl;
import entity.Employee;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EmployeeBoundary implements StrategyBoundary {

	private TextField tfId = new TextField(); 
	private TextField tfEmail = new TextField();
	private TextField tfUsername = new TextField();
	private TextField tfFullname = new TextField();
	private TextField tfTelephoneNumber = new TextField();
	private TextField tfBankDetails = new TextField();
	private DatePicker dpBirthDate = new DatePicker();
	private DatePicker dpCreated = new DatePicker();	
	private ComboBox<String> cbActive = new ComboBox<>();
	private ComboBox<String> cbSpecialty = new ComboBox<>();
	private ComboBox<String> cbRole = new ComboBox<>();

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
	private TableView<Employee> table = new TableView<Employee>();
	
	public void generatedTable() {
        ObservableList<String> actives = FXCollections.observableArrayList("Ativo", "Inativo");
        cbActive.setItems(actives);
        cbActive.setValue("Selecione");
      
		TableColumn<Employee, String> colActive = new TableColumn<>("Status");
		colActive.setCellValueFactory(new PropertyValueFactory<Employee, String>("active"));

		TableColumn<Employee, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));

		TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
		colUsername.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));

		TableColumn<Employee, String> colFullname = new TableColumn<>("Nome Completo");
		colFullname.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullname"));

        ObservableList<String> roles = FXCollections.observableArrayList("admin", "receptionist", "doctor");
        cbRole.setItems(roles);
        cbRole.setValue("Selecione");
		
		TableColumn<Employee, String> colRole = new TableColumn<>("Role");
		colRole.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));

		TableColumn<Employee, String> colTelephoneNumber = new TableColumn<>("Telefone");
		colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephoneNumber"));

		TableColumn<Employee, String> colBankDetails = new TableColumn<>("Dados Bancarios");
		colBankDetails.setCellValueFactory(new PropertyValueFactory<Employee, String>("bankDetails"));
		
        ObservableList<String> specialty =
                FXCollections.observableArrayList(
                		"Acupunturista", 
                		"Cardiologista", 
                		"Comportamento animal",
                		"Dermatologista",
                		"Endocrinologista",
                		"Fisioterapeuta",
                		"Hematologista",
                		"Homeopata",
                		"Nefrologista",
                		"Neurologista",
                		"Nutrólogo",
                		"Dentista",
                		"Oftalmologista",
                		"Ortopedista",
                		"Pediatra"
                		);
        cbSpecialty.setItems(specialty);
        cbSpecialty.setValue("Selecione");

		TableColumn<Employee, String> colSpecialty = new TableColumn<>("Especialidade");
		colSpecialty.setCellValueFactory(new PropertyValueFactory<Employee, String>("specialty"));

		TableColumn<Employee, Date> colBirthDate = new TableColumn<>("Data de Nascimento");
		colBirthDate.setCellValueFactory(new PropertyValueFactory<Employee, Date>("birthDate"));

		TableColumn<Employee, Date> colCreated = new TableColumn<>("Data de Criação");
		colCreated.setCellValueFactory(new PropertyValueFactory<Employee, Date>("created"));

		table
		.getColumns()
		.addAll(
				colActive,
				colEmail,
				colUsername,
				colFullname,
				colTelephoneNumber,
				colBankDetails,
				colSpecialty,
				colBirthDate,
				colCreated,
				colRole
				);


        table.setItems(control.getListView());

        table
        .getSelectionModel()
        .selectedItemProperty()
        .addListener( (obs, older, newer) -> {
        	if (newer != null) {
        		control.setEntity(newer);
        	}
        });

		table.setLayoutY(305.0);
		table.setPrefHeight(469.0);
		table.setPrefWidth(1066.0);
	}
	
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

		cbSpecialty.setLayoutX(645.0);
		cbSpecialty.setLayoutY(71.0);
		cbSpecialty.setPrefHeight(25.0);
		cbSpecialty.setPrefWidth(400.0);

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
		
		formPane.getChildren().addAll(lblId, tfId, lblActive, cbActive, lblEmail, tfEmail, lblUsername, tfUsername, 
				lblFullname, tfFullname, lblTelephoneNumber, tfTelephoneNumber, lblBankDetails, tfBankDetails, lblSpecialty, 
				cbSpecialty, lblBirthDate, dpBirthDate, lblRole, cbRole, lblCreated, dpCreated,
				btnCreate, btnFind, btnUpdate, btnDelete, btnClear);
		
		this.generatedTable();		
		formPane.getChildren().add(table);

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
		
		return formPane;
	}

	private void binding() {
		Bindings.bindBidirectional(tfId.idProperty(), control.idProperty());
		Bindings.bindBidirectional(cbActive.valueProperty(), control.activeProperty());
		Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(tfUsername.textProperty(), control.usernameProperty());
		Bindings.bindBidirectional(tfFullname.textProperty(), control.fullnameProperty());
		Bindings.bindBidirectional(tfTelephoneNumber.textProperty(), control.telephoneNumberProperty());
		Bindings.bindBidirectional(tfBankDetails.textProperty(), control.bankDetailsProperty());
		Bindings.bindBidirectional(cbSpecialty.valueProperty(), control.specialtyProperty());
		Bindings.bindBidirectional(cbRole.valueProperty(), control.roleProperty());
		Bindings.bindBidirectional(dpBirthDate.valueProperty(), control.birthDateProperty());
		Bindings.bindBidirectional(dpCreated.valueProperty(), control.createdProperty());
		//The method bindBidirectional(Property<T>, Property<T>) in the type Bindings is not applicable for the arguments (ObjectProperty<LocalDate>, StringProperty)Java(67108979)
	}

}