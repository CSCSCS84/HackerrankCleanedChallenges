package tilgungsplan;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GermanDateFormater implements DateFormater {

	public String formateDate(Timestamp time) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time.getTime());
		String day;
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			day = "0"+cal.get(Calendar.DAY_OF_MONTH) + "";
		} else {
			day = cal.get(Calendar.DAY_OF_MONTH) + "";
		}
		String month;
		if (cal.get(Calendar.MONTH) +1< 10) {
			month = "0"+(cal.get(Calendar.MONTH)+1) + "";
		} else {
			month = (cal.get(Calendar.MONTH)+1) + "";
		}
		String year = cal.get(Calendar.YEAR) + "";

		return day + "." + month + "." + year;
	}
}
