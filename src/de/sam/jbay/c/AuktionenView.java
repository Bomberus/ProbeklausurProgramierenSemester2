package de.sam.jbay.c;

import de.sam.jbay.a.Auktion;
import de.sam.jbay.a.Bieter;
import de.sam.jbay.a.Gebot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by samuel on 15.07.15.
 */
public class AuktionenView extends JComponent {

    private Bieter bieter;

    public AuktionenView(Bieter bieter) {
        this.bieter = bieter;
    }

    public void setAuktionen(List<Auktion> auktionen) {
        removeAll();
        setLayout(new GridLayout(auktionen.size(), 5));
        for (final Auktion auktion : auktionen) {
            JLabel title = new JLabel(auktion.getWare().getTitel());
            title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(title);
            JLabel preis = new JLabel(String.valueOf(auktion.getAktuellerPreis()));
            preis.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(preis);
            if (auktion.getHoechstGebot() != null) {
                JLabel label = new JLabel(auktion.getHoechstGebot().getBieter().getFullName());
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                add(label);
            } else {
                JLabel label = new JLabel("---");
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                add(label);
            }
            JLabel ende = new JLabel(auktion.getEnde().getTime().toString());
            ende.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(ende);
            JButton gebotButton = new JButton("Gebot");
            gebotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    gebotAbgeben(auktion);
                }
            });
            add(gebotButton);
        }
        revalidate();
        repaint();
    }

    private void gebotAbgeben(Auktion auktion) {
        if (!auktion.isRunning()) {
            JOptionPane.showMessageDialog(this, "Die Auktion ist leider schon vorbei...", "Meldung", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double mindestGebot = auktion.getAktuellerPreis() + Auktion.increment;
        Object gebotObject = JOptionPane.showInputDialog(this, "Bitte ein neues Gebot eingeben.\nMindestens " + mindestGebot + " Euro", "Eingabe", JOptionPane.PLAIN_MESSAGE, null, null, mindestGebot);
        double betrag;
        try {
            betrag = Double.parseDouble(gebotObject.toString());
        } catch (Exception e) {
            betrag = 0;
        }
        if (auktion.gebotAbgeben(new Gebot(betrag, bieter))) {
            JOptionPane.showMessageDialog(this, "Sie sind Höchstbietender!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Gebot zu niedrig!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}