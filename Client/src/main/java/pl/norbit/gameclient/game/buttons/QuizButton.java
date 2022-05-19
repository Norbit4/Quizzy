package pl.norbit.gameclient.game.buttons;

import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.game.panels.QuizPanel;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class QuizButton extends JButton {
    private boolean isAnswer;

    public QuizButton(QuizPanel panel, String question, boolean isAnswer) {

        this.isAnswer = isAnswer;
        this.setText(question);
        this.setFocusPainted(false);
        this.setBackground(GameUtil.GREEN_BUTTON);
        this.setSize(250, 150);
        this.setFont(new Font("ARIAL", Font.BOLD, 15));
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));
        this.setBorder(null);

        this.addActionListener(e -> {
            if(this.isAnswer){
                GameInfo.setPoints(GameInfo.getPoints() + 1);
            }
            for (JButton jButton : panel.getQuizButtons()) {

                jButton.setEnabled(false);
            }
        });
    }

    public void updateButton(boolean isAnswer){
        this.isAnswer = isAnswer;
        this.setEnabled(true);
    }
}
