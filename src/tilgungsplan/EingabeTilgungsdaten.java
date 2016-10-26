package tilgungsplan;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


class EingabeTilgungsdaten {
    public static void main(String[] args) {
        // Verpacke den auszuführenden Quellcode in ein eigenes
        // Runnable-Objekt, um diesen nachher im Event Dispatching
        // Thread ausführen zu können
        Runnable guiCreator = new Runnable() {
            public void run() {
                // Erstellt das Swing-Fenster
                JFrame fenster = new JFrame("Hallo Welt mit Swing");
                // Swing anweisen, das Programm zu beenden, wenn das Fenster
                // geschlossen wird
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Fügt den "Hallo Welt"-Text hinzu
                JLabel label = new JLabel("Hallo Welt");
                fenster.add(label);

                // Zeigt das Fenster an
                fenster.setSize(300, 200);
                fenster.setVisible(true);
            }
        };

        // Führe den obigen Quellcode im Event-Dispatch-Thread aus
        SwingUtilities.invokeLater(guiCreator);
    }
}