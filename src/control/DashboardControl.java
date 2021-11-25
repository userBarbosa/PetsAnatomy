package control;

import dao.impl.ParametersDAOImpl;
import dao.interfaces.ParametersDAO;

public class DashboardControl {
	
	private ParametersDAO service = new ParametersDAOImpl();

	public String getRandomPhrase() {
	    if (service.getRandomPhrase() != null) {
	        return service.getRandomPhrase();
	      }
	      return "Onde cuidar significa mais!";
	}
	
}
