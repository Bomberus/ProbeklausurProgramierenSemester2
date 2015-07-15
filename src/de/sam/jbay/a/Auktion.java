package de.sam.jbay.a;

import de.sam.jbay.c.AuktionUpdateListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;

/**
 * Created by samuel on 15.07.15.
 */
public class Auktion {
    public static final double increment = 1.0;

    private Ware ware;
    private Gebot hoechstGebot;
    private double aktuellerPreis;
    private Calendar ende;
    private AuktionUpdateListener listener;

    static {
        try {
            System.setOut(new PrintStream(new File("auktionen.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Auktion(Ware ware, int dauer) {
        this.ware = ware;
        this.ende = Calendar.getInstance();
        this.ende.setTimeInMillis(System.currentTimeMillis() + 60000 * dauer);
    }

    public boolean gebotAbgeben(Gebot gebot) {
        logGebot(gebot);
        if (gebot.getBetrag() < aktuellerPreis + increment) {
            return false;
        }
        if (hoechstGebot == null) {
            aktuellerPreis = increment;
            hoechstGebot = gebot;
            invokeListener();
            return true;
        }
        if (hoechstGebot.getBieter().equals(gebot.getBieter()) && gebot.getBetrag() > hoechstGebot.getBetrag()) {
            hoechstGebot = gebot;
            invokeListener();
            return true;
        }
        if (gebot.getBetrag() >= aktuellerPreis + increment) {
            if (gebot.getBetrag() > hoechstGebot.getBetrag()) {
                aktuellerPreis = Math.min(gebot.getBetrag(), hoechstGebot.getBetrag() + increment);
                hoechstGebot = gebot;
                invokeListener();
                return true;
            }
            aktuellerPreis = Math.min(gebot.getBetrag() + increment, hoechstGebot.getBetrag());
            invokeListener();
        }
        return false;
    }

    private void logGebot(Gebot gebot) {
        String time = Calendar.getInstance().getTime().toString();
        System.out.println("[" + time + "] Gebot von " + gebot.getBieter().getFullName() + " für " + ware.getTitel() + ": " + gebot.getBetrag() + " Euro.");
    }

    private void invokeListener() {
        if (listener != null) {
            listener.auktionUpdated();
        }
    }

    public Ware getWare() {
        return ware;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }

    public double getAktuellerPreis() {
        return aktuellerPreis;
    }

    public void setAktuellerPreis(double aktuellerPreis) {
        this.aktuellerPreis = aktuellerPreis;
    }

    public Gebot getHoechstGebot() {
        return hoechstGebot;
    }

    public void setHoechstGebot(Gebot hoechstGebot) {
        this.hoechstGebot = hoechstGebot;
    }

    public Calendar getEnde() {
        return ende;
    }

    public void setEnde(Calendar ende) {
        this.ende = ende;
    }

    public AuktionUpdateListener getListener() {
        return listener;
    }

    public void setListener(AuktionUpdateListener listener) {
        this.listener = listener;
    }

    public boolean isRunning() {
        return ende.after(Calendar.getInstance());
    }
}