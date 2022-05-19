package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.labels.EndGameCardLabel;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.EndGameButton;
import pl.norbit.gameclient.game.GameInfo;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class EndGamePanel extends JPanel {

    public EndGamePanel(GameFrame gameFrame, GameClient gameClient, UUID winnerUUID, String winnerNick) {

        JLabel endGameInfo = new JLabel();
        JLabel winInfo = new JLabel();
        EndGameCardLabel.setPlayerPlace(1);

        if(winnerUUID.equals(GameInfo.getPlayerUUID())){
            winInfo.setText("YOU WON!");
            winInfo.setBackground(new Color(50, 164, 93));
        } else {
            winInfo.setText("YOU LOSE!");
            winInfo.setBackground(new Color(197, 77, 77));
        }

        endGameInfo.setVisible(true);
        endGameInfo.setLayout(null);
        endGameInfo.setOpaque(true);
        endGameInfo.setBackground(GameUtil.GUI_BACKGROUND);
        endGameInfo.setSize(GameUtil.SCREEN_WIDTH - 100, GameUtil.SCREEN_HEIGHT - 100);
        endGameInfo.setLocation(50, 50);

        winInfo.setOpaque(true);
        winInfo.setForeground(GameUtil.BUTTON_FOREGROUND);
        winInfo.setVisible(true);
        winInfo.setFont(new Font("Arial", Font.BOLD,40));
        winInfo.setLocation(0,0);
        winInfo.setSize(endGameInfo.getWidth(), 100);
        winInfo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel winner = new JLabel();

        winner.setForeground(GameUtil.BUTTON_FOREGROUND);
        winner.setSize(endGameInfo.getWidth(), 100);
        winner.setFont(new Font("Arial", Font.BOLD,30));
        winner.setLocation(0,250);
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        winner.setText("Winner: " + winnerNick);

        endGameInfo.add(winner);
        endGameInfo.add(winInfo);

        EndGameButton endGameButton = new EndGameButton(gameClient);
        endGameButton.setLocation((endGameInfo.getWidth() - endGameButton.getWidth())/2,
                endGameInfo.getHeight() - endGameButton.getHeight() - 50);
        endGameInfo.add(endGameButton);

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.add(endGameInfo);
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setDoubleBuffered(true);
        this.setLayout(null);
    }
}
