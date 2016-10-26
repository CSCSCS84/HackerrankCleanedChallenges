package tilgungsplan;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Tilgungsplaneintrag {

	private Timestamp date;
	private BigDecimal restschuld;
	private BigDecimal zinsbetrag;
	private BigDecimal tilgungsbetrag;
	private BigDecimal rate;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public BigDecimal getRestschuld() {
		return restschuld;
	}

	public void setRestschuld(BigDecimal restschuld) {
		this.restschuld = restschuld;
	}

	public BigDecimal getZinsbetrag() {
		return zinsbetrag;
	}

	public void setZinsbetrag(BigDecimal zinsbetrag) {
		this.zinsbetrag = zinsbetrag;
	}

	public BigDecimal getTilgungsbetrag() {
		return tilgungsbetrag;
	}

	public void setTilgungsbetrag(BigDecimal tilgungsbetrag) {
		this.tilgungsbetrag = tilgungsbetrag;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String toString(OutputFormater outputFormater) {	
		DateFormater df=outputFormater.getDateFormater();
		BetragFormater bf=outputFormater.getBetragFormater();
		return df.formateDate(date) + " " + bf.formatBetrag(restschuld) + " "
				+ bf.formatBetrag(zinsbetrag) + " " + bf.formatBetrag(tilgungsbetrag)
				+ " " + bf.formatBetrag(rate);
	}

}
