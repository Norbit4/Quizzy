package pl.norbit.gameclient.game;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.panels.EndGamePanel;
import pl.norbit.gameclient.packets.GamePacket;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        GameClient gameClient = new GameClient();

        GameInfo.setPlayerUUID(UUID.randomUUID());

        GamePacket gamePacket = new GamePacket("");

        GamePacket.PlayerPacket g1 =  new GamePacket.PlayerPacket(UUID.randomUUID());
        gamePacket.addPlayerPacket(g1);
        g1.setPlayerName("Norbit4");
        g1.addPoint();

        GamePacket.PlayerPacket g2 =  new GamePacket.PlayerPacket(UUID.randomUUID());
        g2.setPlayerName("Zioomkox");
        gamePacket.addPlayerPacket(g2);
        g2.addPoint();

        GameFrame gameFrame = new GameFrame();
        gameFrame.openGame();
        gameFrame.updatePanel(new EndGamePanel(gameFrame, gameClient, gamePacket));
    }
}
