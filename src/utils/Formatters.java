package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Formatters {

  public String dateToString(Date date) {
    // dd/MM/yyyy
    LocalDate fmt = LocalDate.ofInstant(
      date.toInstant(),
      ZoneId.systemDefault()
    );

    return (
      normalizeDateOutcome(fmt.getDayOfMonth()) +
      "/" +
      normalizeDateOutcome(fmt.getMonthValue()) +
      "/" +
      fmt.getYear()
    );
  }

  public String hourToString(Date date) {
    LocalTime fmt = LocalTime.ofInstant(
      date.toInstant(),
      ZoneId.systemDefault()
    );

    return (
      normalizeDateOutcome(fmt.getHour()) +
      ":" +
      normalizeDateOutcome(fmt.getMinute())
    );
  }

  public String normalizeDateOutcome(int n) {
    if (n < 10) {
      return "0" + n;
    }
    return Integer.toString(n);
  }

  public Date stringToDate(String date) {
    String split[] = date.split("/");
    int year = Integer.parseInt(split[2]);
    int month = Integer.parseInt(split[1]);
    int day = Integer.parseInt(split[0]);

    Date dt = Date.from(
      LocalDate
        .of(year, month, day)
        .atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant()
    );

    return dt;
  }

  public Date stringToTimeDate(String date, String schedule) {
    String splitDate[] = date.split("/");
    String splitHour[] = schedule.split(":");
    
    int year = Integer.parseInt(splitDate[2]);
    int month = Integer.parseInt(splitDate[1]);
    int day = Integer.parseInt(splitDate[0]);
    int hour = Integer.parseInt(splitHour[0]);
    int minutes = Integer.parseInt(splitHour[0]);

    Date dt = Date.from(
      LocalDate
        .of(year, month, day)
        .atTime(hour, minutes)
        .atZone(ZoneId.systemDefault())
        .toInstant()
    );
    return dt;
  }

  public String timeDateToString(Date date) {
    return dateToString(date) + " às " + hourToString(date);
  }

  public String BooleanToString(Boolean value) {
	  return value == true ? "Ativo" : "Inativo";
  }

  public boolean StringToBoolean(String value) {
	  if (value.equals("Ativo")) {
		  return true;
	  } else {
		  return false;
	  }
  }
 
}
