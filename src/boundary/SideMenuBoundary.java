package boundary;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SideMenuBoundary {

  private RadioButton btnHome = new RadioButton("PetsAnatomy");
  private RadioButton btnAgenda = new RadioButton("Agenda");
  private RadioButton btnPacientes = new RadioButton("Pacientes");
  private RadioButton btnClientes = new RadioButton("Clientes");
  private RadioButton btnFuncionarios = new RadioButton("Funcionários");
  private RadioButton btnConfiguracoes = new RadioButton("Configurações");
  private RadioButton btnSair = new RadioButton("Sair");

  private static AnchorPane sideMenu = new AnchorPane();

  static MainBoundary main = new MainBoundary();
  static StrategyBoundary dash = new DashboardBoundary();
  static StrategyBoundary appointment = new AppointmentBoundary();
  static StrategyBoundary patient = new PatientBoundary();
  static StrategyBoundary owner = new OwnerBoundary();
  static StrategyBoundary employee = new EmployeeBoundary();
  static StrategyBoundary configuration = new ConfigurationBoundary();

  public Pane generateSideMenuStrategy(String role) {
    Font fontBtnHome = Font.loadFont(
      "file:resources/fonts/Poppins-Bold.ttf",
      15
    );
    Font fontBtns = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      14
    );

    sideMenu.prefHeight(768.0);
    sideMenu.prefWidth(300.0);
    sideMenu.setStyle("-fx-background-color: #000E44;");

    ToggleGroup group = new ToggleGroup();

    switch (role) {
      case "admin":
        btnHome.setLayoutX(0.0);
        btnHome.setMinSize(300.0, 55.0);
        btnHome.setFont(fontBtnHome);
        btnHome.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              dash.generateBoundaryStrategy()
            );
            if (!btnHome.isSelected()) {
              btnHome.setSelected(true);
            }
          }
        );
        btnHome.setToggleGroup(group);
        btnHome.setSelected(true);
        btnHome.setAlignment(Pos.CENTER);

        btnAgenda.setLayoutY(110.0);
        btnAgenda.setMinSize(300.0, 50.0);
        btnAgenda.setFont(fontBtns);
        btnAgenda.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              appointment.generateBoundaryStrategy()
            );
            if (!btnAgenda.isSelected()) {
              btnAgenda.setSelected(true);
            }
          }
        );
        btnAgenda.setToggleGroup(group);
        btnAgenda.setAlignment(Pos.CENTER);

        btnPacientes.setLayoutY(180.0);
        btnPacientes.setMinSize(300.0, 50.0);
        btnPacientes.setFont(fontBtns);
        btnPacientes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              patient.generateBoundaryStrategy()
            );
            if (!btnPacientes.isSelected()) {
              btnPacientes.setSelected(true);
            }
          }
        );
        btnPacientes.setToggleGroup(group);
        btnPacientes.setAlignment(Pos.CENTER);

        btnClientes.setLayoutY(250.0);
        btnClientes.setMinSize(300.0, 50.0);
        btnClientes.setFont(fontBtns);
        btnClientes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              owner.generateBoundaryStrategy()
            );
            if (!btnClientes.isSelected()) {
              btnClientes.setSelected(true);
            }
          }
        );
        btnClientes.setToggleGroup(group);
        btnClientes.setAlignment(Pos.CENTER);

        btnFuncionarios.setLayoutY(320.0);
        btnFuncionarios.setMinSize(300.0, 50.0);
        btnFuncionarios.setFont(fontBtns);
        btnFuncionarios.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              employee.generateBoundaryStrategy()
            );
            if (!btnFuncionarios.isSelected()) {
              btnFuncionarios.setSelected(true);
            }
          }
        );
        btnFuncionarios.setToggleGroup(group);
        btnFuncionarios.setAlignment(Pos.CENTER);

        btnConfiguracoes.setLayoutY(390.0);
        btnConfiguracoes.setMinSize(300.0, 50.0);
        btnConfiguracoes.setFont(fontBtns);
        btnConfiguracoes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              configuration.generateBoundaryStrategy()
            );
            if (!btnConfiguracoes.isSelected()) {
              btnConfiguracoes.setSelected(true);
            }
          }
        );
        btnConfiguracoes.setToggleGroup(group);
        btnConfiguracoes.setAlignment(Pos.CENTER);

        btnSair.setLayoutY(718.0);
        btnSair.setMinSize(300.0, 50.0);
        btnSair.setFont(fontBtns);
        btnSair.setOnAction(
          e -> {
            System.out.println("Bye (=");
            Platform.exit();
            System.exit(0);
          }
        );
        btnSair.setToggleGroup(group);
        btnSair.setAlignment(Pos.CENTER);

        sideMenu
          .getChildren()
          .addAll(
            btnHome,
            btnAgenda,
            btnPacientes,
            btnClientes,
            btnFuncionarios,
            btnConfiguracoes,
            btnSair
          );
        sideMenu
          .getStylesheets()
          .add(getClass().getResource("Style.css").toExternalForm());

        return sideMenu;
      case "receptionist":
        btnHome.setLayoutX(0.0);
        btnHome.setMinSize(300.0, 55.0);
        btnHome.setFont(fontBtnHome);
        btnHome.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              dash.generateBoundaryStrategy()
            );
            if (!btnHome.isSelected()) {
              btnHome.setSelected(true);
            }
          }
        );
        btnHome.setToggleGroup(group);
        btnHome.setSelected(true);
        btnHome.setAlignment(Pos.CENTER);

        btnAgenda.setLayoutY(110.0);
        btnAgenda.setMinSize(300.0, 50.0);
        btnAgenda.setFont(fontBtns);
        btnAgenda.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              appointment.generateBoundaryStrategy()
            );
            if (!btnAgenda.isSelected()) {
              btnAgenda.setSelected(true);
            }
          }
        );
        btnAgenda.setToggleGroup(group);
        btnAgenda.setAlignment(Pos.CENTER);

        btnPacientes.setLayoutY(180.0);
        btnPacientes.setMinSize(300.0, 50.0);
        btnPacientes.setFont(fontBtns);
        btnPacientes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              patient.generateBoundaryStrategy()
            );
            if (!btnPacientes.isSelected()) {
              btnPacientes.setSelected(true);
            }
          }
        );
        btnPacientes.setToggleGroup(group);
        btnPacientes.setAlignment(Pos.CENTER);

        btnClientes.setLayoutY(250.0);
        btnClientes.setMinSize(300.0, 50.0);
        btnClientes.setFont(fontBtns);
        btnClientes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              owner.generateBoundaryStrategy()
            );
            if (!btnClientes.isSelected()) {
              btnClientes.setSelected(true);
            }
          }
        );
        btnClientes.setToggleGroup(group);
        btnClientes.setAlignment(Pos.CENTER);

        btnSair.setLayoutY(718.0);
        btnSair.setMinSize(300.0, 50.0);
        btnSair.setFont(fontBtns);
        btnSair.setOnAction(
          e -> {
            System.out.println("Bye (=");
            Platform.exit();
            System.exit(0);
          }
        );
        btnSair.setToggleGroup(group);
        btnSair.setAlignment(Pos.CENTER);

        sideMenu
          .getChildren()
          .addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnSair);
        sideMenu
          .getStylesheets()
          .add(getClass().getResource("Style.css").toExternalForm());
        return sideMenu;
      case "doctor":
        btnHome.setLayoutX(0.0);
        btnHome.setMinSize(300.0, 55.0);
        btnHome.setFont(fontBtnHome);
        btnHome.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              dash.generateBoundaryStrategy()
            );
            if (!btnHome.isSelected()) {
              btnHome.setSelected(true);
            }
          }
        );
        btnHome.setToggleGroup(group);
        btnHome.setSelected(true);
        btnHome.setAlignment(Pos.CENTER);

        btnAgenda.setLayoutY(110.0);
        btnAgenda.setMinSize(300.0, 50.0);
        btnAgenda.setFont(fontBtns);
        btnAgenda.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              appointment.generateBoundaryStrategy()
            );
            if (!btnAgenda.isSelected()) {
              btnAgenda.setSelected(true);
            }
          }
        );
        btnAgenda.setToggleGroup(group);
        btnAgenda.setAlignment(Pos.CENTER);

        btnPacientes.setLayoutY(180.0);
        btnPacientes.setMinSize(300.0, 50.0);
        btnPacientes.setFont(fontBtns);
        btnPacientes.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              patient.generateBoundaryStrategy()
            );
            if (!btnPacientes.isSelected()) {
              btnPacientes.setSelected(true);
            }
          }
        );
        btnPacientes.setToggleGroup(group);
        btnPacientes.setAlignment(Pos.CENTER);

        btnSair.setLayoutY(718.0);
        btnSair.setMinSize(300.0, 50.0);
        btnSair.setFont(fontBtns);
        btnSair.setOnAction(
          e -> {
            System.out.println("Bye (=");
            Platform.exit();
            System.exit(0);
          }
        );
        btnSair.setToggleGroup(group);
        btnSair.setAlignment(Pos.CENTER);

        sideMenu
          .getChildren()
          .addAll(btnHome, btnAgenda, btnPacientes, btnSair);
        sideMenu
          .getStylesheets()
          .add(getClass().getResource("Style.css").toExternalForm());
        return sideMenu;
      case "":
        btnHome.setLayoutX(0.0);
        btnHome.setMinSize(300.0, 55.0);
        btnHome.setFont(fontBtnHome);
        btnHome.setOnAction(
          e -> {
            main.setPaneLeftRightAnchor(
              sideMenu,
              dash.generateBoundaryStrategy()
            );
            if (!btnHome.isSelected()) {
              btnHome.setSelected(true);
            }
          }
        );
        btnHome.setToggleGroup(group);
        btnHome.setSelected(true);
        btnHome.setAlignment(Pos.CENTER);

        btnSair.setLayoutY(718.0);
        btnSair.setMinSize(300.0, 50.0);
        btnSair.setFont(fontBtns);
        btnSair.setOnAction(
          e -> {
            System.out.println("Bye (=");
            Platform.exit();
            System.exit(0);
          }
        );
        btnSair.setToggleGroup(group);
        btnSair.setAlignment(Pos.CENTER);

        sideMenu.getChildren().addAll(btnHome, btnSair);
        sideMenu
          .getStylesheets()
          .add(getClass().getResource("Style.css").toExternalForm());
        return sideMenu;
    }
    return null;
  }
}
