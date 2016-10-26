package tilgungsplan;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TilgungsplanCalculator {
	private static int scale = 128;
	static MathContext mathContext = new MathContext(scale, RoundingMode.HALF_DOWN);
	private static int scale2 = 128;
	static MathContext mathContext2 = new MathContext(scale2, RoundingMode.HALF_DOWN);
	

	public static void main(String[] args) {
		TilgungsplanCalculator tc = new TilgungsplanCalculator();
		// Tilgungsplangroessen tilgungsplangroessen = tc.readInput(args);
		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(new BigDecimal("100000", mathContext),
				new BigDecimal("0.0212", mathContext), new BigDecimal("0.02", mathContext), (short) 10);
		Tilgungsplan tp = tc.calcTilgungsplan(tilgungsplangroessen);
		OutputFormater outputFormater = new OutputFormater(new GermanDateFormater(), new GermanBetragFormater());
		System.out.println(tp.toString(outputFormater));

		BigDecimal ersteRate = tc.calcFirstRate(tilgungsplangroessen);
		System.out.println(ersteRate.toString());

	}

	private Tilgungsplangroessen readInput(String[] input) {
		BigDecimal darlehensbetrag = new BigDecimal(input[0]);
		BigDecimal sollzins = new BigDecimal(input[1]);
		BigDecimal tilgungAnfang = new BigDecimal(input[2]);
		Short zinsbindung = Short.parseShort(input[3]);
		return new Tilgungsplangroessen(darlehensbetrag, sollzins, tilgungAnfang, zinsbindung);
	}

	private BigDecimal getFirstTilgung(Tilgungsplangroessen tilgungsplangroessen) {
		BigDecimal rate = tilgungsplangroessen.getDarlehensbetrag().multiply(tilgungsplangroessen.getTilgungAnfang())
				.divide(new BigDecimal("12"), mathContext);
		rate=rate.setScale(2, RoundingMode.DOWN);
		return rate;
	}

	private Tilgungsplan calcTilgungsplan(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplan tilgunsplan = new Tilgungsplan();
		Timestamp date = new Timestamp(System.currentTimeMillis());

		int anzahlMonate = tilgungsplangroessen.getZinsbindung() * 12;
		int countMonate = 0;
		BigDecimal restSchuld = tilgungsplangroessen.getDarlehensbetrag().negate(mathContext);
		BigDecimal zinsBetrag = zinsbetrag(restSchuld, tilgungsplangroessen);
		BigDecimal rate = getFirstTilgung(tilgungsplangroessen).add(zinsBetrag);

		Calendar mycal = new GregorianCalendar();

		int lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

		date.setTime(lastDate);
		mycal.set(Calendar.DAY_OF_MONTH, lastDate);
		while (countMonate <= anzahlMonate) {
			zinsBetrag = zinsbetrag(restSchuld, tilgungsplangroessen);
			BigDecimal tilgungsBetrag = rate.subtract(zinsBetrag, mathContext);
			restSchuld=restSchuld.setScale(2, RoundingMode.HALF_DOWN);
			tilgungsBetrag=tilgungsBetrag.setScale(2, RoundingMode.HALF_DOWN);
			restSchuld = restSchuld.add(tilgungsBetrag, mathContext);
			addTilgungsplaneintrag(tilgunsplan, restSchuld, zinsBetrag, rate, mycal, tilgungsBetrag);
			mycal.add(Calendar.MONTH, 1);
			lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			mycal.set(Calendar.DAY_OF_MONTH, lastDate);
			countMonate++;
		}

		return tilgunsplan;
	}

	private void addTilgungsplaneintrag(Tilgungsplan tilgunsplan, BigDecimal restSchuld, BigDecimal zinsBetrag,
			BigDecimal rate, Calendar mycal, BigDecimal tilgungsBetrag) {
		Tilgungsplaneintrag tilgungsplaneintrag = new Tilgungsplaneintrag();
		tilgungsplaneintrag.setDate(new Timestamp(mycal.getTimeInMillis()));
		
		tilgungsplaneintrag.setRestschuld(restSchuld);
		tilgungsplaneintrag.setZinsbetrag(zinsBetrag);
		tilgungsplaneintrag.setTilgungsbetrag(tilgungsBetrag);
		tilgungsplaneintrag.setRate(rate);

		tilgunsplan.addTilgungsplaneintrag(tilgungsplaneintrag);
	}

	private BigDecimal zinsbetrag(BigDecimal restSchuld, Tilgungsplangroessen tilgungsplangroessen) {

		BigDecimal zinsBetrag = restSchuld.multiply(tilgungsplangroessen.getSollzins()).divide(new BigDecimal("12"),
				mathContext2);
		zinsBetrag = zinsBetrag.negate(mathContext2);
		zinsBetrag=zinsBetrag.setScale(2, RoundingMode.HALF_DOWN);
		return zinsBetrag;
	}

	private BigDecimal calcFirstRate(Tilgungsplangroessen tilgungsplangroessen) {
		BigDecimal zinssatzMonatlich = tilgungsplangroessen.getSollzins().divide(new BigDecimal(12), mathContext);
		BigDecimal zinssatz = zinssatzMonatlich.add(new BigDecimal("1"));
		BigDecimal mult = new BigDecimal("1").subtract(zinssatz.pow(12));
		mult = mult.divide(zinssatzMonatlich.negate());

		BigDecimal rateErstesJahr = tilgungsplangroessen.getTilgungAnfang().multiply(
				tilgungsplangroessen.getDarlehensbetrag());
		BigDecimal ersteRate = rateErstesJahr.divide(mult, mathContext);

		return ersteRate;
	}

}
