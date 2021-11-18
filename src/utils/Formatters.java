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

	public LocalTime stringToHour(String schedule){
		String splitHour[] = schedule.split(":");
		int hour = Integer.parseInt(splitHour[0]);
		int minutes = Integer.parseInt(splitHour[0]);
		LocalTime lt = LocalTime.of(hour, minutes);
		return lt;
	}

	public Date stringToTimeDate(String date, String schedule) {
		String splitDate[] = date.split("/");
		String splitHour[] = schedule.split(":");

		int day = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int year = Integer.parseInt(splitDate[2]);
		int hour = Integer.parseInt(splitHour[0]);
		int minutes = Integer.parseInt(splitHour[1]);

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

	public Date localToDate(LocalDate date){
		Date dt = Date.from(date
				.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant()
				);
		return dt;
	}

	public LocalDate dateToLocal(Date date) {
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	public String activeBooleanToString(Boolean value) {
		return value == true ? "Ativo" : "Inativo";
	}

	public boolean activeStringToBoolean(String value) {
		if (value.equals("Ativo")) {
			return true;
		} else {
			return false;
		}
	}

	public String treatmentBooleanToString(Boolean value) {
		return value == true ? "Sim" : "Não";
	}

	public boolean treatmentStringToBoolean(String value) {
		if (value.equals("Sim")) {
			return true;
		} else {
			return false;
		}
	}

	public int financialStateStringToInteger(String value) {
		if (value.equals("Pago")) {
			return 1;		 
		} else if (value.equals("Parcialmente pago")) {
			return 2;
		} else if (value.equals("Não pago")) {
			return 3;
		} else {
			// Cancelado
			return 4;
		}
	}

	public String financialStateIntegerToString(int value) {
		if (value == 1) {
			return "Pago";		 
		} else if (value == 2) {
			return "Parcialmente pago";
		} else if (value == 3) {
			return "Não pago";
		} else { 
			return "Cancelado";
		}
	}

	public int stateStringToInteger(String value) {
		if (value.equals("Agendado")) {
			return 1;		 
		} else if (value.equals("Encerrado")) {
			return 2;
		} else {
			// Cancelada
			return 3;
		}
	}

	public String stateIntegerToString(int value) {
		if (value == 1) {
			return "Agendado";		 
		} else if (value == 2) {
			return "Encerrado";
		} else { 
			return "Cancelada";
		}
	}

}
