package utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkShift {
	public List<String> workShift(
    int endingHourWorkShift,
    int startHourWorkShift,
    int appDuration
  ) {
    LocalTime workShift = LocalTime.of(startHourWorkShift, 0);
    List<String> workShiftList = new ArrayList<String>();

    while (workShift.plusMinutes(appDuration).getHour() < endingHourWorkShift) {
      workShift = workShift.plusMinutes(appDuration);
      workShiftList.add(workShift.toString());
    }
    return workShiftList;
  }
}
