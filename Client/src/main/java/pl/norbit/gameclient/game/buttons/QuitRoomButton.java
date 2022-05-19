package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.game.panels.MenuPanel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;

public class QuitRoomButton extends JButton {

    public QuitRoomButton(GameFrame gameFrame, GameClient gameClient) {

        this.setBackground(GameUtil.RED_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setText("Quit");
        this.setFont(GameUtil.BUTTON_FONT);
        this.setSize(200, 40);
        this.setFocusPainted(false);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.RED_BUTTON_ENTER));

        this.addActionListener(e -> {

            gameClient.sendObject(new ObjectPacket(Channel.LEAVE_ROOM.name(), ""));
            gameFrame.updatePanel(new MenuPanel(gameFrame, gameClient));
        });
    }
}
