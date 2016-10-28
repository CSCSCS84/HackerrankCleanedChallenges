package tilgungsplan;

import java.math.BigDecimal;

public class InputValidator {

	private Tilgungsplangroessen readInput(String[] input) {
		BigDecimal darlehensbetrag = new BigDecimal(input[0]);
		BigDecimal sollzins = new BigDecimal(input[1]);
		BigDecimal tilgungAnfang = new BigDecimal(input[2]);
		Short zinsbindung = Short.parseShort(input[3]);
		return new Tilgungsplangroessen(darlehensbetrag, sollzins, tilgungAnfang, zinsbindung, Tilgungsrhythmus.ONEMONTH);
	}
}
