package tilgungsplan;

import java.math.MathContext;
import java.math.RoundingMode;

public class Rundungsregel {

	BigIntegerPrecision tilgungsbetragRunden = new BigIntegerPrecision(2, new MathContext(128, RoundingMode.DOWN));

	BigIntegerPrecision rateRunden = new BigIntegerPrecision(2, new MathContext(128, RoundingMode.HALF_DOWN));

	BigIntegerPrecision zinsRunden = new BigIntegerPrecision(2, new MathContext(128, RoundingMode.HALF_DOWN));

	BigIntegerPrecision restSchuld = new BigIntegerPrecision(2, new MathContext(128, RoundingMode.HALF_DOWN));

	public BigIntegerPrecision getTilgungsbetragRunden() {
		return tilgungsbetragRunden;
	}

	public void setTilgungsbetragRunden(BigIntegerPrecision tilgungsbetragRunden) {
		this.tilgungsbetragRunden = tilgungsbetragRunden;
	}

	public BigIntegerPrecision getRateRunden() {
		return rateRunden;
	}

	public void setRateRunden(BigIntegerPrecision rateRunden) {
		this.rateRunden = rateRunden;
	}

	public BigIntegerPrecision getZinsRunden() {
		return zinsRunden;
	}

	public void setZinsRunden(BigIntegerPrecision zinsRunden) {
		this.zinsRunden = zinsRunden;
	}

	public BigIntegerPrecision getRestSchuld() {
		return restSchuld;
	}

	public void setRestSchuld(BigIntegerPrecision restSchuld) {
		this.restSchuld = restSchuld;
	}

}
