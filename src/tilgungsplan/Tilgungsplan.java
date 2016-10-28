package tilgungsplan;

import java.util.LinkedList;

public class Tilgungsplan {

	private LinkedList<Tilgungsplaneintrag> tilgungsplaneintraege;

	public Tilgungsplan() {
		super();
		this.tilgungsplaneintraege = new LinkedList<>();

	}

	public void addTilgungsplaneintrag(Tilgungsplaneintrag tilgungsplaneintrag) {
		tilgungsplaneintraege.add(tilgungsplaneintrag);
	}

	public String toString(OutputFormater outputFormater) {
		StringBuffer tilgungsplan = new StringBuffer();
		for (Tilgungsplaneintrag te : tilgungsplaneintraege) {
			tilgungsplan.append(te.toString(outputFormater) + "\n");
		}
		return tilgungsplan.toString();
	}

	public LinkedList<Tilgungsplaneintrag> getTilgungsplaneintraege() {
		return tilgungsplaneintraege;
	}

	public void setTilgungsplaneintraege(LinkedList<Tilgungsplaneintrag> tilgungsplaneintraege) {
		this.tilgungsplaneintraege = tilgungsplaneintraege;
	}

}
