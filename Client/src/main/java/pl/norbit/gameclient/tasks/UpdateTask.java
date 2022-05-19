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
        boolean c = true;
        while (c) {
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
                c = false;
            }
            gameFrame.revalidate();
            gameFrame.repaint();
        }
    }
}
