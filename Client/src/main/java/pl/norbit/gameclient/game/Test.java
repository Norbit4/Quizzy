package pl.norbit.gameclient.game;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.panels.EndGamePanel;
import pl.norbit.gameclient.objects.Question;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
        Question question = new Question();
        question.setTittle("pytanie");
        question.addAnswer("odp", true);
        question.addAnswer("odp" );
        question.addAnswer("odp" );
        question.addAnswer("odp" );

        GameFrame gameFrame = new GameFrame();
        gameFrame.openGame();
        gameFrame.updatePanel(new EndGamePanel(gameFrame, gameClient, UUID.randomUUID(),"Norbit4"));
    }
}
