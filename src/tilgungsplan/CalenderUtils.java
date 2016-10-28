package tilgungsplan;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderUtils {

	public static Timestamp calcEndOfNextMonth(long time) {
		Timestamp date = null;
		return date;
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
