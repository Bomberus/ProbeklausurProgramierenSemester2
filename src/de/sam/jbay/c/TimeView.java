package de.sam.jbay.c;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

private boolean active = false;

public boolean isActive() {
    return active;
}

public void setActive(boolean active) {
    this.active = active;

    if (active) {
            new Thread(this).start();
    }
}

/**
 * Created by samuel on 15.07.15.
 */
public class TimeView extends JLabel implements Runnable {
    public TimeView() {
        updateText();
        //new Thread(this).start();
    }


    @Override
    public void run() {
        while (active) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            SwingUtilities.invokeLater(this::updateText); //Power of Java 8 :D
        }
    }

    private void updateText() {
        setText(Calendar.getInstance().getTime().toString());
        revalidate();
        repaint();
    }
}
