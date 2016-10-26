package tilgungsplan;

public class OutputFormater {
	DateFormater dateFormater;
	BetragFormater betragFormater;

	public OutputFormater(DateFormater dateFormater, BetragFormater betragFormater) {
		super();
		this.dateFormater = dateFormater;
		this.betragFormater = betragFormater;
	}

	public DateFormater getDateFormater() {
		return dateFormater;
	}

	public void setDateFormater(DateFormater dateFormater) {
		this.dateFormater = dateFormater;
	}

	public BetragFormater getBetragFormater() {
		return betragFormater;
	}

	public void setBetragFormater(BetragFormater betragFormater) {
		this.betragFormater = betragFormater;
	}

}
