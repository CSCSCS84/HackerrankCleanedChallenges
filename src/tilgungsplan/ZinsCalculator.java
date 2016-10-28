package tilgungsplan;

import java.math.BigDecimal;

public class ZinsCalculator {

	BigIntegerPrecision bigIntegerPrecision;

	public ZinsCalculator(BigIntegerPrecision bigIntegerPrecision) {
		super();
		this.bigIntegerPrecision = bigIntegerPrecision;
	}

	public BigDecimal calcZinssatzForTilgungsrhytmus(BigDecimal zinssatzProJahr, Tilgungsrhythmus tilgungsrhytmus) {
		BigDecimal zinssatzProTilgungsrhytmus = null;

		switch (tilgungsrhytmus) {

		case ONEYEAR:
			zinssatzProTilgungsrhytmus = zinssatzProJahr;
			break;

		case SIXMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("2"),
					bigIntegerPrecision.getMathContext());
			break;

		case THREEMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("4"),
					bigIntegerPrecision.getMathContext());
			break;

		case ONEMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("12"),
					bigIntegerPrecision.getMathContext());
			break;
		}

		return zinssatzProTilgungsrhytmus;

	}

	public BigDecimal zinsbetrag(BigDecimal betrag, BigDecimal zinsen) {
		BigDecimal zinsBetrag = betrag.multiply(zinsen);
		zinsBetrag = zinsBetrag.negate(bigIntegerPrecision.getMathContext());
		zinsBetrag = zinsBetrag.setScale(bigIntegerPrecision.getScale(), bigIntegerPrecision.getRoundingMode());
		return zinsBetrag;
	}

	public BigIntegerPrecision getBigIntegerPrecision() {
		return bigIntegerPrecision;
	}

	public void setBigIntegerPrecision(BigIntegerPrecision bigIntegerPrecision) {
		this.bigIntegerPrecision = bigIntegerPrecision;
	}
}
