package de.sam.jbay.a;

/**
 * Created by samuel on 15.07.15.
 */
public class Bieter {
    private String vorname;
    private String nachname;

    public Bieter(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFullName() {
        return vorname + " " + nachname;
    }
}