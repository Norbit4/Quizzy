package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;

public class StartGameButton extends JButton {

    public StartGameButton(GameFrame gameFrame, GameClient gameClient) {

        Font font = GameUtil.BUTTON_FONT;
        this.setBackground(GameUtil.GREEN_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("Start game");
        this.setFont(font);
        this.setSize(200, 40);
        this.setFocusPainted(false);
        this.setEnabled(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameClient.sendObject(new ObjectPacket(Channel.START_GAME.name(),""));
        });
    }
}
