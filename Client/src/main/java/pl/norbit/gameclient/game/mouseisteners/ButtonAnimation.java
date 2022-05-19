package pl.norbit.gameclient.game.mouseisteners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonAnimation implements MouseListener {
    private final JButton button;
    private final Color normalColor,enteredColor;

    public ButtonAnimation(JButton button, Color enteredColor) {
        this.button = button;
        this.normalColor = button.getBackground();
        this.enteredColor = enteredColor;

        button.setBorder(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if(button.isEnabled()) {
            button.setBackground(enteredColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(normalColor);
    }
}
