package pl.norbit.gameclient.listeners;

import com.google.gson.Gson;
import pl.norbit.client.GameClient;
import pl.norbit.client.listeners.OnMessageReceiveClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.enums.Error;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.panels.EndGamePanel;
import pl.norbit.gameclient.game.panels.LobbyPanel;
import pl.norbit.gameclient.game.panels.MenuPanel;
import pl.norbit.gameclient.game.panels.QuizPanel;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.gameclient.objects.Question;
import pl.norbit.gameclient.packets.GamePacket;
import pl.norbit.server.objects.ObjectPacket;
import pl.norbit.server.objects.Packets;

import java.util.UUID;

public class MessageEvent implements OnMessageReceiveClient {
    private final static Gson gson;
    private final GameFrame gameFrame;
    private final GameClient gameClient;
    private QuizPanel quizPanel;

    static {
        gson = new Gson();
    }

    public MessageEvent(GameFrame gameFrame, GameClient gameClient) {
        this.gameFrame = gameFrame;
        this.gameClient = gameClient;
    }

    @Override
    public void OnMessageEvent(Packets packets) {

        String channel = packets.getChannel();

        if(channel.equals(Channel.CREATE_NEW_ROOM.name())){

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String stringGamePacket = (String) objectPacket.getObject();

            GamePacket gamePacket = gson.fromJson(stringGamePacket, GamePacket.class);

            gameFrame.updatePanel(new LobbyPanel(gameFrame,gameClient, gamePacket));

        }else if(channel.equals(Channel.JOIN_TO_ROOM.name())){
            ObjectPacket objectPacket = (ObjectPacket) packets;
            String stringPacket = (String) objectPacket.getObject();

            if(stringPacket.equals(Error.SLOTS.name())){
                System.out.println("brak slotow");

            }else if(stringPacket.equals(Error.NULL_ROOM.name())){

                System.out.println("nie ma takiego pokoju");
            } else{
                GamePacket gamePacket = gson.fromJson(stringPacket, GamePacket.class);

                gameFrame.updatePanel(new LobbyPanel(gameFrame, gameClient, gamePacket));

            }

        } else if(channel.equals(Channel.QUESTION.name())) {
            ObjectPacket objectPacket = (ObjectPacket) packets;

            String stringQuestion = (String) objectPacket.getObject();

            Question question = gson.fromJson(stringQuestion, Question.class);

            System.out.println(question.getAnswers());

            if(quizPanel != null){
                quizPanel.updateQuestion(question);
            } else{
                quizPanel = new QuizPanel(gameFrame,gameClient, question);
                gameFrame.updatePanel(quizPanel);
            }

        } else if(channel.equals(Channel.SET_NICK.name())) {
            ObjectPacket objectPacket = (ObjectPacket) packets;

            String stringUUID = (String) objectPacket.getObject();

            GameInfo.setPlayerUUID(UUID.fromString(stringUUID));

            gameFrame.updatePanel(new MenuPanel(gameFrame, gameClient));

        }else if(channel.equals(Channel.TIMER.name())) {

            ObjectPacket objectPacket = (ObjectPacket) packets;

            String stringInt = (String) objectPacket.getObject();

            quizPanel.updateTime(Integer.parseInt(stringInt));

        }else if(channel.equals(Channel.END_GAME.name())) {
            quizPanel = null;

            ObjectPacket objectPacket = (ObjectPacket) packets;

            String gamePacketString = (String) objectPacket.getObject();

            GamePacket gamePacket = gson.fromJson(gamePacketString, GamePacket.class);

            gamePacket.getPlayers().forEach(playerPacket ->
                    System.out.println(playerPacket.getPoints() + " " + playerPacket.getPlayerName()));
            gameFrame.updatePanel(new EndGamePanel(gameFrame, gameClient, gamePacket));

        }else if(channel.equals(Channel.UPDATE_LOBBY.name())) {

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String stringGamePacket = (String) objectPacket.getObject();

            GamePacket gamePacket = gson.fromJson(stringGamePacket, GamePacket.class);

            gameFrame.updatePanel(new LobbyPanel(gameFrame, gameClient, gamePacket));

        }else if(channel.equals(Channel.EXIT_GAME.name())) {
            GameInfo.setPoints(0);

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String stringGamePacket = (String) objectPacket.getObject();

            GamePacket gamePacket = gson.fromJson(stringGamePacket, GamePacket.class);

            gameFrame.updatePanel(new LobbyPanel(gameFrame, gameClient, gamePacket));

        }else if(channel.equals(Channel.KICK.name())){

                gameFrame.updatePanel(new MenuPanel(gameFrame, gameClient));
        }
    }
}

