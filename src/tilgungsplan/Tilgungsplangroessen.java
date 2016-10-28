package tilgungsplan;

import java.math.BigDecimal;

public class Tilgungsplangroessen {

	private BigDecimal darlehensbetrag;
	private BigDecimal sollzins;
	private BigDecimal tilgungAnfang;
	private short zinsbindung;
	private Tilgungsrhythmus tilgungsrhythmus;
	Rundungsregel rundungsregel;

	public Tilgungsplangroessen(BigDecimal darlehensbetrag, BigDecimal sollzins, BigDecimal tilgungAnfang,
			short zinsbindung, Tilgungsrhythmus tilgungsrhythmus,Rundungsregel rundungsregel) {
		super();
		this.darlehensbetrag = darlehensbetrag;
		this.sollzins = sollzins;
		this.tilgungAnfang = tilgungAnfang;
		this.zinsbindung = zinsbindung;
		this.tilgungsrhythmus = tilgungsrhythmus;
		this.rundungsregel=rundungsregel;
	}

	public BigDecimal getDarlehensbetrag() {
		return darlehensbetrag;
	}

	public void setDarlehensbetrag(BigDecimal darlehensbetrag) {
		this.darlehensbetrag = darlehensbetrag;
	}

	public BigDecimal getSollzins() {
		return sollzins;
	}

	public void setSollzins(BigDecimal sollzins) {
		this.sollzins = sollzins;
	}

	public BigDecimal getTilgungAnfang() {
		return tilgungAnfang;
	}

	public void setTilgungAnfang(BigDecimal tilgungAnfang) {
		this.tilgungAnfang = tilgungAnfang;
	}

	public short getZinsbindung() {
		return zinsbindung;
	}

	public void setZinsbindung(short zinsbindung) {
		this.zinsbindung = zinsbindung;
	}

	public Tilgungsrhythmus getTilgungsrhythmus() {
		return tilgungsrhythmus;
	}

	public void setTilgungsrhythmus(Tilgungsrhythmus tilgungsrhythmus) {
		this.tilgungsrhythmus = tilgungsrhythmus;
	}

	public Rundungsregel getRundungsregel() {
		return rundungsregel;
	}

	public void setRundungsregel(Rundungsregel rundungsregel) {
		this.rundungsregel = rundungsregel;
	}

}
