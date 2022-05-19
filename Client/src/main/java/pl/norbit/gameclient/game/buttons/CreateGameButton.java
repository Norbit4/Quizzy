package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;

public class CreateGameButton extends JButton {

    public CreateGameButton(GameFrame gameFrame, GameClient gameClient) {

        this.setBackground(new Color(90, 136, 108));
        this.setText("Create new room");

        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setAlignmentY(600);
        this.setFont(GameUtil.BUTTON_FONT);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameClient.sendObject(new ObjectPacket(Channel.CREATE_NEW_ROOM.name(),""));
        });
    }
}
