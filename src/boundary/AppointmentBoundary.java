package boundary;

import control.AppointmentControl;
import entity.Appointment;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import utils.WorkShift;

public class AppointmentBoundary implements StrategyBoundary {

  private TextField tfId = new TextField();
  private TextField tfObs = new TextField();
  private TextField tfValue = new TextField();
  private DatePicker dpDate = new DatePicker();
  private ComboBox<String> cbTime = new ComboBox<>();
  private ComboBox<String> cbPatient = new ComboBox<>();
  private ComboBox<String> cbOwner = new ComboBox<>();
  private ComboBox<String> cbEmployee = new ComboBox<>();
  private ComboBox<String> cbState = new ComboBox<>();
  private ComboBox<String> cbFinancialState = new ComboBox<>();

  private Label lblDateGte = new Label("Data Inicial");
  private Label lblDateLt = new Label("Data Final");
  private DatePicker dpDateGte = new DatePicker();
  private DatePicker dpDateLt = new DatePicker();
  private Button btnDismiss = new Button("Buscar");

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
  private Button btnFindByDate = new Button("Pesquisar por Data");
  private Button btnCreate = new Button("Adicionar");
  private Button btnDelete = new Button("Remover");
  private Button btnUpdateList = new Button("Atualizar Lista");

  Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
  Font fontLbls = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
  Font fontTf = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);

  private TableView<Appointment> table = new TableView<Appointment>();
  private static AppointmentControl control = new AppointmentControl();
  private WorkShift ws = new WorkShift();

  public void generatedComboBox() {
    ObservableList<String> timeOptions = FXCollections.observableArrayList(
      ws.workShift(8, 17, 30)
    );
    cbTime.setItems(timeOptions);

    cbOwner.setItems(control.getAllOwnerIdAndNames());

    cbOwner
      .valueProperty()
      .addListener(
        (obs, older, newer) -> {
          if (newer == null || newer == "") {
            cbPatient.setDisable(true);
            cbPatient.getItems().clear();
          } else {
            List<String> patientsByOwner = control.getPatientByOwnerName(newer);
            cbPatient.getItems().setAll(patientsByOwner);
            cbPatient.setDisable(false);
          }
        }
      );

    cbPatient.setDisable(true);

    cbEmployee.setItems(control.getAllEmployeeIdAndNames());

    ObservableList<String> states = FXCollections.observableArrayList(
      "Agendado",
      "Encerrado",
      "Cancelada"
    );
    cbState.setItems(states);

    ObservableList<String> financialStates = FXCollections.observableArrayList(
      "Pago",
      "Parcialmente pago",
      "Não pago",
      "Cancelado"
    );
    cbFinancialState.setItems(financialStates);
  }

  public void generatedTable() {
    control.listAll();

    TableColumn<Appointment, String> colDate = new TableColumn<>("Data");
    colDate.setCellValueFactory(
      appointmentProp -> {
        Date date = appointmentProp.getValue().getDate();
        return new ReadOnlyStringWrapper(control.dateToString(date));
      }
    );

    TableColumn<Appointment, String> colTime = new TableColumn<>("Horário");
    colTime.setCellValueFactory(
      appointmentProp -> {
        Date date = appointmentProp.getValue().getDate();
        return new ReadOnlyStringWrapper(control.hourToString(date));
      }
    );

    TableColumn<Appointment, String> colOwner = new TableColumn<>("Dono");
    colOwner.setCellValueFactory(
      appointmentProp -> {
        String ownerId = appointmentProp.getValue().getOwnerId().toString();
        return new ReadOnlyStringWrapper(control.getOwnerNameById(ownerId));
      }
    );

    TableColumn<Appointment, String> colPatient = new TableColumn<>("Paciente");
    colPatient.setCellValueFactory(
      appointmentProp -> {
        String patientId = appointmentProp.getValue().getPatientId().toString();
        return new ReadOnlyStringWrapper(control.getPatientNameById(patientId));
      }
    );

    TableColumn<Appointment, String> colEmployee = new TableColumn<>("Médico");
    colEmployee.setCellValueFactory(
      appointmentProp -> {
        String employeeId = appointmentProp
          .getValue()
          .getEmployeeId()
          .toString();
        return new ReadOnlyStringWrapper(
          control.getEmployeeNameById(employeeId)
        );
      }
    );

    TableColumn<Appointment, String> colState = new TableColumn<>("Status");
    colState.setCellValueFactory(
      appointmentProp -> {
        Integer state = appointmentProp.getValue().getState();
        return new ReadOnlyStringWrapper(control.stateIntegerToString(state));
      }
    );

    TableColumn<Appointment, String> colFinancialState = new TableColumn<>(
      "Pagamento"
    );
    colFinancialState.setCellValueFactory(
      appointmentProp -> {
        Integer financialState = appointmentProp.getValue().getFinancialState();
        return new ReadOnlyStringWrapper(
          control.financialStateIntegerToString(financialState)
        );
      }
    );

    TableColumn<Appointment, String> colValue = new TableColumn<>("Valor");
    colValue.setCellValueFactory(
      new PropertyValueFactory<Appointment, String>("value")
    );

    TableColumn<Appointment, String> colObs = new TableColumn<>("Observação");
    colObs.setCellValueFactory(
      new PropertyValueFactory<Appointment, String>("obs")
    );

    table
      .getColumns()
      .addAll(
        colDate,
        colTime,
        colEmployee,
        colOwner,
        colPatient,
        colState,
        colFinancialState,
        colValue,
        colObs
      );

    table.setItems(control.getListAppointments());

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

    binding();

    formPane.setPrefHeight(768.0);
    formPane.setPrefWidth(1066.0);
    formPane.setStyle("-fx-background-color: #ffffff;");
    formPane.setLayoutX(300);

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
    dpDate.setPrefWidth(170.0);

    lblTime.setLayoutX(280.0);
    lblTime.setLayoutY(70.0);
    lblTime.setPrefHeight(17.0);
    lblTime.setPrefWidth(46.0);
    lblTime.setFont(fontLbls);

    cbTime.setLayoutX(340.0);
    cbTime.setLayoutY(66.0);
    cbTime.setPrefHeight(25.0);
    cbTime.setPrefWidth(170.0);

    lblOwner.setLayoutX(25.0);
    lblOwner.setLayoutY(107.0);
    lblOwner.setPrefHeight(17.0);
    lblOwner.setPrefWidth(53.0);
    lblOwner.setFont(fontLbls);

    cbOwner.setLayoutX(98.0);
    cbOwner.setLayoutY(104.0);
    cbOwner.setPrefHeight(25.0);
    cbOwner.setPrefWidth(414.0);

    lblPatient.setLayoutX(25.0);
    lblPatient.setLayoutY(146.0);
    lblPatient.setPrefHeight(17.0);
    lblPatient.setPrefWidth(53.0);
    lblPatient.setFont(fontLbls);

    cbPatient.setLayoutX(98.0);
    cbPatient.setLayoutY(143.0);
    cbPatient.setPrefHeight(25.0);
    cbPatient.setPrefWidth(414.0);

    lblEmployee.setLayoutX(25.0);
    lblEmployee.setLayoutY(184.0);
    lblEmployee.setPrefHeight(17.0);
    lblEmployee.setPrefWidth(53.0);
    lblEmployee.setFont(fontLbls);

    cbEmployee.setLayoutX(98.0);
    cbEmployee.setLayoutY(182.0);
    cbEmployee.setPrefHeight(25.0);
    cbEmployee.setPrefWidth(414.0);

    lblValue.setLayoutX(540.0);
    lblValue.setLayoutY(32.0);
    lblValue.setPrefHeight(17.0);
    lblValue.setPrefWidth(46.0);
    lblValue.setFont(fontLbls);

    tfValue.setLayoutX(620.0);
    tfValue.setLayoutY(32.0);
    tfValue.setPrefHeight(25.0);
    tfValue.setPrefWidth(414.0);
    tfValue.setFont(fontTf);

    lblObs.setLayoutX(540.0);
    lblObs.setLayoutY(70.0);
    lblObs.setPrefHeight(17.0);
    lblObs.setPrefWidth(77.0);
    lblObs.setFont(fontLbls);

    tfObs.setLayoutX(620.0);
    tfObs.setLayoutY(66.0);
    tfObs.setPrefHeight(25.0);
    tfObs.setPrefWidth(414.0);
    tfObs.setFont(fontTf);

    lblState.setLayoutX(540.0);
    lblState.setLayoutY(107.0);
    lblState.setPrefHeight(17.0);
    lblState.setPrefWidth(40.0);
    lblState.setFont(fontLbls);

    cbState.setLayoutX(620.0);
    cbState.setLayoutY(104.0);
    cbState.setPrefHeight(25.0);
    cbState.setPrefWidth(414.0);

    lblFinancialState.setLayoutX(540.0);
    lblFinancialState.setLayoutY(146.0);
    lblFinancialState.setPrefHeight(17.0);
    lblFinancialState.setPrefWidth(77.0);
    lblFinancialState.setFont(fontLbls);

    cbFinancialState.setLayoutX(620.0);
    cbFinancialState.setLayoutY(143.0);
    cbFinancialState.setPrefHeight(25.0);
    cbFinancialState.setPrefWidth(414.0);

    if (getTable().getColumns().size() == 0) {
      this.generatedTable();
      this.generatedComboBox();
    }

    formPane
      .getChildren()
      .addAll(
        lblId,
        tfId,
        lblDate,
        dpDate,
        lblTime,
        cbTime,
        lblPatient,
        cbPatient,
        lblOwner,
        cbOwner,
        lblEmployee,
        cbEmployee,
        lblState,
        cbState,
        lblFinancialState,
        cbFinancialState,
        lblValue,
        tfValue,
        lblObs,
        tfObs,
        btnCreate,
        btnFind,
        btnFindByDate,
        btnUpdate,
        btnDelete,
        btnClear,
        btnUpdateList,
        table
      );

    btnCreate.setOnAction(
      e -> {
        control.create();
      }
    );
    btnCreate.setLayoutX(24.0);
    btnCreate.setLayoutY(246.0);
    btnCreate.setFont(fontBtns);

    btnDelete.setOnAction(
      e -> {
        if (tfId.getText() == "" || tfId.getText() == null) {
          JOptionPane.showMessageDialog(
            null,
            "Selecione uma consulta!",
            "Erro",
            JOptionPane.ERROR_MESSAGE
          );
        } else {
          Alert alert = new Alert(
            Alert.AlertType.WARNING,
            "Você tem certeza que deseja remover a consulta? ",
            ButtonType.YES,
            ButtonType.CANCEL
          );
          Optional<ButtonType> clicked = alert.showAndWait();
          if (clicked.isPresent() && clicked.get().equals(ButtonType.YES)) {
            control.deleteById();
          }
        }
      }
    );
    btnDelete.setLayoutX(114.0);
    btnDelete.setLayoutY(246.0);
    btnDelete.setFont(fontBtns);

    btnUpdate.setOnAction(
      e -> {
        control.updateById();
      }
    );
    btnUpdate.setLayoutX(205.0);
    btnUpdate.setLayoutY(246.0);
    btnUpdate.setFont(fontBtns);

    btnFind.setOnAction(
      e -> {
        control.findByField();
      }
    );
    btnFind.setLayoutX(290.0);
    btnFind.setLayoutY(246.0);
    btnFind.setFont(fontBtns);

    btnFindByDate.setOnAction(
      e -> {
        this.findByDatePopup();
      }
    );
    btnFindByDate.setLayoutX(380.0);
    btnFindByDate.setLayoutY(246.0);
    btnFindByDate.setFont(fontBtns);

    btnUpdateList.setOnAction(
      e -> {
        control.listAll();
      }
    );
    btnUpdateList.setLayoutX(540.0);
    btnUpdateList.setLayoutY(246.0);
    btnUpdateList.setFont(fontBtns);

    btnClear.setOnAction(
      e -> {
        control.clearFields();
      }
    );
    btnClear.setLayoutX(980.0);
    btnClear.setLayoutY(246.0);
    btnClear.setFont(fontBtns);

    return formPane;
  }

  private void findByDatePopup() {
    Stage popup = new Stage();
    popup.initModality(Modality.WINDOW_MODAL);

    AnchorPane pane = new AnchorPane();
    Scene scene = new Scene(pane, 420, 100);
    popup.setScene(scene);

    lblDateGte.setLayoutX(25.0);
    lblDateGte.setLayoutY(20.0);
    lblDateGte.setPrefHeight(17.0);
    lblDateGte.setPrefWidth(100.0);
    lblDateGte.setFont(fontLbls);

    dpDateGte.setLayoutX(110.0);
    dpDateGte.setLayoutY(17.0);
    dpDateGte.setPrefHeight(25.0);
    dpDateGte.setPrefWidth(100.0);

    lblDateLt.setLayoutX(220.0);
    lblDateLt.setLayoutY(20.0);
    lblDateLt.setPrefHeight(17.0);
    lblDateLt.setPrefWidth(100.0);
    lblDateLt.setFont(fontLbls);

    dpDateLt.setLayoutX(290.0);
    dpDateLt.setLayoutY(17.0);
    dpDateLt.setPrefHeight(25.0);
    dpDateLt.setPrefWidth(100.0);

    btnDismiss.setOnAction(
      e -> {
        control.findByDate(dpDateGte.getValue(), dpDateLt.getValue());
        popup.close();
        dpDateGte.setValue(null);
        dpDateLt.setValue(null);
      }
    );
    btnDismiss.setLayoutX(25.0);
    btnDismiss.setLayoutY(60.0);
    btnDismiss.setFont(fontBtns);

    pane
      .getChildren()
      .addAll(btnDismiss, lblDateGte, dpDateGte, lblDateLt, dpDateLt);

    popup.setTitle("Pesquisar consulta por data");
    popup.setResizable(false);
    popup.showAndWait();
  }

  private void binding() {
    Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
    Bindings.bindBidirectional(dpDate.valueProperty(), control.dateProperty());
    Bindings.bindBidirectional(cbTime.valueProperty(), control.timeProperty());
    Bindings.bindBidirectional(
      cbPatient.valueProperty(),
      control.patientIdProperty()
    );
    Bindings.bindBidirectional(
      cbOwner.valueProperty(),
      control.ownerIdProperty()
    );
    Bindings.bindBidirectional(
      cbEmployee.valueProperty(),
      control.employeeIdProperty()
    );
    Bindings.bindBidirectional(
      cbState.valueProperty(),
      control.stateProperty()
    );
    Bindings.bindBidirectional(
      cbFinancialState.valueProperty(),
      control.financialStateProperty()
    );
    Bindings.bindBidirectional(
      tfValue.textProperty(),
      control.valueProperty(),
      new NumberStringConverter()
    );
    Bindings.bindBidirectional(tfObs.textProperty(), control.obsProperty());
  }

  public TableView<Appointment> getTable() {
    return table;
  }
}
