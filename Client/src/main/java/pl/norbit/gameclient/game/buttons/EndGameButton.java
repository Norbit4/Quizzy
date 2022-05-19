package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;

public class EndGameButton extends JButton {

    public EndGameButton(GameClient gameClient) {

        this.setBackground(GameUtil.RED_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("Exit");
        this.setFont(GameUtil.BUTTON_FONT);
        this.setSize(200, 50);
        this.setFocusPainted(false);
        this.setBorder(null);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.RED_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameClient.sendObject(new ObjectPacket(Channel.EXIT_GAME.name(), ""));
        });
    }
}
