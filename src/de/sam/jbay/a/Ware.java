package de.sam.jbay.a;

/**
 * Created by samuel on 15.07.15.
 */
public class Ware {
    private String titel;
    private String beschreibung;

    public Ware(String titel, String beschreibung) {
        this.titel = titel;
        this.beschreibung = beschreibung;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}