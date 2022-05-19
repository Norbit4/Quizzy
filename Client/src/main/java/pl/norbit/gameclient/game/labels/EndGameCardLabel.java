package pl.norbit.gameclient.game.labels;

import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class EndGameCardLabel extends JLabel{
    private static int playerPlace;

    public EndGameCardLabel(JLabel endGameInfo, String playerNick, int points) {

        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setSize(endGameInfo.getWidth(), 100);
        this.setFont(new Font("Arial", Font.BOLD,30));
        this.setLocation(0,250);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText( "#" + playerPlace + " " + playerNick + " " + points);
        playerPlace++;
    }

    public static void setPlayerPlace(int playerPlace) {
        EndGameCardLabel.playerPlace = playerPlace;
    }
}
