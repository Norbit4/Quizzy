package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.buttons.CancelJoinButton;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.JoinSetButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JoinPanel extends JPanel {

    public JoinPanel(GameFrame gameFrame, GameClient gameClient) {

        JLabel logo = new JLabel();
        logo.setIcon(GameUtil.LOGO);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setSize(300,300);
        logo.setLocation(150,50);

        JLabel joinText = new JLabel();
        joinText.setHorizontalAlignment(SwingConstants.CENTER);
        joinText.setSize(300,100);
        joinText.setLocation(150,350);
        joinText.setFont(GameUtil.BUTTON_FONT);
        joinText.setText("Your token:");
        joinText.setForeground(Color.white);

        JTextField tokenField = new JTextField();
        tokenField.setFont(GameUtil.BUTTON_FONT);
        tokenField.setSize(300, 80);
        tokenField.setLocation(150, 450);
        tokenField.setForeground(new Color(119, 118, 118));
        tokenField.setHorizontalAlignment(SwingConstants.CENTER);
        tokenField.setBorder(null);

        JoinSetButton joinSetButton = new JoinSetButton(gameFrame, gameClient,tokenField);
        joinSetButton.setLocation(150, 540);

        CancelJoinButton cancelJoinButton = new CancelJoinButton(gameFrame, gameClient);
        cancelJoinButton.setLocation(150, 620);

        JLabel mainLabel = new JLabel();
        mainLabel.setOpaque(true);
        mainLabel.setBackground(GameUtil.GUI_BACKGROUND);
        mainLabel.setSize(600, 720);
        mainLabel.setLocation((GameUtil.SCREEN_WIDTH - mainLabel.getWidth())/2,(GameUtil.SCREEN_HEIGHT - mainLabel.getHeight())/2);
        mainLabel.setLayout(null);
        mainLabel.setBorder(new LineBorder(Color.GRAY, 3,false));
        mainLabel.add(cancelJoinButton);
        mainLabel.add(joinText);
        mainLabel.add(logo);
        mainLabel.add(tokenField);
        mainLabel.add(joinSetButton);

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.add(mainLabel);
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setDoubleBuffered(true);
        this.setLayout(null);
    }
}
