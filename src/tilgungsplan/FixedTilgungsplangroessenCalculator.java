package tilgungsplan;

import java.math.BigDecimal;

public class FixedTilgungsplangroessenCalculator {

	public TilgungsplanFixedGroessen calcTilgungsplanFixedGroessen(Tilgungsplangroessen tp) {
		int numberOfIntervalls = calcNumberOfIntervalls(tp);
		ZinsCalculator zinssatzcalculator = new ZinsCalculator(tp.getRundungsregel().zinsRunden);
		BigDecimal zinsen = zinssatzcalculator.calcZinssatzForTilgungsrhytmus(tp.getSollzins(),
				Tilgungsrhythmus.ONEMONTH);
		BigDecimal firstTilgung = calcFirstTilgung(tp);
		BigDecimal fixedRate = calcFixedRate(tp.getDarlehensbetrag(), zinsen, firstTilgung, tp);

		return new TilgungsplanFixedGroessen(fixedRate, zinsen, numberOfIntervalls, tp.getTilgungsrhythmus(),
				tp.getRundungsregel());
	}

	private BigDecimal calcFirstTilgung(Tilgungsplangroessen tilgungsplangroessen) {

		BigDecimal firstTilgung = zinssatzErsteTilgung(tilgungsplangroessen);

		BigDecimal tilgungsrate = tilgungsplangroessen.getDarlehensbetrag().multiply(firstTilgung);
		tilgungsrate = tilgungsrate.setScale(tilgungsplangroessen.getRundungsregel().tilgungsbetragRunden.getScale(),
				tilgungsplangroessen.getRundungsregel().tilgungsbetragRunden.getRoundingMode());
		tilgungsrate = tilgungsrate.negate();
		return tilgungsrate;
	}

	private BigDecimal calcFixedRate(BigDecimal betrag, BigDecimal zinsen, BigDecimal firstTilgung,
			Tilgungsplangroessen tilgungsplangroessen) {
		ZinsCalculator zinssatzcalculator = new ZinsCalculator(tilgungsplangroessen.getRundungsregel().zinsRunden);
		BigDecimal zinsBetrag = zinssatzcalculator.zinsbetrag(betrag, zinsen);
		BigDecimal fixedRate = firstTilgung.add(zinsBetrag);
		fixedRate = fixedRate.setScale(tilgungsplangroessen.getRundungsregel().tilgungsbetragRunden.getScale(),
				tilgungsplangroessen.getRundungsregel().tilgungsbetragRunden.getRoundingMode());

		return fixedRate;
	}

	private BigDecimal zinssatzErsteTilgung(Tilgungsplangroessen tp) {
		BigDecimal numberOfIntervalls = null;
		switch (tp.getTilgungsrhythmus()) {
		case ONEYEAR:
			numberOfIntervalls = tp.getTilgungAnfang();
			break;
		case SIXMONTH:
			numberOfIntervalls = tp.getTilgungAnfang().divide(new BigDecimal("2"),
					tp.getRundungsregel().zinsRunden.getMathContext());
			break;
		case THREEMONTH:
			numberOfIntervalls = tp.getTilgungAnfang().divide(new BigDecimal("4"),
					tp.getRundungsregel().zinsRunden.getMathContext());
			break;
		case ONEMONTH:
			numberOfIntervalls = tp.getTilgungAnfang().divide(new BigDecimal("12"),
					tp.getRundungsregel().zinsRunden.getMathContext());
			;
			break;
		}
		return numberOfIntervalls;

	}

	private int calcNumberOfIntervalls(Tilgungsplangroessen tp) {
		int numberOfIntervalls = 0;
		switch (tp.getTilgungsrhythmus()) {
		case ONEYEAR:
			numberOfIntervalls = tp.getZinsbindung();
			break;
		case SIXMONTH:
			numberOfIntervalls = tp.getZinsbindung() * 2;
			break;
		case THREEMONTH:
			numberOfIntervalls = tp.getZinsbindung() * 4;
			break;
		case ONEMONTH:
			numberOfIntervalls = tp.getZinsbindung() * 12;
			break;
		}

		return numberOfIntervalls;
	}

}
