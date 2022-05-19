package pl.norbit.gameclient.game;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.panels.*;
import pl.norbit.gameclient.listeners.MessageEvent;
import pl.norbit.gameclient.tasks.UpdateTask;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;

public class GameFrame extends JFrame {

    public void openGame() {

        GameClient gameClient = new GameClient();
        gameClient.registerListener(new MessageEvent(this, gameClient));

        this.add(new NickPanel(this, gameClient));
        this.setVisible(true);
        this.setFocusable(true);
        //this.pack();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(GameUtil.LOGO.getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.dispose(); // Fixes the issue
        this.setUndecorated(true);
        this.setVisible(true);

        Thread updateThread = new Thread(new UpdateTask(this));
        updateThread.start();
    }

    public void updatePanel(JPanel panel){
        this.getContentPane().removeAll();
        this.add(panel);
        //SwingUtilities.updateComponentTreeUI(this);
    }

}
