package tilgungsplan;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateCalculator {

	private void setCalendarToLastDayOfMonth(long currentTime) {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		int lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

		date.setTime(lastDate);
		mycal.set(Calendar.DAY_OF_MONTH, lastDate);
	}

	private void setCalendarToLastDayOfNextMonth(Calendar mycal) {
		int lastDate;
		mycal.add(Calendar.MONTH, 1);
		lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		mycal.set(Calendar.DAY_OF_MONTH, lastDate);
	}
}
