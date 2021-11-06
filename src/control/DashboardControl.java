package control;

import dao.ParametersDAO;
import dao.ParametersDAOImpl;

public class DashboardControl {

  public void readPermissions(String role) {
    switch (role) {
      case "admin":
        // render all buttons
        break;
      case "doctor":
        // render patients, schedule
        break;
      case "receptionist": //assistant?
        // render clients, adoptions, schedule
        break;
      default:
        break;
    }
  }
	private ParametersDAO service = new ParametersDAOImpl();

	public String getDailyPhrase() {
		return service.dailyPhrase();
	}

	private void insertDailyPhrase() {
		service.insertDailyPhrase("Uma pata e uma mão estendida", 2);
	}

	public static void main(String[] args) {
		DashboardControl dc = new DashboardControl();

		dc.getDailyPhrase();
	}


}
