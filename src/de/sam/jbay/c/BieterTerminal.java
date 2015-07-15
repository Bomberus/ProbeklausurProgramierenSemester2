package de.sam.jbay.c;

import de.sam.jbay.a.Auktionshaus;
import de.sam.jbay.a.Bieter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by samuel on 15.07.15.
 */
public class BieterTerminal extends JFrame {
    private Bieter bieter;
    private Auktionshaus auktionshaus;

    private final AuktionenView auktionenView;

    public BieterTerminal(Bieter bieter, Auktionshaus auktionshaus) {
        this.bieter = bieter;
        this.auktionshaus = auktionshaus;
        this.setTitle(bieter.getFullName() + "'s Terminal");
        this.setSize(750, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.add(new TimeView(), BorderLayout.NORTH);

        auktionenView = new AuktionenView(bieter);
        this.add(auktionenView);
        updateAuktionen();

        this.setVisible(true);
        auktionshaus.register(this);
    }

    public void updateAuktionen() {
        auktionenView.setAuktionen(auktionshaus.getAuktionen());
        revalidate();
        repaint();
    }
}