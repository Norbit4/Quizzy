package pl.norbit.gameclient.game.buttons;

import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class MinimizeButton extends JButton {

    public MinimizeButton(GameFrame gameFrame) {

        this.setBackground(GameUtil.GUI_BACKGROUND);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("-");
        this.setFont(new Font("Arial", Font.BOLD, 23));
        this.setSize(30, 30);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, Color.gray));

        this.addActionListener(e -> {
            gameFrame.setState(JFrame.ICONIFIED);
        });
    }
}
