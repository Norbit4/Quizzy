package pl.norbit.gameclient.tasks;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.panels.NickPanel;

public class Checker implements Runnable {
    private final GameClient gameClient;
    private final GameFrame gameFrame;

    public Checker(GameFrame gameFrame, GameClient gameClient) {
        this.gameClient = gameClient;
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        int i=0;
        boolean c = true;

        while (c) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!gameClient.isConnected()) {
                gameFrame.updatePanel(new NickPanel(gameFrame, gameClient));
                c = false;
            }
        }
    }
}
