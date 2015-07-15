package de.sam.jbay.a;

import de.sam.jbay.c.AuktionUpdateListener;
import de.sam.jbay.c.BieterTerminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.07.15.
 */
public class Auktionshaus implements AuktionUpdateListener {
    private List<Auktion> auktionen;
    private List<BieterTerminal> terminals;

    public Auktionshaus() {
        auktionen = new ArrayList<Auktion>();
        terminals = new ArrayList<BieterTerminal>();
    }

    public void addAuktion(Auktion auktion) {
        auktionen.add(auktion);
        auktion.setListener(this);
    }

    public void removeAuktion(Auktion auktion) {
        auktionen.remove(auktion);
    }

    public List<Auktion> getAuktionen() {
        return auktionen;
    }

    public void register(BieterTerminal terminal) {
        terminals.add(terminal);
    }

    public void unregister(BieterTerminal terminal) {
        terminals.remove(terminal);
    }

    @Override
    public void auktionUpdated() {
        for (BieterTerminal terminal : terminals) {
            terminal.updateAuktionen();
        }
    }
}