package boundary;

import control.PatientControl;
import entity.Patient;
import java.util.Date;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

public class PatientBoundary implements StrategyBoundary {

  private TextField tfId = new TextField();
  private TextField tfName = new TextField();
  private TextField tfObs = new TextField();
  private TextField tfBloodtype = new TextField();
  private TextField tfFamily = new TextField();
  private ComboBox<String> cbOwner = new ComboBox<>();
  private ComboBox<String> cbSpecies = new ComboBox<>();
  private ComboBox<String> cbTreatment = new ComboBox<>();
  private DatePicker dpBirthdate = new DatePicker();
  private TextField tfLastVisit = new TextField();

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

  private Button btnClear = new Button("Limpar");
  private Button btnUpdate = new Button("Atualizar");
  private Button btnFind = new Button("Pesquisar");
  private Button btnCreate = new Button("Adicionar");
  private Button btnDelete = new Button("Remover");
  private Button btnUpdateList = new Button("Atualizar Lista");

  private static PatientControl control = new PatientControl();
  private TableView<Patient> table = new TableView<Patient>();
  static AppointmentBoundary appointment = new AppointmentBoundary();

  public void generatedComboBox() {
    cbOwner.setItems(control.getAllIdAndNames());

    ObservableList<String> species = FXCollections.observableArrayList(
      "Peixe",
      "Réptil",
      "Ave",
      "Mamífero",
      "Anfíbio",
      "Porífero",
      "Cnidário",
      "Platelminto",
      "Molusco",
      "Artrópode",
      "Nematelminto",
      "Anelídeo",
      "Equinodermo"
    );
    cbSpecies.setItems(species);

    ObservableList<String> status = FXCollections.observableArrayList(
      "Sim",
      "Não"
    );
    cbTreatment.setItems(status);
  }

  public void generatedTable() {
    control.listAll();

    TableColumn<Patient, String> colName = new TableColumn<>("Nome");
    colName.setCellValueFactory(
      new PropertyValueFactory<Patient, String>("name")
    );

    TableColumn<Patient, String> colOwner = new TableColumn<>("Dono");
    colOwner.setCellValueFactory(
      patientProp -> {
        String ownerId = patientProp.getValue().getOwnerId().toString();
        return new ReadOnlyStringWrapper(control.getNameById(ownerId));
      }
    );

    TableColumn<Patient, String> colSpecies = new TableColumn<>("Espécie");
    colSpecies.setCellValueFactory(
      new PropertyValueFactory<Patient, String>("species")
    );

    TableColumn<Patient, String> colFamily = new TableColumn<>("Família");
    colFamily.setCellValueFactory(
      new PropertyValueFactory<Patient, String>("family")
    );

    TableColumn<Patient, String> colBloodtype = new TableColumn<>(
      "Tipo Sanguíneo"
    );
    colBloodtype.setCellValueFactory(
      new PropertyValueFactory<Patient, String>("bloodtype")
    );

    TableColumn<Patient, String> colBirthdate = new TableColumn<>(
      "Data de Nascimento"
    );
    colBirthdate.setCellValueFactory(
      patientProp -> {
        Date birthDate = patientProp.getValue().getBirthdate();
        return new ReadOnlyStringWrapper(control.dateToString(birthDate));
      }
    );

    TableColumn<Patient, String> colObs = new TableColumn<>("Observação");
    colObs.setCellValueFactory(
      new PropertyValueFactory<Patient, String>("obs")
    );

    TableColumn<Patient, String> colLastVisit = new TableColumn<>(
      "Última Consulta"
    );
    colLastVisit.setCellValueFactory(
      patientProp -> {
        Date lastVisit = patientProp.getValue().getLastVisit();
        return new ReadOnlyStringWrapper(control.timeDateToString(lastVisit));
      }
    );

    TableColumn<Patient, String> colTreatment = new TableColumn<>(
      "Em Tratamento"
    );
    colTreatment.setCellValueFactory(
      patientProp -> {
        Boolean treatment = patientProp.getValue().getTreatment();
        return new ReadOnlyStringWrapper(
          control.treatmentBooleanToString(treatment)
        );
      }
    );

    table
      .getColumns()
      .addAll(
        colName,
        colOwner,
        colSpecies,
        colFamily,
        colBloodtype,
        colBirthdate,
        colObs,
        colLastVisit,
        colTreatment
      );

    table.setItems(control.getListPatients());

    table
      .getSelectionModel()
      .selectedItemProperty()
      .addListener(
        (obs, older, newer) -> {
          if (newer != null) {
            control.setEntity(newer);
          }
        }
      );

    table.setLayoutY(305.0);
    table.setPrefHeight(469.0);
    table.setPrefWidth(1066.0);
  }

