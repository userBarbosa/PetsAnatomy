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
}
