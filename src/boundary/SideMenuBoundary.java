package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SideMenuBoundary implements StrategyBoundary, EventHandler<ActionEvent> {

	private RadioButton btnHome = new RadioButton("PetsAnatomy");	
	private RadioButton btnAgenda = new RadioButton("Agenda");
	private RadioButton btnPacientes = new RadioButton("Pacientes");
	private RadioButton btnClientes = new RadioButton("Clientes");
	private RadioButton btnFuncionarios = new RadioButton("Funcionários");
	private RadioButton btnConfiguracoes = new RadioButton("Configurações");
	private RadioButton btnSair = new RadioButton("Sair");

	private static AnchorPane sideMenu = new AnchorPane();
	AnchorPane mainPane = MainBoundary.mainPane;
	Map<String, StrategyBoundary> boundaries = new HashMap<>();

	public SideMenuBoundary() {
		boundaries.put("PetsAnatomy", new DashboardBoundary());
		boundaries.put("Agenda", new AppointmentBoundary());
		boundaries.put("Pacientes", new PatientBoundary());
		boundaries.put("Clientes", new OwnerBoundary());
		boundaries.put("Funcionários", new EmployeeBoundary());
		boundaries.put("Configurações", new ConfigurationBoundary());
	}
	
	@Override
	public Pane generateBoundaryStrategy() {
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);

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

		return sideMenu;
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
}
