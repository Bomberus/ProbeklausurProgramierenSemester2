package de.sam.jbay.b;

import de.sam.jbay.a.Auktion;
import de.sam.jbay.a.Auktionshaus;
import de.sam.jbay.a.Bieter;
import de.sam.jbay.a.Ware;
import de.sam.jbay.c.BieterTerminal;

/**
 * Created by samuel on 15.07.15.
 */
public class jBay {
    public static void main(String[] args) {
        // Aufgabe b
        Auktionshaus jbay = new Auktionshaus();
        jbay.addAuktion(new Auktion(new Ware("Turnschuhe", "Tolle Turnschuhe, kaum getragen"), 2));
        jbay.addAuktion(new Auktion(new Ware("iPad", "Nagelneues iPad 3"), 4));
        jbay.addAuktion(new Auktion(new Ware("Currywurst", "Scharf, ohne Pommes"), 5));
        // Aufgabe c
        BieterTerminal b1 = new BieterTerminal(new Bieter("Micky", "Maus"), jbay);
        BieterTerminal b2 = new BieterTerminal(new Bieter("Donald", "Duck"), jbay);
    }
}