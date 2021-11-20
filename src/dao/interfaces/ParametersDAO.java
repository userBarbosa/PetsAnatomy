package dao.interfaces;

public interface ParametersDAO {
	String dailyPhrase();
	boolean insertPhrase(String phrase, int day);
}
