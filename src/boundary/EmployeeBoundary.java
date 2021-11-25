package boundary;

import control.EmployeeControl;
import entity.Employee;
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

public class EmployeeBoundary implements StrategyBoundary {

  private TextField tfId = new TextField();
  private TextField tfEmail = new TextField();
  private TextField tfUsername = new TextField();
  private TextField tfFullname = new TextField();
  private TextField tfTelephoneNumber = new TextField();
  private TextField tfBankDetails = new TextField();
  private DatePicker dpBirthDate = new DatePicker();
  private ComboBox<String> cbActive = new ComboBox<>();
  private ComboBox<String> cbSpecialty = new ComboBox<>();

  private Label lblId = new Label("Id");
  private Label lblActive = new Label("Status");
  private Label lblEmail = new Label("Email");
  private Label lblUsername = new Label("Username");
  private Label lblFullname = new Label("Nome");
  private Label lblTelephoneNumber = new Label("Telefone");
  private Label lblBankDetails = new Label("Dados Bancários");
  private Label lblSpecialty = new Label("Especialidade");
  private Label lblBirthDate = new Label("Data de Nascimento");

  private Button btnClear = new Button("Limpar");
  private Button btnUpdate = new Button("Atualizar");
  private Button btnFind = new Button("Pesquisar");
  private Button btnCreate = new Button("Adicionar");
  private Button btnDelete = new Button("Remover");
  private Button btnUpdateList = new Button("Atualizar Lista");

  private static EmployeeControl control = new EmployeeControl();
  private TableView<Employee> table = new TableView<Employee>();
  static AppointmentBoundary appointment = new AppointmentBoundary();

  public void generatedComboBox() {
    ObservableList<String> status = FXCollections.observableArrayList(
      "Ativo",
      "Inativo"
    );
    cbActive.setItems(status);

    ObservableList<String> specialty = FXCollections.observableArrayList(
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
  }

  public void generatedTable() {
    control.listAll();

    TableColumn<Employee, String> colActive = new TableColumn<>("Status");
    colActive.setCellValueFactory(
      employeeProp -> {
        Boolean active = employeeProp.getValue().getActive();
        return new ReadOnlyStringWrapper(control.activeBooleanToString(active));
      }
    );

    TableColumn<Employee, String> colEmail = new TableColumn<>("Email");
    colEmail.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("email")
    );

    TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
    colUsername.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("username")
    );

    TableColumn<Employee, String> colFullname = new TableColumn<>(
      "Nome Completo"
    );
    colFullname.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("fullname")
    );

    TableColumn<Employee, String> colTelephoneNumber = new TableColumn<>(
      "Telefone"
    );
    colTelephoneNumber.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("telephoneNumber")
    );

    TableColumn<Employee, String> colBankDetails = new TableColumn<>(
      "Dados Bancarios"
    );
    colBankDetails.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("bankDetails")
    );

    TableColumn<Employee, String> colSpecialty = new TableColumn<>(
      "Especialidade"
    );
    colSpecialty.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("specialty")
    );

    TableColumn<Employee, String> colBirthDate = new TableColumn<>(
      "Data de Nascimento"
    );
    colBirthDate.setCellValueFactory(
      employeeProp -> {
        Date birthDate = employeeProp.getValue().getBirthDate();
        return new ReadOnlyStringWrapper(control.dateToString(birthDate));
      }
    );

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
        colBirthDate
      );

    table.setItems(control.getListEmployees());

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
    formPane.setLayoutX(300.0);
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

    lblTelephoneNumber.setLayoutX(510.0);
    lblTelephoneNumber.setLayoutY(37.0);
    lblTelephoneNumber.setPrefHeight(17.0);
    lblTelephoneNumber.setPrefWidth(130.0);
    lblTelephoneNumber.setFont(fontLbls);

    tfTelephoneNumber.setLayoutX(645.0);
    tfTelephoneNumber.setLayoutY(33.0);
    tfTelephoneNumber.setPrefHeight(25.0);
    tfTelephoneNumber.setPrefWidth(400.0);
    tfTelephoneNumber.setFont(fontTf);

    lblBankDetails.setLayoutX(510.0);
    lblBankDetails.setLayoutY(75.0);
    lblBankDetails.setPrefHeight(17.0);
    lblBankDetails.setPrefWidth(150.0);
    lblBankDetails.setFont(fontLbls);

    tfBankDetails.setLayoutX(645.0);
    tfBankDetails.setLayoutY(71.0);
    tfBankDetails.setPrefHeight(25.0);
    tfBankDetails.setPrefWidth(400.0);
    tfBankDetails.setFont(fontTf);

    lblSpecialty.setLayoutX(510.0);
    lblSpecialty.setLayoutY(112.0);
    lblSpecialty.setPrefHeight(17.0);
    lblSpecialty.setPrefWidth(150.0);
    lblSpecialty.setFont(fontLbls);

    cbSpecialty.setLayoutX(645.0);
    cbSpecialty.setLayoutY(108.0);
    cbSpecialty.setPrefHeight(25.0);
    cbSpecialty.setPrefWidth(400.0);

    lblBirthDate.setLayoutX(510.0);
    lblBirthDate.setLayoutY(151.0);
    lblBirthDate.setPrefHeight(17.0);
    lblBirthDate.setPrefWidth(150.0);
    lblBirthDate.setFont(fontLbls);

    dpBirthDate.setLayoutX(645.0);
    dpBirthDate.setLayoutY(147.0);
    dpBirthDate.setPrefHeight(25.0);
    dpBirthDate.setPrefWidth(400.0);

    if (getTable().getColumns().size() == 0) {
      this.generatedTable();
      this.generatedComboBox();
    }

    formPane
      .getChildren()
      .addAll(
        lblId,
        tfId,
        lblActive,
        cbActive,
        lblEmail,
        tfEmail,
        lblUsername,
        tfUsername,
        lblFullname,
        tfFullname,
        lblTelephoneNumber,
        tfTelephoneNumber,
        lblBankDetails,
        tfBankDetails,
        lblSpecialty,
        cbSpecialty,
        lblBirthDate,
        dpBirthDate,
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
            "Selecione um funcionário!",
            "Erro",
            JOptionPane.ERROR_MESSAGE
          );
        } else {
          Alert alert = new Alert(
            Alert.AlertType.WARNING,
            "Você tem certeza que deseja remover o funcionário? ",
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
      cbActive.valueProperty(),
      control.activeProperty()
    );
    Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
    Bindings.bindBidirectional(
      tfUsername.textProperty(),
      control.usernameProperty()
    );
    Bindings.bindBidirectional(
      tfFullname.textProperty(),
      control.fullnameProperty()
    );
    Bindings.bindBidirectional(
      tfTelephoneNumber.textProperty(),
      control.telephoneNumberProperty()
    );
    Bindings.bindBidirectional(
      tfBankDetails.textProperty(),
      control.bankDetailsProperty()
    );
    Bindings.bindBidirectional(
      cbSpecialty.valueProperty(),
      control.specialtyProperty()
    );
    Bindings.bindBidirectional(
      dpBirthDate.valueProperty(),
      control.birthDateProperty()
    );
  }

  public TableView<Employee> getTable() {
    return table;
  }
}
