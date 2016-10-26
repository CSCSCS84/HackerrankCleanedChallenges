package tilgungsplan;

import java.util.LinkedList;

public class Tilgungsplan {

	LinkedList<Tilgungsplaneintrag> tilgungsplaneintraege;

	public Tilgungsplan() {
		tilgungsplaneintraege = new LinkedList<>();
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

}
