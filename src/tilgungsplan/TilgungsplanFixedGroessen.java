package tilgungsplan;

import java.math.BigDecimal;

public class TilgungsplanFixedGroessen {

	BigDecimal rate;
	BigDecimal zinsen;
	int laufzeit;
	Tilgungsrhythmus tilgungsrhythmus;
	Rundungsregel rundungsregeln;

	public TilgungsplanFixedGroessen(BigDecimal rate, BigDecimal zinsen, int laufzeit, Tilgungsrhythmus intervall,
			Rundungsregel rundungsregeln) {
		super();
		this.rate = rate;
		this.zinsen = zinsen;
		this.laufzeit = laufzeit;
		this.tilgungsrhythmus = intervall;
		this.rundungsregeln = rundungsregeln;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getZinsen() {
		return zinsen;
	}

	public void setZinsen(BigDecimal zinsen) {
		this.zinsen = zinsen;
	}

	public int getLaufzeit() {
		return laufzeit;
	}

	public void setLaufzeit(int laufzeit) {
		this.laufzeit = laufzeit;
	}

	public Tilgungsrhythmus getTilgungsrhythmus() {
		return tilgungsrhythmus;
	}

	public void setTilgungsrhythmus(Tilgungsrhythmus tilgungsrhythmus) {
		this.tilgungsrhythmus = tilgungsrhythmus;
	}

	public Rundungsregel getRundungsregeln() {
		return rundungsregeln;
	}

	public void setRundungsregeln(Rundungsregel rundungsregeln) {
		this.rundungsregeln = rundungsregeln;
	}

}
