package utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkShift {

  public List<String> workShift(
    int startHourWorkShift,
    int endingHourWorkShift,
    int appDuration
  ) {
    LocalTime workShift = LocalTime.of(startHourWorkShift, 0);
    List<String> workShiftList = new ArrayList<String>();
		workShiftList.add(workShift.toString());


    while (workShift.plusMinutes(appDuration).getHour() < endingHourWorkShift) {
      workShift = workShift.plusMinutes(appDuration);
      workShiftList.add(workShift.toString());
    }
    return workShiftList;
  }
}
