package boundary;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;

import control.OwnerControl;
import entity.Employee;
import entity.Owner;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
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

public class OwnerBoundary implements StrategyBoundary {

	private TextField tfId = new TextField(); 
	private TextField tfEmail = new TextField();
	private TextField tfFullname = new TextField();
	private TextField tfTelephoneNumber = new TextField();
	private TextField tfAddress = new TextField();
	private ComboBox<String> cbPatients = new ComboBox<>();
	private ComboBox<String> cbIdentificationDoc = new ComboBox<>();
	private TextField tfIdentificationNumber = new TextField();
	private DatePicker dpLastVisit = new DatePicker();

	private Label lblId = new Label("Id"); 
	private Label lblPatients = new Label("Pacientes");
	private Label lblEmail = new Label("Email");	
	private Label lblFullname = new Label("Nome");
	private Label lblTelephoneNumber = new Label("Telefone");
	private Label lblAddress = new Label("Endereço");
	private Label lblIdentificationDoc = new Label("Documento");
	private Label lblIdentificationNumber = new Label("Número Documento");
	private Label lblLastVisit = new Label("Última Consulta");

	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	private Button btnFind = new Button("Pesquisar");
	private Button btnCreate = new Button("Adicionar");
	private Button btnDelete = new Button("Remover");

	private static OwnerControl control = new OwnerControl();
	private TableView<Owner> table = new TableView<Owner>();

	public void generatedTable() {
		control.listAll();

		TableColumn<Owner, String> colFullname = new TableColumn<>("Nome Completo");
		colFullname.setCellValueFactory(new PropertyValueFactory<Owner, String>("fullname"));
		
		TableColumn<Owner, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Owner, String>("email"));
		
		TableColumn<Owner, ObjectId> colPatients = new TableColumn<>("Pacientes");
		colPatients.setCellValueFactory(new PropertyValueFactory<Owner, ObjectId>("patientsId"));

		TableColumn<Owner, String> colTelephoneNumber = new TableColumn<>("Telefone");
		colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<Owner, String>("telephoneNumber"));

		TableColumn<Owner, String> colAddress = new TableColumn<>("Endereço");
		colAddress.setCellValueFactory(new PropertyValueFactory<Owner, String>("address"));

		TableColumn<Owner, String> colIdentificationNumber = new TableColumn<>("Documento");
		colIdentificationNumber.setCellValueFactory(new PropertyValueFactory<Owner, String>("identificationNumber"));

		TableColumn<Owner, String> colLastVisit = new TableColumn<>("Última Consulta");
		colLastVisit.setCellValueFactory(new PropertyValueFactory<Owner, String>("lastVisit"));

		table
		.getColumns()
		.addAll(
				colPatients,
				colEmail,
				colFullname,
				colTelephoneNumber,
				colAddress,
				colIdentificationNumber,
				colLastVisit
				);
		
        table.setItems(control.getListOwners());

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
		formPane.setStyle("-fx-background-color: #ffffff;");
		formPane.setLayoutX(300.0);

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
		tfEmail.setFont(fontTf);

		lblFullname.setLayoutX(15.0);
		lblFullname.setLayoutY(151.0);
		lblFullname.setPrefHeight(17.0);
		lblFullname.setPrefWidth(100.0);
		lblFullname.setFont(fontLbls);

		tfFullname.setLayoutX(95.0);
		tfFullname.setLayoutY(148.0);
		tfFullname.setPrefHeight(25.0);
		tfFullname.setPrefWidth(400.0);
		tfFullname.setFont(fontTf);
		
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

		lblIdentificationNumber.setLayoutX(510.0);
		lblIdentificationNumber.setLayoutY(37.0);
		lblIdentificationNumber.setPrefHeight(17.0);
		lblIdentificationNumber.setPrefWidth(100.0);
		lblIdentificationNumber.setFont(fontLbls);

		tfIdentificationNumber.setLayoutX(645.0);
		tfIdentificationNumber.setLayoutY(33.0);
		tfIdentificationNumber.setPrefHeight(25.0);
		tfIdentificationNumber.setPrefWidth(400.0);

		lblLastVisit.setLayoutX(510.0);
		lblLastVisit.setLayoutY(75.0);
		lblLastVisit.setPrefHeight(17.0);
		lblLastVisit.setPrefWidth(150.0);
		lblLastVisit.setFont(fontLbls);

		dpLastVisit.setLayoutX(645.0);
		dpLastVisit.setLayoutY(71.0);
		dpLastVisit.setPrefHeight(25.0);
		dpLastVisit.setPrefWidth(400.0);

        if (getTable().getColumns().size() == 0) {
        	this.generatedTable();
        }
		
		formPane.getChildren().addAll(lblId, tfId, lblPatients, cbPatients, lblEmail, tfEmail, lblFullname, tfFullname, 
				lblTelephoneNumber, tfTelephoneNumber, lblAddress, tfAddress, lblIdentificationNumber, tfIdentificationNumber, 
				lblLastVisit, dpLastVisit, btnCreate, btnFind, btnUpdate, btnDelete, btnClear, table);
		
		btnCreate.setOnAction((e) -> {
			control.create();
		});
		btnCreate.setLayoutX(15.0);
		btnCreate.setLayoutY(269.0);
		btnCreate.setFont(fontBtns);

		btnFind.setOnAction((e) -> {
			control.findByField();
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
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(cbPatients.valueProperty(), control.patientsIdProperty());
        Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(tfFullname.textProperty(), control.fullnameProperty());
        Bindings.bindBidirectional(tfTelephoneNumber.textProperty(), control.telephoneNumberProperty());
        Bindings.bindBidirectional(tfAddress.textProperty(), control.addressProperty());
        Bindings.bindBidirectional(tfIdentificationNumber.textProperty(), control.identificationNumberProperty());
        Bindings.bindBidirectional(dpLastVisit.valueProperty(), control.lastVisitProperty());
	}

	public TableView<Owner> getTable() {
		return table;
	}

}