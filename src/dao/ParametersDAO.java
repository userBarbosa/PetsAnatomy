package dao;

public interface ParametersDAO {
	String dailyPhrase();
	boolean insertDailyPhrase(String phrase, int day);
}