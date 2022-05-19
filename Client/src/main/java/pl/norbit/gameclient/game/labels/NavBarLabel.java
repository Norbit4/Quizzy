package pl.norbit.gameclient.game.labels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.MinimizeButton;
import pl.norbit.gameclient.game.buttons.QuitGameButton;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;

public class NavBarLabel extends JLabel {

    public NavBarLabel(GameFrame gameFrame, GameClient gameClient) {

        QuitGameButton quitButton = new QuitGameButton(gameClient);
        quitButton.setLocation(GameUtil.SCREEN_WIDTH - quitButton.getWidth(), 0);

        MinimizeButton minimizeButton = new MinimizeButton(gameFrame);
        minimizeButton.setLocation(GameUtil.SCREEN_WIDTH - (quitButton.getWidth() + minimizeButton.getWidth()), 0);

        this.add(quitButton);
        this.add(minimizeButton);
        this.setOpaque(true);
        this.setBackground(GameUtil.GUI_BACKGROUND);
        this.setSize(GameUtil.SCREEN_WIDTH, 30);
        this.setLocation(0,0);
        this.setLayout(null);
    }
}
