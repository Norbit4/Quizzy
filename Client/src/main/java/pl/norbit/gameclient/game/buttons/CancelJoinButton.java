package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.game.panels.MenuPanel;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;

public class CancelJoinButton extends JButton {

    public CancelJoinButton(GameFrame gameFrame, GameClient gameClient) {

        this.setBackground(GameUtil.RED_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setSize(300, 60);
        this.setText("Cancel");
        this.setFont(GameUtil.BUTTON_FONT);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.RED_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameFrame.updatePanel(new MenuPanel(gameFrame, gameClient));
        });
    }
}