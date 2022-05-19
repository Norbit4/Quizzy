package pl.norbit.gameclient.tasks;

import pl.norbit.gameclient.game.GameFrame;

public class UpdateTask implements Runnable{

    private final GameFrame gameFrame;
    private boolean c;

    public UpdateTask(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameFrame.revalidate();
            gameFrame.repaint();
        }
    }
}
