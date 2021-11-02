package boundary;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import control.AppointmentControl;
import entity.Appointment;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AppointmentBoundary extends Application {
	
	private TextField tfId = new TextField(); 
	private TextField tfObs = new TextField();
	private TextField tfValue = new TextField();
	private DatePicker dpDate = new DatePicker();
	private ComboBox cbTime = new ComboBox();
	private ComboBox cbPatient = new ComboBox();
	private ComboBox cbOwner = new ComboBox();
	private ComboBox cbEmployee = new ComboBox();
	private ComboBox cbState = new ComboBox();
	private ComboBox cbFinancialState = new ComboBox();
	
	private Label lblId = new Label("Id"); 
	private Label lblDate = new Label("Data");
	private Label lblTime = new Label("Horário");	
	private Label lblPatient = new Label("Paciente");
	private Label lblOwner = new Label("Dono");
	private Label lblEmployee = new Label("Médico");
	private Label lblValue = new Label("Valor");
	private Label lblObs = new Label("Observação");
	private Label lblState = new Label("Status");
	private Label lblFinancialState = new Label("Pagamento");
	
	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	private Button btnFind = new Button("Pesquisar");
	private Button btnCreate = new Button("Adicionar");
	private Button btnDelete = new Button("Remover");
	
	private static AppointmentControl control = new AppointmentControl();

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
		
		lblId.setLayoutX(25.0);
		lblId.setLayoutY(32.0);
		lblId.setPrefHeight(17.0);
		lblId.setPrefWidth(20.0);
		lblId.setFont(fontLbls);
		
		tfId.setDisable(true);
		tfId.setEditable(false);
		tfId.setLayoutX(98.0);
		tfId.setLayoutY(32.0);
		tfId.setPrefHeight(25.0);
		tfId.setPrefWidth(414.0);
		tfId.setFont(fontTf);
		
		lblDate.setLayoutX(25.0);
		lblDate.setLayoutY(70.0);
		lblDate.setPrefHeight(17.0);
		lblDate.setPrefWidth(40.0);
		lblDate.setFont(fontLbls);
		
		dpDate.setLayoutX(98.0);
		dpDate.setLayoutY(66.0);
		dpDate.setPrefHeight(25.0);
		dpDate.setPrefWidth(414.0);
		
		lblTime.setLayoutX(24.0);
		lblTime.setLayoutY(107.0);
		lblTime.setPrefHeight(17.0);
		lblTime.setPrefWidth(46.0);
		lblTime.setFont(fontLbls);
		
		cbTime.setLayoutX(98.0);
		cbTime.setLayoutY(104.0);
		cbTime.setPrefHeight(25.0);
		cbTime.setPrefWidth(414.0);
		
		lblPatient.setLayoutX(25.0);
		lblPatient.setLayoutY(146.0);
		lblPatient.setPrefHeight(17.0);
		lblPatient.setPrefWidth(53.0);
		lblPatient.setFont(fontLbls);
		
		cbPatient.setLayoutX(98.0);
		cbPatient.setLayoutY(143.0);
		cbPatient.setPrefHeight(25.0);
		cbPatient.setPrefWidth(414.0);
		
		lblOwner.setLayoutX(25.0);
		lblOwner.setLayoutY(184.0);
		lblOwner.setPrefHeight(17.0);
		lblOwner.setPrefWidth(46.0);
		lblOwner.setFont(fontLbls);
		
		cbOwner.setLayoutX(98.0);
		cbOwner.setLayoutY(182.0);
		cbOwner.setPrefHeight(25.0);
		cbOwner.setPrefWidth(414.0);
		
		lblEmployee.setLayoutX(540.0);
		lblEmployee.setLayoutY(32.0);
		lblEmployee.setPrefHeight(17.0);
		lblEmployee.setPrefWidth(53.0);
		lblEmployee.setFont(fontLbls);
		
		cbEmployee.setLayoutX(620.0);
		cbEmployee.setLayoutY(32.0);
		cbEmployee.setPrefHeight(25.0);
		cbEmployee.setPrefWidth(414.0);
		
		lblValue.setLayoutX(540.0);
		lblValue.setLayoutY(70.0);
		lblValue.setPrefHeight(17.0);
		lblValue.setPrefWidth(46.0);
		lblValue.setFont(fontLbls);
		
		tfValue.setLayoutX(620.0);
		tfValue.setLayoutY(66.0);
		tfValue.setPrefHeight(25.0);
		tfValue.setPrefWidth(414.0);
		tfValue.setFont(fontTf);
		
		lblObs.setLayoutX(540.0);
		lblObs.setLayoutY(107.0);
		lblObs.setPrefHeight(17.0);
		lblObs.setPrefWidth(77.0);
		lblObs.setFont(fontLbls);
		
		tfObs.setLayoutX(620.0);
		tfObs.setLayoutY(104.0);
		tfObs.setPrefHeight(25.0);
		tfObs.setPrefWidth(414.0);
		tfObs.setFont(fontTf);
		
		lblState.setLayoutX(540.0);
		lblState.setLayoutY(146.0);
		lblState.setPrefHeight(17.0);
		lblState.setPrefWidth(40.0);
		lblState.setFont(fontLbls);
		
		cbState.setLayoutX(620.0);
		cbState.setLayoutY(143.0);
		cbState.setPrefHeight(25.0);
		cbState.setPrefWidth(414.0);
		
		lblFinancialState.setLayoutX(540.0);
		lblFinancialState.setLayoutY(184.0);
		lblFinancialState.setPrefHeight(17.0);
		lblFinancialState.setPrefWidth(77.0);
		lblFinancialState.setFont(fontLbls);
		
		cbFinancialState.setLayoutX(620.0);
		cbFinancialState.setLayoutY(180.0);
		cbFinancialState.setPrefHeight(25.0);
		cbFinancialState.setPrefWidth(414.0);
		
//        if (control.getTable().getColumns().size() == 0) {
//            control.generatedTable();
//        }
//        
//        Node table = control.getTable();
//        table.setLayoutY(299.0);
//        table.prefHeight(469.0);
//        table.prefWidth(1066.0);
        
        formPane.getChildren().addAll(lblId, tfId, lblDate, dpDate, lblTime, cbTime, lblPatient, cbPatient, 
        		lblOwner, cbOwner, lblEmployee, cbEmployee, lblState, cbState, lblFinancialState, 
        		cbFinancialState, lblValue, tfValue, lblObs, tfObs, 
        		btnCreate, btnFind, btnUpdate, btnDelete, btnClear
//        		, table
        		);

        btnCreate.setOnAction((e) -> {
            control.create();
        });
        btnCreate.setLayoutX(24.0);
        btnCreate.setLayoutY(246.0);
        btnCreate.setFont(fontBtns);
		
        btnFind.setOnAction((e) -> {
            control.findByDate();
        });
        btnFind.setLayoutX(290.0);
        btnFind.setLayoutY(246.0);
        btnFind.setFont(fontBtns);
		
        btnUpdate.setOnAction((e) -> {
            control.updateById();
        });
        btnUpdate.setLayoutX(205.0);
        btnUpdate.setLayoutY(246.0);
        btnUpdate.setFont(fontBtns);
        
        btnDelete.setOnAction((e) -> {
            control.deleteById();
        });
        btnDelete.setLayoutX(114.0);
        btnDelete.setLayoutY(246.0);
        btnDelete.setFont(fontBtns);
        
        btnClear.setOnAction((e) -> {
            control.clearFields();
        });
        btnClear.setLayoutX(459.0);
        btnClear.setLayoutY(246.0);
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
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(dpDate.valueProperty(), control.dateProperty());
        Bindings.bindBidirectional(cbTime.valueProperty(), control.timeProperty());
        Bindings.bindBidirectional(cbPatient.valueProperty(), control.patientIdProperty());
        Bindings.bindBidirectional(cbOwner.valueProperty(), control.ownerIdProperty());
        Bindings.bindBidirectional(cbEmployee.valueProperty(), control.employeeIdProperty());
        Bindings.bindBidirectional(cbState.valueProperty(), control.stateProperty());
        Bindings.bindBidirectional(cbFinancialState.valueProperty(), control.financialStateProperty());
        Bindings.bindBidirectional(tfValue.textProperty(), control.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(tfObs.textProperty(), control.obsProperty());
	}

	public static void main(String[] args) {
		launch(args);
	}

}