package tilgungsplan;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TilgungsplanCalculator {
	private static int scaleForInput = 128;
	static MathContext mathContextForInput = new MathContext(scaleForInput, RoundingMode.HALF_DOWN);

	public static void main(String[] args) {

		TilgungsplanCalculator tc = new TilgungsplanCalculator();
		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(new BigDecimal("-100000",
				mathContextForInput), new BigDecimal("0.0212", mathContextForInput), new BigDecimal("0.02",
				mathContextForInput), (short) 10, Tilgungsrhythmus.ONEMONTH, new Rundungsregel());
		Tilgungsplan tp = tc.calcTilgungsplan(tilgungsplangroessen);
		OutputFormater outputFormater = new OutputFormater(new GermanDateFormater(), new GermanBetragFormater());
		System.out.println(tp.toString(outputFormater));

	}

	private Tilgungsplan calcTilgungsplan(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplan tilgungsplan = new Tilgungsplan();
		FixedTilgungsplangroessenCalculator fixedTilgungsplangroessenCalculator = new FixedTilgungsplangroessenCalculator();
		TilgungsplanFixedGroessen tfg = fixedTilgungsplangroessenCalculator
				.calcTilgungsplanFixedGroessen(tilgungsplangroessen);

		Tilgungsplaneintrag te = calcFirstTilgungsplaneintrag(tilgungsplangroessen);
		tilgungsplan.addTilgungsplaneintrag(te);

		for (int i = 1; i <= tfg.getLaufzeit(); i++) {
			te = calcTilgungsplanEintrag(te, tfg);
			tilgungsplan.addTilgungsplaneintrag(te);
		}

		return tilgungsplan;
	}

	private Tilgungsplaneintrag calcFirstTilgungsplaneintrag(Tilgungsplangroessen tilgungsplangroessen) {

		Calendar mycal = new GregorianCalendar();

		// setCalendarToLastDayOfMonth(mycal);
		Tilgungsplaneintrag tp = new Tilgungsplaneintrag(calcNextDate(System.currentTimeMillis(),
				tilgungsplangroessen.getTilgungsrhythmus()), tilgungsplangroessen.getDarlehensbetrag(), new BigDecimal(
				"0"), tilgungsplangroessen.getDarlehensbetrag(), tilgungsplangroessen.getDarlehensbetrag());
		return tp;
	}

	private Tilgungsplaneintrag calcTilgungsplanEintrag(Tilgungsplaneintrag te, TilgungsplanFixedGroessen tfg) {

		ZinsCalculator zinssatzCalculator = new ZinsCalculator(tfg.getRundungsregeln().zinsRunden);
		BigDecimal zinsBetrag = zinssatzCalculator.zinsbetrag(te.getRestschuld(), tfg.getZinsen());
		BigDecimal tilgungsBetrag = tfg.getRate().subtract(zinsBetrag);

		BigDecimal restSchuld = te.getRestschuld().setScale(tfg.getRundungsregeln().getRestSchuld().getScale(),
				tfg.getRundungsregeln().getRestSchuld().getRoundingMode());
		restSchuld = restSchuld.add(tilgungsBetrag, tfg.getRundungsregeln().getRestSchuld().getMathContext());
		tilgungsBetrag = tilgungsBetrag.setScale(tfg.getRundungsregeln().tilgungsbetragRunden.getScale(),
				tfg.getRundungsregeln().tilgungsbetragRunden.getScale());
		Timestamp nextDate = calcNextDate(te.getDate().getTime(), tfg.getTilgungsrhythmus());
		Tilgungsplaneintrag tp = new Tilgungsplaneintrag(nextDate, restSchuld, zinsBetrag, tilgungsBetrag,
				tfg.getRate());
		return tp;
	}

	private Timestamp calcNextDate(long time, Tilgungsrhythmus tilgungsrhythmus) {
		Timestamp date = null;
		switch (tilgungsrhythmus) {

		case ONEYEAR:
			date = CalenderUtils.calcEndOfNextYear(time);
			break;

		case SIXMONTH:
			date = CalenderUtils.calcEndOfNextHalfYear(time);
			break;

		case THREEMONTH:
			date = CalenderUtils.calcEndOfNextQuartal(time);
			break;

		case ONEMONTH:
			date = CalenderUtils.calcEndOfNextMonth(time);
			break;
		}
		return date;
	}

}
