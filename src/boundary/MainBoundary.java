package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainBoundary extends Application implements EventHandler<ActionEvent> {
	
	private Button btnHome = new Button("PetsAnatomy");	
	private Button btnAgenda = new Button("Agenda");
	private Button btnPacientes = new Button("Pacientes");
	private Button btnClientes = new Button("Clientes");
	private Button btnFuncionarios = new Button("Funcionários");
	private Button btnConfiguracoes = new Button("Configurações");
	private Button btnSair = new Button("Sair");
  
//  private ScreenStrategy screenSignUp = new SignUpBoundary();
//  private ScreenStrategy screenLogin = new LoginBoundary();
	
	Map<String, StrategyBoundary> boundaries = new HashMap<>();
    
    private static AnchorPane mainPane = new AnchorPane();
    
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

		AnchorPane sideMenu = new AnchorPane();

		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);

		sideMenu.prefHeight(768.0);
		sideMenu.prefWidth(300.0);
		sideMenu.setStyle("-fx-background-color: #000E44;");
		
		btnHome.setMinSize(300.0, 55.0);
		btnHome.setFont(fontBtnHome);
	    btnHome.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
		btnHome.setOnAction(this);
	    
	    btnAgenda.setLayoutY(110.0);
	    btnAgenda.setMinSize(300.0, 50.0);
	    btnAgenda.setFont(fontBtns);
	    btnAgenda.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnAgenda.setOnAction(this);
	    
	    btnPacientes.setLayoutY(180.0);
	    btnPacientes.setMinSize(300.0, 50.0);
	    btnPacientes.setFont(fontBtns);
	    btnPacientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnPacientes.setOnAction(this);
	    
	    btnClientes.setLayoutY(250.0);
	    btnClientes.setMinSize(300.0, 50.0);
	    btnClientes.setFont(fontBtns);
	    btnClientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnClientes.setOnAction(this);
	    
	    btnFuncionarios.setLayoutY(320.0);
	    btnFuncionarios.setMinSize(300.0, 50.0);
	    btnFuncionarios.setFont(fontBtns);
	    btnFuncionarios.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnFuncionarios.setOnAction(this);
	    
	    btnConfiguracoes.setLayoutY(390.0);
	    btnConfiguracoes.setMinSize(300.0, 50.0);
	    btnConfiguracoes.setFont(fontBtns);
	    btnConfiguracoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnConfiguracoes.setOnAction(this);
	    
	    btnSair.setLayoutY(718.0);
	    btnSair.setMinSize(300.0, 50.0);
	    btnSair.setFont(fontBtns);
	    btnSair.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    btnSair.setOnAction((e) -> {
			Platform.exit();
			System.exit(0);
		});
		
	    sideMenu.getChildren().addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnFuncionarios, btnConfiguracoes, btnSair);

		mainPane.setLeftAnchor(sideMenu, 0.0);
		mainPane.getChildren().add(sideMenu);
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);
	    
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent e) {
		EventTarget target = e.getTarget();
        if (target instanceof Button) {
        	Button button = (Button) target;
            String text = button.getText();
            StrategyBoundary boundary = boundaries.get(text);
            mainPane.setRightAnchor(boundary.generateBoundaryStrategy(), 0.0);
            mainPane.getChildren().clear();
            mainPane.getChildren().add(boundary.generateBoundaryStrategy());
        }
	}
	
    public static void main(String[] args) {
        Application.launch(MainBoundary.class, args);
    }

}
