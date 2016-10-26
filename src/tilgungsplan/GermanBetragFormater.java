package tilgungsplan;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GermanBetragFormater implements BetragFormater {

	public String formatBetrag(BigDecimal number) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		df.setMinimumFractionDigits(2);
		df.setGroupingSize(3);

		df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.GERMANY));

		return df.format(number);
	}

}
