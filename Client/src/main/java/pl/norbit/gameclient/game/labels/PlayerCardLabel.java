package pl.norbit.gameclient.game.labels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class PlayerCardLabel extends JLabel {
    private static final int x;
    private static int y;

    static {
        x = 30;
    }

    public PlayerCardLabel(GameClient gameClient, UUID playerUUID, UUID ownerUUID, String playerNick) {

        Font nickFont = new Font("SansSerif",Font.BOLD, 26);
        Font kickButtonFont = new Font("SansSerif",Font.BOLD, 15);

        JButton kickButton = new JButton("Kick");
        kickButton.setVisible(true);
        kickButton.setSize(100, 30);
        kickButton.setLocation(350,25);
        kickButton.setBackground(GameUtil.RED_BUTTON);
        kickButton.setForeground(GameUtil.BUTTON_FOREGROUND);
        kickButton.setFont(kickButtonFont);
        kickButton.setFocusPainted(false);
        kickButton.addMouseListener(new ButtonAnimation(kickButton, GameUtil.RED_BUTTON_ENTER));

        kickButton.addActionListener(e -> {

            gameClient.sendObject(new ObjectPacket(Channel.KICK.name(), playerUUID.toString()));
        });

        if(playerUUID.equals(GameInfo.getPlayerUUID()) || !ownerUUID.equals(GameInfo.getPlayerUUID())){
            kickButton.setEnabled(false);
        }

        if(playerUUID.equals(ownerUUID)){
            Image image = GameUtil.LEADER_ICON.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel leaderIcon = new JLabel();
            leaderIcon.setIcon(imageIcon);
            leaderIcon.setSize(80, 80);
            leaderIcon.setHorizontalAlignment(CENTER);
            leaderIcon.setLocation(250,0);
            leaderIcon.setVisible(true);
            this.add(leaderIcon);
        }

        JLabel playerNickLabel = new JLabel();
        playerNickLabel.setSize(200, 80);
        playerNickLabel.setHorizontalAlignment(CENTER);
        playerNickLabel.setLocation(0,0);
        playerNickLabel.setFont(nickFont);
        playerNickLabel.setForeground(GameUtil.BUTTON_FOREGROUND);
        playerNickLabel.setText(playerNick);
        playerNickLabel.setVisible(true);

        this.setLocation(x, y);
        this.add(playerNickLabel);
        this.add(kickButton);
        this.setBackground(GameUtil.GUI_BACKGROUND);
        this.setVisible(true);
        this.setSize(new Dimension(500, 80));
        this.setOpaque(true);

        y += 110;
    }

    public static void setY(int y) {
        PlayerCardLabel.y = y;
    }
}
