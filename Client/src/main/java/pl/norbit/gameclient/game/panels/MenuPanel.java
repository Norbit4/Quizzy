package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.CreateGameButton;
import pl.norbit.gameclient.game.buttons.JoinRoomButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(GameFrame gameFrame, GameClient gameClient) {

        JLabel logo = new JLabel();
        logo.setIcon(GameUtil.LOGO);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setSize(300,300);
        logo.setLocation(150,50);

        CreateGameButton gameButton = new CreateGameButton(gameFrame, gameClient);

        gameButton.setSize(300,60);
        gameButton.setLocation(150, 450);
        gameButton.setFocusPainted(false);

        JoinRoomButton joinRoomButton = new JoinRoomButton(gameFrame, gameClient);

        joinRoomButton.setSize(300,60);
        joinRoomButton.setLocation(150, 550);
        joinRoomButton.setFocusPainted(false);
        joinRoomButton.addMouseListener(new ButtonAnimation(joinRoomButton, GameUtil.GREEN_BUTTON_ENTER));

        JLabel mainLabel = new JLabel();
        mainLabel.setOpaque(true);
        mainLabel.setBackground(GameUtil.GUI_BACKGROUND);
        mainLabel.setSize(600, 720);
        mainLabel.setLocation((GameUtil.SCREEN_WIDTH - mainLabel.getWidth())/2,(GameUtil.SCREEN_HEIGHT - mainLabel.getHeight())/2);
        mainLabel.setLayout(null);
        mainLabel.setBorder(new LineBorder(Color.GRAY, 3,false));
        mainLabel.add(joinRoomButton);
        mainLabel.add(gameButton);
        mainLabel.add(logo);
        mainLabel.add(gameButton);

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.add(mainLabel);
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setDoubleBuffered(true);
        this.setLayout(null);
    }
}
