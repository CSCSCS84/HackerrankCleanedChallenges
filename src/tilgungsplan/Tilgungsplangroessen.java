package tilgungsplan;

import java.math.BigDecimal;

public class Tilgungsplangroessen {

	private BigDecimal darlehensbetrag;
	private BigDecimal sollzins;
	private BigDecimal tilgungAnfang;
	private short zinsbindung;

	public Tilgungsplangroessen(BigDecimal darlehensbetrag, BigDecimal sollzins, BigDecimal tilgungAnfang,
			short zinsbindung) {
		this.darlehensbetrag = darlehensbetrag;
		this.sollzins = sollzins;
		this.tilgungAnfang = tilgungAnfang;
		this.zinsbindung = zinsbindung;
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

	
	
}
