package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.MongoConnect;

public class MainBoundary extends Application implements EventHandler<ActionEvent> {

	private RadioButton btnHome = new RadioButton("PetsAnatomy");	
	private RadioButton btnAgenda = new RadioButton("Agenda");
	private RadioButton btnPacientes = new RadioButton("Pacientes");
	private RadioButton btnClientes = new RadioButton("Clientes");
	private RadioButton btnFuncionarios = new RadioButton("Funcionários");
	private RadioButton btnConfiguracoes = new RadioButton("Configurações");
	private RadioButton btnSair = new RadioButton("Sair");

	//  private ScreenStrategy screenSignUp = new SignUpBoundary();
	//  private ScreenStrategy screenLogin = new LoginBoundary();

	StrategyBoundary dash = new DashboardBoundary();
	Map<String, StrategyBoundary> boundaries = new HashMap<>();

	private static AnchorPane mainPane = new AnchorPane();
	private static AnchorPane sideMenu = new AnchorPane();

	public MainBoundary() {
		boundaries.put("PetsAnatomy", new DashboardBoundary());
		boundaries.put("Agenda", new AppointmentBoundary());
		boundaries.put("Pacientes", new PatientBoundary());
		boundaries.put("Clientes", new OwnerBoundary());
		boundaries.put("Funcionários", new EmployeeBoundary());
		boundaries.put("Configurações", new ConfigurationBoundary()); 
	}

	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(mainPane, 1366, 768);
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 18);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);

		sideMenu.prefHeight(768.0);
		sideMenu.prefWidth(300.0);
		sideMenu.setStyle("-fx-background-color: #000E44;");
		
		ToggleGroup group = new ToggleGroup();
		
		btnHome.setLayoutX(0.0);
		btnHome.setMinSize(300.0, 55.0);
		btnHome.setFont(fontBtnHome);
		btnHome.setOnAction(this);
		btnHome.setToggleGroup(group);
		btnHome.setSelected(true);
		btnHome.setAlignment(Pos.CENTER);

		btnAgenda.setLayoutY(110.0);
		btnAgenda.setMinSize(300.0, 50.0);
		btnAgenda.setFont(fontBtns);
		btnAgenda.setOnAction(this);
		btnAgenda.setToggleGroup(group);
		btnAgenda.setAlignment(Pos.CENTER);

		btnPacientes.setLayoutY(180.0);
		btnPacientes.setMinSize(300.0, 50.0);
		btnPacientes.setFont(fontBtns);
		btnPacientes.setOnAction(this);
		btnPacientes.setToggleGroup(group);
		btnPacientes.setAlignment(Pos.CENTER);
		
		btnClientes.setLayoutY(250.0);
		btnClientes.setMinSize(300.0, 50.0);
		btnClientes.setFont(fontBtns);
		btnClientes.setOnAction(this);
		btnClientes.setToggleGroup(group);
		btnClientes.setAlignment(Pos.CENTER);
		
		btnFuncionarios.setLayoutY(320.0);
		btnFuncionarios.setMinSize(300.0, 50.0);
		btnFuncionarios.setFont(fontBtns);
		btnFuncionarios.setOnAction(this);
		btnFuncionarios.setToggleGroup(group);
		btnFuncionarios.setAlignment(Pos.CENTER);
		
		btnConfiguracoes.setLayoutY(390.0);
		btnConfiguracoes.setMinSize(300.0, 50.0);
		btnConfiguracoes.setFont(fontBtns);
		btnConfiguracoes.setOnAction(this);
		btnConfiguracoes.setToggleGroup(group);
		btnConfiguracoes.setAlignment(Pos.CENTER);
		
		btnSair.setLayoutY(718.0);
		btnSair.setMinSize(300.0, 50.0);
		btnSair.setFont(fontBtns);
		btnSair.setOnAction((e) -> {
			System.out.println("Bye (=");
			Platform.exit();
			System.exit(0);
		});
		btnSair.setToggleGroup(group);
		btnSair.setAlignment(Pos.CENTER);
		
		sideMenu.getChildren().addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnFuncionarios, btnConfiguracoes, btnSair);
		mainPane.setLeftAnchor(sideMenu, 0.0);
		mainPane.setRightAnchor(dash.generateBoundaryStrategy(), 0.0);
		mainPane.getChildren().addAll(sideMenu, dash.generateBoundaryStrategy());
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);

		sideMenu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());;
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		EventTarget target = e.getTarget();
		if (target instanceof RadioButton) {
			RadioButton radioButton = (RadioButton) target;
			String text = radioButton.getText();
			StrategyBoundary boundary = boundaries.get(text);
			mainPane.getChildren().clear();
			mainPane.setRightAnchor(boundary.generateBoundaryStrategy(), 0.0);
			mainPane.setLeftAnchor(sideMenu, 0.0);
			mainPane.getChildren().addAll(sideMenu, boundary.generateBoundaryStrategy());
			if(!radioButton.isSelected()) {
				radioButton.setSelected(true);    
			}
		}
	}

	public static void main(String[] args) {
		MongoConnect mc = new MongoConnect();
		mc.connection();
		Application.launch(MainBoundary.class, args);
	}

}
