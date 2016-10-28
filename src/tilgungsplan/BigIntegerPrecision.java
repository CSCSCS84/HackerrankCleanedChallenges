package tilgungsplan;

import java.math.MathContext;
import java.math.RoundingMode;

public class BigIntegerPrecision {

	private int scale;
	private MathContext mathContext;

	public BigIntegerPrecision(int scale, MathContext mathContext) {
		super();
		this.scale = scale;
		this.mathContext = mathContext;
	}

	public RoundingMode getRoundingMode() {
		return mathContext.getRoundingMode();
	}

	public int getPrecision() {
		return mathContext.getPrecision();
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public void setMathContext(MathContext mathContext) {
		this.mathContext = mathContext;
	}

}
