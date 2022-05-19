package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.labels.MenuBarLabel;
import pl.norbit.gameclient.game.labels.PlayerCardLabel;
import pl.norbit.gameclient.packets.GamePacket;

import javax.swing.*;

public class LobbyPanel extends JPanel {

    public LobbyPanel(GameFrame gameFrame, GameClient gameClient, GamePacket gamePacket) {

        PlayerCardLabel.setY(60);

        MenuBarLabel menuBarLabel = new MenuBarLabel(gameFrame, gameClient, gamePacket);
        menuBarLabel.setLocation(30, GameUtil.SCREEN_HEIGHT - (menuBarLabel.getHeight()) - 30);

        gamePacket.getPlayers().forEach(playerPacket -> {
            this.add(new PlayerCardLabel(gameClient, playerPacket.getPlayerUUUD(),
                    gamePacket.getOwner().getPlayerUUUD(), playerPacket.getPlayerName()));
        });

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.add(menuBarLabel);
        this.setDoubleBuffered(true);
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setLayout(null);
    }
}
