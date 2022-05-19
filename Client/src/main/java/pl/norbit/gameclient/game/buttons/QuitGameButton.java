package pl.norbit.gameclient.game.buttons;


import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class QuitGameButton extends JButton {

    public QuitGameButton(GameClient gameClient) {

        this.setBackground(GameUtil.GUI_BACKGROUND);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("X");
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setSize(30, 30);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.RED_BUTTON_ENTER));

        this.addActionListener(e -> {
            System.exit(0);
        });
    }
}
