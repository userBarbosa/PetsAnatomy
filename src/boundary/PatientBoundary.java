package boundary;

import java.io.FileNotFoundException;

import control.AppointmentControl;
import control.PatientControl;
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
import javafx.util.converter.NumberStringConverter;

public class PatientBoundary implements StrategyBoundary {
	
	private TextField tfId = new TextField(); 
	private ComboBox cbOwner = new ComboBox();
	private TextField tfName = new TextField();
	private ComboBox cbSpecies = new ComboBox();
	private ComboBox cbFamily = new ComboBox();
	private ComboBox cbBloodtype = new ComboBox();
	private DatePicker dpBirthdate = new DatePicker();
	private TextField tfObs = new TextField();
	private DatePicker dpLastVisit = new DatePicker();
	private ComboBox cbTreatment = new ComboBox();
	private DatePicker dpCreated = new DatePicker();
	private DatePicker dpUpdated = new DatePicker();
	
	private Label lblId = new Label("Id"); 
	private Label lblOwner = new Label("Dono");
	private Label lblName = new Label("Nome");	
	private Label lblSpecies = new Label("Espécie");
	private Label lblFamily = new Label("Família");
	private Label lblBloodtype = new Label("Tipo Sanguíneo");
	private Label lblBirthdate = new Label("Data de Nascimento");
	private Label lblObs = new Label("Observação");
	private Label lblLastVisit = new Label("Última Consulta");
	private Label lblTreatment = new Label("Em Tratamento");
	private Label lblCreated = new Label("Criado Em");
	private Label lblUpdated = new Label("Última Atualizção");	
	
	private Button btnClear = new Button("Limpar");
	private Button btnUpdate = new Button("Atualizar");
	private Button btnFind = new Button("Pesquisar");
	private Button btnCreate = new Button("Adicionar");
	private Button btnDelete = new Button("Remover");
	
	private static PatientControl control = new PatientControl();

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
		
		lblOwner.setLayoutX(15.0);
		lblOwner.setLayoutY(75.0);
		lblOwner.setPrefHeight(17.0);
		lblOwner.setPrefWidth(40.0);
		lblOwner.setFont(fontLbls);
		
		cbOwner.setLayoutX(95.0);
		cbOwner.setLayoutY(71.0);
		cbOwner.setPrefHeight(25.0);
		cbOwner.setPrefWidth(400.0);
		
		lblName.setLayoutX(15.0);
		lblName.setLayoutY(112.0);
		lblName.setPrefHeight(17.0);
		lblName.setPrefWidth(46.0);
		lblName.setFont(fontLbls);
		
		tfName.setLayoutX(95.0);
		tfName.setLayoutY(109.0);
		tfName.setPrefHeight(25.0);
		tfName.setPrefWidth(400.0);
		tfName.setFont(fontTf);
		
		lblSpecies.setLayoutX(15.0);
		lblSpecies.setLayoutY(151.0);
		lblSpecies.setPrefHeight(17.0);
		lblSpecies.setPrefWidth(53.0);
		lblSpecies.setFont(fontLbls);
		
		cbSpecies.setLayoutX(95.0);
		cbSpecies.setLayoutY(148.0);
		cbSpecies.setPrefHeight(25.0);
		cbSpecies.setPrefWidth(400.0);
		
		lblFamily.setLayoutX(15.0);
		lblFamily.setLayoutY(189.0);
		lblFamily.setPrefHeight(17.0);
		lblFamily.setPrefWidth(46.0);
		lblFamily.setFont(fontLbls);
		
		cbFamily.setLayoutX(95.0);
		cbFamily.setLayoutY(187.0);
		cbFamily.setPrefHeight(25.0);
		cbFamily.setPrefWidth(400.0);
		
		lblObs.setLayoutX(15.0);
		lblObs.setLayoutY(230.0);
		lblObs.setPrefHeight(17.0);
		lblObs.setPrefWidth(77.0);
		lblObs.setFont(fontLbls);
		
		tfObs.setLayoutX(95.0);
		tfObs.setLayoutY(226.0);
		tfObs.setPrefHeight(25.0);
		tfObs.setPrefWidth(400.0);
		tfObs.setFont(fontTf);
		
		lblBloodtype.setLayoutX(510.0);
		lblBloodtype.setLayoutY(37.0);
		lblBloodtype.setPrefHeight(17.0);
		lblBloodtype.setPrefWidth(100.0);
		lblBloodtype.setFont(fontLbls);
		
