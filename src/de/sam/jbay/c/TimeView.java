package de.sam.jbay.c;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by samuel on 15.07.15.
 */
public class TimeView extends JLabel implements Runnable {
    public TimeView() {
        updateText();
        new Thread(this).start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    updateText();
                }
            });
        }
    }

    private void updateText() {
        setText(Calendar.getInstance().getTime().toString());
        revalidate();
        repaint();
    }
}