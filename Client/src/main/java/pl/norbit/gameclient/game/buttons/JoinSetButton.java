package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;

public class JoinSetButton extends JButton {

    public JoinSetButton(GameFrame gameFrame, GameClient gameClient, JTextField jTextField) {

        this.setBackground(GameUtil.GREEN_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setSize(300,60);
        this.setText("Join");
        this.setFont(GameUtil.BUTTON_FONT);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));

        this.addActionListener(e -> {
            String token = jTextField.getText();

            gameClient.sendObject(new ObjectPacket(Channel.JOIN_TO_ROOM.name(), token));
        });
    }
}