		cbBloodtype.setLayoutX(645.0);
		cbBloodtype.setLayoutY(33.0);
		cbBloodtype.setPrefHeight(25.0);
		cbBloodtype.setPrefWidth(400.0);
		
		lblBirthdate.setLayoutX(510.0);
		lblBirthdate.setLayoutY(75.0);
		lblBirthdate.setPrefHeight(17.0);
		lblBirthdate.setPrefWidth(150.0);
		lblBirthdate.setFont(fontLbls);
		
		dpBirthdate.setLayoutX(645.0);
		dpBirthdate.setLayoutY(71.0);
		dpBirthdate.setPrefHeight(25.0);
		dpBirthdate.setPrefWidth(400.0);
		
		lblLastVisit.setLayoutX(510.0);
		lblLastVisit.setLayoutY(112.0);
		lblLastVisit.setPrefHeight(17.0);
		lblLastVisit.setPrefWidth(100.0);
		lblLastVisit.setFont(fontLbls);
		
		dpLastVisit.setLayoutX(645.0);
		dpLastVisit.setLayoutY(108.0);
		dpLastVisit.setPrefHeight(25.0);
		dpLastVisit.setPrefWidth(400.0);
		
		lblTreatment.setLayoutX(510.0);
		lblTreatment.setLayoutY(151.0);
		lblTreatment.setPrefHeight(17.0);
		lblTreatment.setPrefWidth(100.0);
		lblTreatment.setFont(fontLbls);
		
		cbTreatment.setLayoutX(645.0);
		cbTreatment.setLayoutY(147.0);
		cbTreatment.setPrefHeight(25.0);
		cbTreatment.setPrefWidth(400.0);
		
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
		
		lblUpdated.setLayoutX(510.0);
		lblUpdated.setLayoutY(230.0);
		lblUpdated.setPrefHeight(17.0);
		lblUpdated.setPrefWidth(120.0);
		lblUpdated.setFont(fontLbls);
		
		dpUpdated.setLayoutX(645.0);
		dpUpdated.setLayoutY(226.0);
		dpUpdated.setPrefHeight(25.0);
		dpUpdated.setPrefWidth(400.0);
		dpUpdated.setEditable(false);
		dpUpdated.setDisable(true);
		
        if (control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        
        Node table = control.getTable();
        table.setLayoutY(299.0);
        table.prefHeight(469.0);
        table.prefWidth(1066.0);
        
        formPane.getChildren().addAll(lblId, tfId, lblOwner, cbOwner, lblName, tfName, lblSpecies, cbSpecies, 
        		lblFamily, cbFamily, lblBloodtype, cbBloodtype, lblBirthdate, dpBirthdate, lblObs, 
        		tfObs, lblLastVisit, dpLastVisit, lblTreatment, cbTreatment, lblCreated, dpCreated,
        		lblUpdated, dpUpdated, btnCreate, btnFind, btnUpdate, btnDelete, btnClear
        		, table
        		);

        btnCreate.setOnAction((e) -> {
            control.create();
        });
        btnCreate.setLayoutX(15.0);
        btnCreate.setLayoutY(269.0);
        btnCreate.setFont(fontBtns);
		
        btnFind.setOnAction((e) -> {
            control.findByName();
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
        Bindings.bindBidirectional(cbOwner.valueProperty(), control.ownerIdProperty());
        Bindings.bindBidirectional(tfName.textProperty(), control.nameProperty());
        Bindings.bindBidirectional(cbSpecies.valueProperty(), control.speciesProperty());
        Bindings.bindBidirectional(cbFamily.valueProperty(), control.familyProperty());
        Bindings.bindBidirectional(cbBloodtype.valueProperty(), control.bloodtypeProperty());
        Bindings.bindBidirectional(dpBirthdate.valueProperty(), control.birthdateProperty());
        Bindings.bindBidirectional(tfObs.textProperty(), control.obsProperty());
        Bindings.bindBidirectional(dpLastVisit.valueProperty(), control.lastVisitProperty());
        Bindings.bindBidirectional(cbTreatment.valueProperty(), control.treatmentProperty());
        Bindings.bindBidirectional(dpCreated.valueProperty(), control.createdProperty());
        Bindings.bindBidirectional(dpUpdated.valueProperty(), control.updatedProperty());
	}

}