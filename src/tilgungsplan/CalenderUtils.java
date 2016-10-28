package tilgungsplan;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderUtils {

	public static Timestamp calcEndOfNextMonth(long time) {
		Calendar cal=new GregorianCalendar();
		cal.setTimeInMillis(time+1);
	//	cal.add(Calendar.DAY_OF_MONTH, 1);
		int maxDay=cal.getMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, maxDay);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static Timestamp calcEndOfNextQuartal(long time) {
		Calendar cal=new GregorianCalendar();
		cal.setTimeInMillis(time);
		//cal.add(field, amount)
		Timestamp date = null;
		return date;
	}

	public static Timestamp calcEndOfNextHalfYear(long time) {
		Timestamp date = null;
		return date;
	}

	public static Timestamp calcEndOfNextYear(long time) {
		Timestamp date = null;
		return date;
	}
}
