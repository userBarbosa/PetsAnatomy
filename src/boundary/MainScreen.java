package boundary;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainScreen extends Application implements PerformerActions {
	
	private Button btnHome = new Button("Pets Anatomy");	
	private Button btnAgenda = new Button("Agenda");
	private Button btnPacientes = new Button("Pacientes");
	private Button btnClientes = new Button("Clientes");
	private Button btnAdocoes = new Button("Adoções");
	private Button btnFuncionarios = new Button("Funcionários");
	private Button btnConfiguracoes = new Button("Configurações");
	private Button btnSair = new Button("Sair");
	
//    private ScreenStrategy screenDashboard = new DashboardBoundary();	
//    private ScreenStrategy screenAppointment = new AppointmentBoundary();
//    private ScreenStrategy screenPatient = new PatientBoundary();
//    private ScreenStrategy screenOwner = new OwnerBoundary();
//    private ScreenStrategy screenAdoption = new AdoptionBoundary();
//    private ScreenStrategy screenEmployee = new EmployeeBoundary();
//    private ScreenStrategy screenConfiguration = new ConfigurationBoundary();   
//    private ScreenStrategy screenSignUp = new SignUpBoundary();
//    private ScreenStrategy screenLogin = new LoginBoundary();
    
    private static AnchorPane mainPane = new AnchorPane();
	
	@Override
	public void start(Stage stage) throws Exception {
//		
//		Scene scene = new Scene(mainPane, 1366, 768);
//		
//		AnchorPane menu = new AnchorPane();
//		
//		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
//		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
//		
//		menu.prefHeight(768.0);
//		menu.prefWidth(300.0);
//		menu.setStyle("-fx-background-color: #000E44;");
//		
//		btnHome.setMinSize(300.0, 55.0);
//		btnHome.setFont(fontBtnHome);
//	    btnHome.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnAgenda.setLayoutY(110.0);
//	    btnAgenda.setMinSize(300.0, 50.0);
//	    btnAgenda.setFont(fontBtns);
//	    btnAgenda.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnPacientes.setLayoutY(180.0);
//	    btnPacientes.setMinSize(300.0, 50.0);
//	    btnPacientes.setFont(fontBtns);
//	    btnPacientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnClientes.setLayoutY(250.0);
//	    btnClientes.setMinSize(300.0, 50.0);
//	    btnClientes.setFont(fontBtns);
//	    btnClientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnAdocoes.setLayoutY(320.0);
//	    btnAdocoes.setMinSize(300.0, 50.0);
//	    btnAdocoes.setFont(fontBtns);
//	    btnAdocoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnFuncionarios.setLayoutY(390.0);
//	    btnFuncionarios.setMinSize(300.0, 50.0);
//	    btnFuncionarios.setFont(fontBtns);
//	    btnFuncionarios.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnConfiguracoes.setLayoutY(460.0);
//	    btnConfiguracoes.setMinSize(300.0, 50.0);
//	    btnConfiguracoes.setFont(fontBtns);
//	    btnConfiguracoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//	    
//	    btnSair.setLayoutY(718.0);
//	    btnSair.setMinSize(300.0, 50.0);
//	    btnSair.setFont(fontBtns);
//	    btnSair.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
//		
//	    menu.getChildren().addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnAdocoes, btnFuncionarios, btnConfiguracoes, btnSair);
//	    
//	    hideButtons();
//	    
//	    mainPane.setHgrow(menu, Priority.ALWAYS);
//	    mainPane.getChildren();
//	    mainPane.setHgrow(screenLogin.generateScreenStrategy(), Priority.ALWAYS);
//	    
//	    btnAgenda.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenAppointment.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//
//	    btnPacientes.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenPatient.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//
//	    btnClientes.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenOwner.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//
//	    btnAdocoes.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenAdoption.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//	    
//	    btnFuncionarios.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenEmployee.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//
//	    btnConfiguracoes.setOnAction((e) -> {
//	    	try {
//				mainPane.setHgrow(screenConfiguration.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        });
//
//        btnSair.setOnAction((e) -> {
//        	try {
//				mainPane.setHgrow(screenLogin.generateScreenStrategy(), Priority.ALWAYS);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//        	hideButtons();
//        });
//	    
//        stage.setTitle("Clínica Veterinária PetsAnatomy");
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
//		
	}
	
    public static void main(String[] args) {
        Application.launch(MainScreen.class, args);
    }
	
	@Override
	public void performerActionAdmin(String role, String username, String password, String action) throws FileNotFoundException {
//        if ("Admin".equals(role) && "Admin".equals(username) && "Admin".equals(password) && "Login".equals(action)) {
//        	mainPane.setHgrow(screenDashboard.generateScreenStrategy(), Priority.ALWAYS);
//            showButtons(role);
//        }
	}

	@Override
	public void performerActionEmployee(String role, String username, String password, String action) throws FileNotFoundException {
//        if ("Employee".equals(role) && "Employee".equals(username) && "Employee".equals(password) && "Login".equals(action)) {
//        	mainPane.setHgrow(screenDashboard.generateScreenStrategy(), Priority.ALWAYS);
//            showButtons(role);
//        }
	}

	@Override
	public void performerActionDoctor(String role, String username, String password, String action) throws FileNotFoundException {
//        if ("Doctor".equals(role) && "Doctor".equals(username) && "Doctor".equals(password) && "Login".equals(action)) {
//        	mainPane.setHgrow(screenDashboard.generateScreenStrategy(), Priority.ALWAYS);
//            showButtons(role);
//        }
	}
	
	private void hideButtons() {
		btnHome.setVisible(false);
		btnAgenda.setVisible(false);
		btnPacientes.setVisible(false);
		btnClientes.setVisible(false);
		btnAdocoes.setVisible(false);
		btnFuncionarios.setVisible(false);
		btnConfiguracoes.setVisible(false);
		btnSair.setVisible(false);		
	}
	
	private void showButtons(String role) {
        if (role.equals("Admin")) {
    		btnHome.setVisible(true);
    		btnAgenda.setVisible(true);
    		btnPacientes.setVisible(true);
    		btnClientes.setVisible(true);
    		btnAdocoes.setVisible(true);
    		btnFuncionarios.setVisible(true);
    		btnConfiguracoes.setVisible(true);
    		btnSair.setVisible(true);		
        } else if (role.equals("Employee")) {
    		btnHome.setVisible(true);
    		btnAgenda.setVisible(true);
    		btnPacientes.setVisible(true);
    		btnClientes.setVisible(true);
    		btnAdocoes.setVisible(true);
    		btnFuncionarios.setVisible(false);
    		btnConfiguracoes.setVisible(false);
    		btnSair.setVisible(true);	        	
        } else if (role.equals("Doctor")) {
    		btnHome.setVisible(true);
    		btnAgenda.setVisible(true);
    		btnPacientes.setVisible(true);
    		btnClientes.setVisible(false);
    		btnAdocoes.setVisible(false);
    		btnFuncionarios.setVisible(false);
    		btnConfiguracoes.setVisible(false);
    		btnSair.setVisible(true);	         	
        }
	}

}
