package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.game.panels.JoinPanel;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;

public class JoinRoomButton extends JButton {

    public JoinRoomButton(GameFrame gameFrame, GameClient gameClient) {

        this.setBackground(GameUtil.GREEN_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("Join to room");
        this.setFont(GameUtil.BUTTON_FONT);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameFrame.updatePanel(new JoinPanel(gameFrame, gameClient));
        });
    }
}

