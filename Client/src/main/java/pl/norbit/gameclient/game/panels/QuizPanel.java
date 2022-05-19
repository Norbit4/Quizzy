package pl.norbit.gameclient.game.panels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.labels.NavBarLabel;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.buttons.QuizButton;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.gameclient.objects.Question;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JPanel {
    private final QuizButton[] quizButtons;
    private final String[] answerArray = {"A", "B", "C", "D"};
    private final JLabel[] answerTextArray;
    private final JLabel timer;
    private final String timerPrefix = "Time: ";
    private final GameClient gameClient;
    private final JLabel questionTittle;

    public QuizPanel(GameFrame gameFrame, GameClient gameClient, Question question) {
        this.quizButtons = new QuizButton[4];
        this.gameClient = gameClient;
        this.answerTextArray = new JLabel[4];

        int time = 30;
        int x = 0;
        int questionY = 250;

        JLabel answerLabel = new JLabel();

        for (int i = 0; i < 4; i++) {

            String answer = question.getAnswers().get(i);

            QuizButton quizButton = new QuizButton(this, answerArray[i] ,i == question.getGoodAnswer());

            JLabel answerText = new JLabel();
            answerText.setFont(new Font("Arial",Font.PLAIN, 17));
            answerText.setText(answerArray[i] +  ": " + answer);
            answerText.setSize(GameUtil.SCREEN_WIDTH - 100, 50);
            answerText.setForeground(Color.WHITE);
            answerText.setLocation(0, questionY);
            answerText.setHorizontalAlignment(SwingConstants.CENTER);
            answerText.setVisible(true);
            answerLabel.add(answerText);
            answerTextArray[i] = answerText;
            questionY += 50;

            if(x == 0) {
                x += ((GameUtil.SCREEN_WIDTH - 100 - (4 * quizButton.getWidth())) / 5);
            } else{
                x += ((GameUtil.SCREEN_WIDTH - 100 - (4 * quizButton.getWidth())) / 5) + quizButton.getWidth();
            }
            quizButton.setLocation(x, 0);
            quizButton.setVisible(true);

            quizButtons[i] = quizButton;
        }

        Font font = new Font("SansSerif",Font.BOLD, 30);

        timer = new JLabel();
        timer.setBackground(GameUtil.GUI_BACKGROUND);
        timer.setOpaque(true);
        timer.setVisible(true);
        timer.setFont(font);
        timer.setText(timerPrefix + time);
        timer.setForeground(new Color(47, 185, 91));
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setSize(200, 100);
        timer.setLocation((GameUtil.SCREEN_WIDTH - timer.getWidth())/2 ,60);

        answerLabel.setOpaque(true);
        answerLabel.setBackground(GameUtil.GUI_BACKGROUND);
        answerLabel.setLocation(50, timer.getHeight() + ((int)timer.getLocation().getY() + 50));
        answerLabel.setSize(GameUtil.SCREEN_WIDTH - 100,
                GameUtil.SCREEN_HEIGHT - ((int)answerLabel.getLocation().getY()) - 50);

        questionTittle = new JLabel();
        questionTittle.setText(question.getTittle());
        questionTittle.setForeground(Color.white);
        questionTittle.setFont(font);
        questionTittle.setHorizontalAlignment(SwingConstants.CENTER);
        questionTittle.setSize(answerLabel.getWidth(), 100);
        questionTittle.setLocation(0,50);
        answerLabel.add(questionTittle);

        JLabel questions = new JLabel();
        for (QuizButton quizButton : quizButtons) {
            questions.add(quizButton);
        }
        questions.setSize(GameUtil.SCREEN_WIDTH - 100, 150);
        questions.setLocation(0, answerLabel.getHeight() - questions.getHeight() - 50);
        questions.setLayout(null);
        questions.setVisible(true);
        answerLabel.add(questions);

        this.add(new NavBarLabel(gameFrame, gameClient));
        this.setBackground(GameUtil.MAIN_BACKGROUND);
        this.setDoubleBuffered(true);
        this.add(timer);
        this.add(answerLabel);
        this.setLayout(null);
        this.setVisible(true);
    }

    public JButton[] getQuizButtons() {
        return quizButtons;
    }

    public void updateTime(int time) {

        if (time <= 5) {
            timer.setForeground(new Color(194, 50, 50));

        }else if (time <= 15){
            timer.setForeground(new Color(176, 136, 48));

        }else if (time <= 30) {
            timer.setForeground(new Color(47, 185, 91));
        }

        timer.setText(timerPrefix + time);

        if(time == 0){
            gameClient.sendObject(new ObjectPacket(Channel.TIME_QUESTION_OUT.name(),
                    String.valueOf(GameInfo.getPoints())));
        }
    }
    
    public void updateQuestion(Question question) {

        for (int i = 0; i < 4; i++) {

            String answer = question.getAnswers().get(i);

            QuizButton quizButton = quizButtons[i];
            quizButton.setEnabled(true);
            quizButton.updateButton(i == question.getGoodAnswer());

            answerTextArray[i].setText(answerArray[i] +  ": " + answer);
        }
        questionTittle.setText(question.getTittle());
        updateTime(30);
    }
}