  @Override
  public Pane generateBoundaryStrategy() {
    AnchorPane formPane = new AnchorPane();

    Font fontBtns = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      12
    );
    Font fontLbls = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      12
    );
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

    tfFamily.setLayoutX(95.0);
    tfFamily.setLayoutY(187.0);
    tfFamily.setPrefHeight(25.0);
    tfFamily.setPrefWidth(400.0);
    tfFamily.setFont(fontTf);

    lblObs.setLayoutX(510.0);
    lblObs.setLayoutY(37.0);
    lblObs.setPrefHeight(17.0);
    lblObs.setPrefWidth(100.0);
    lblObs.setFont(fontLbls);

    tfObs.setLayoutX(645.0);
    tfObs.setLayoutY(33.0);
    tfObs.setPrefHeight(25.0);
    tfObs.setPrefWidth(400.0);
    tfObs.setFont(fontTf);

    lblBloodtype.setLayoutX(510.0);
    lblBloodtype.setLayoutY(75.0);
    lblBloodtype.setPrefHeight(17.0);
    lblBloodtype.setPrefWidth(150.0);
    lblBloodtype.setFont(fontLbls);

    tfBloodtype.setLayoutX(645.0);
    tfBloodtype.setLayoutY(71.0);
    tfBloodtype.setPrefHeight(25.0);
    tfBloodtype.setPrefWidth(400.0);

    lblBirthdate.setLayoutX(510.0);
    lblBirthdate.setLayoutY(112.0);
    lblBirthdate.setPrefHeight(17.0);
    lblBirthdate.setPrefWidth(150.0);
    lblBirthdate.setFont(fontLbls);

    dpBirthdate.setLayoutX(645.0);
    dpBirthdate.setLayoutY(108.0);
    dpBirthdate.setPrefHeight(25.0);
    dpBirthdate.setPrefWidth(400.0);

    lblLastVisit.setLayoutX(510.0);
    lblLastVisit.setLayoutY(151.0);
    lblLastVisit.setPrefHeight(17.0);
    lblLastVisit.setPrefWidth(100.0);
    lblLastVisit.setFont(fontLbls);

    tfLastVisit.setLayoutX(645.0);
    tfLastVisit.setLayoutY(147.0);
    tfLastVisit.setPrefHeight(25.0);
    tfLastVisit.setPrefWidth(400.0);
    tfLastVisit.setDisable(true);
    tfLastVisit.setEditable(false);

    lblTreatment.setLayoutX(510.0);
    lblTreatment.setLayoutY(189.0);
    lblTreatment.setPrefHeight(17.0);
    lblTreatment.setPrefWidth(100.0);
    lblTreatment.setFont(fontLbls);

    cbTreatment.setLayoutX(645.0);
    cbTreatment.setLayoutY(187.0);
    cbTreatment.setPrefHeight(25.0);
    cbTreatment.setPrefWidth(400.0);

    if (getTable().getColumns().size() == 0) {
      this.generatedTable();
      this.generatedComboBox();
    }

    formPane
      .getChildren()
      .addAll(
        lblId,
        tfId,
        lblOwner,
        cbOwner,
        lblName,
        tfName,
        lblSpecies,
        cbSpecies,
        lblFamily,
        tfFamily,
        lblBloodtype,
        tfBloodtype,
        lblBirthdate,
        dpBirthdate,
        lblObs,
        tfObs,
        lblLastVisit,
        tfLastVisit,
        lblTreatment,
        cbTreatment,
        btnCreate,
        btnFind,
        btnUpdate,
        btnDelete,
        btnClear,
        btnUpdateList,
        table
      );

    btnCreate.setOnAction(
      e -> {
        control.create();
        appointment.generatedComboBox();
      }
    );
    btnCreate.setLayoutX(15.0);
    btnCreate.setLayoutY(269.0);
    btnCreate.setFont(fontBtns);

    btnDelete.setOnAction(
      e -> {
        if (tfId.getText() == "" || tfId.getText() == null) {
          JOptionPane.showMessageDialog(
            null,
            "Selecione um paciente!",
            "Erro",
            JOptionPane.ERROR_MESSAGE
          );
        } else {
          Alert alert = new Alert(
            Alert.AlertType.WARNING,
            "Você tem certeza que deseja remover o paciente? ",
            ButtonType.YES,
            ButtonType.CANCEL
          );
          Optional<ButtonType> clicked = alert.showAndWait();
          if (clicked.isPresent() && clicked.get().equals(ButtonType.YES)) {
            control.deleteById();
          }
        }
        appointment.generatedComboBox();
      }
    );
    btnDelete.setLayoutX(105.0);
    btnDelete.setLayoutY(269.0);
    btnDelete.setFont(fontBtns);

    btnUpdate.setOnAction(
      e -> {
        control.updateById();
        appointment.generatedComboBox();
      }
    );
    btnUpdate.setLayoutX(196.0);
    btnUpdate.setLayoutY(269.0);
    btnUpdate.setFont(fontBtns);

    btnFind.setOnAction(
      e -> {
        control.findByField();
      }
    );
    btnFind.setLayoutX(281.0);
    btnFind.setLayoutY(269.0);
    btnFind.setFont(fontBtns);

    btnUpdateList.setOnAction(
      e -> {
        control.listAll();
      }
    );
    btnUpdateList.setLayoutX(380.0);
    btnUpdateList.setLayoutY(269.0);
    btnUpdateList.setFont(fontBtns);

    btnClear.setOnAction(
      e -> {
        control.clearFields();
      }
    );
    btnClear.setLayoutX(980.0);
    btnClear.setLayoutY(269.0);
    btnClear.setFont(fontBtns);

    return formPane;
  }

  private void binding() {
    Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
    Bindings.bindBidirectional(
      cbOwner.valueProperty(),
      control.ownerIdProperty()
    );
    Bindings.bindBidirectional(tfName.textProperty(), control.nameProperty());
    Bindings.bindBidirectional(
      cbSpecies.valueProperty(),
      control.speciesProperty()
    );
    Bindings.bindBidirectional(
      tfFamily.textProperty(),
      control.familyProperty()
    );
    Bindings.bindBidirectional(
      tfBloodtype.textProperty(),
      control.bloodtypeProperty()
    );
    Bindings.bindBidirectional(
      dpBirthdate.valueProperty(),
      control.birthdateProperty()
    );
    Bindings.bindBidirectional(tfObs.textProperty(), control.obsProperty());
    Bindings.bindBidirectional(
      tfLastVisit.textProperty(),
      control.lastVisitProperty()
    );
    Bindings.bindBidirectional(
      cbTreatment.valueProperty(),
      control.treatmentProperty()
    );
  }

  public TableView<Patient> getTable() {
    return table;
  }
}
