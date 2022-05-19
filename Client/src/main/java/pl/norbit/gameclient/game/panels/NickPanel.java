package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.NickSetButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NickPanel extends JPanel {

    public NickPanel(GameFrame gameFrame, GameClient gameClient) {

        //logo
        JLabel logo = new JLabel();
        logo.setIcon(GameUtil.LOGO);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setSize(300,300);
        logo.setLocation(150,50);

        //nick text
        JLabel nickText = new JLabel();
        nickText.setHorizontalAlignment(SwingConstants.CENTER);
        nickText.setSize(300,100);
        nickText.setLocation(150,430);
        nickText.setFont(GameUtil.BUTTON_FONT);
        nickText.setText("Your nickname:");
        nickText.setForeground(Color.white);

        //nick
        JTextField nickField = new JTextField();
        nickField.setFont(GameUtil.BUTTON_FONT);
        nickField.setSize(300, 80);
        nickField.setLocation(150, 530);
        nickField.setForeground(new Color(119, 118, 118));
        nickField.setHorizontalAlignment(SwingConstants.CENTER);
        nickField.setBorder(null);

        //button
        NickSetButton nickSetButton = new NickSetButton(gameFrame,gameClient, nickField);
        nickSetButton.setLocation(150, 620);

        JLabel mainLabel = new JLabel();
        mainLabel.setOpaque(true);
        mainLabel.setBackground(GameUtil.GUI_BACKGROUND);
        mainLabel.setSize(600, 720);
        mainLabel.setLocation((GameUtil.SCREEN_WIDTH - mainLabel.getWidth())/2,(GameUtil.SCREEN_HEIGHT - mainLabel.getHeight())/2);
        mainLabel.setLayout(null);
        mainLabel.setBorder(new LineBorder(Color.GRAY, 3,false));
        mainLabel.add(nickText);
        mainLabel.add(logo);
        mainLabel.add(nickField);
        mainLabel.add(nickSetButton);

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.add(mainLabel);
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setDoubleBuffered(true);
        this.setLayout(null);
    }
}
