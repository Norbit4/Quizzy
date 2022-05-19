package pl.norbit.gameserver.listeners;

import com.google.gson.Gson;
import pl.norbit.gameserver.enums.Channel;
import pl.norbit.gameserver.enums.Error;
import pl.norbit.gameserver.objects.GamePlayer;
import pl.norbit.gameserver.objects.GameRoom;
import pl.norbit.gameserver.objects.Question;
import pl.norbit.gameserver.question.QuestionReader;
import pl.norbit.server.ConnectedClient;
import pl.norbit.server.listeners.OnMessageReceiveServer;
import pl.norbit.server.objects.ObjectPacket;
import pl.norbit.server.objects.Packets;

import java.util.UUID;

public class OnMessageEvent implements OnMessageReceiveServer {
    private final static Gson gson;

    static {
        gson = new Gson();
    }

    @Override
    public void onMessageEvent(ConnectedClient connectedClient, Packets packets) {
        String channel = packets.getChannel();

        if(channel.equals(Channel.CREATE_NEW_ROOM.name())){

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);

            if(gamePlayer != null) {
                GameRoom gameRoom = new GameRoom(gamePlayer, 2);

                String jsonStringFromObject = gson.toJson(gameRoom.getGamePacket());

                ObjectPacket objectPacket = new ObjectPacket(Channel.CREATE_NEW_ROOM.name(), jsonStringFromObject);

                connectedClient.sendObject(objectPacket);
            }

        }else if(channel.equals(Channel.JOIN_TO_ROOM.name())){

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String token = (String) objectPacket.getObject();

            GameRoom gameRoom = checkRoomExist(connectedClient, token, channel);

            System.out.println(gameRoom);

            if(gameRoom != null) {

                int info = gameRoom.addPlayer(GamePlayer.getPlayer(connectedClient));

                if (info != 1) {
                    connectedClient.sendObject(new ObjectPacket(Channel.JOIN_TO_ROOM.name(),
                            Error.SLOTS.name()));
                }
            }
        }else if(channel.equals(Channel.KICK.name())){

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String stringUUID = (String) objectPacket.getObject();

            GameRoom gameRoom = GamePlayer.getPlayer(connectedClient).getGameRoom();

            GamePlayer gamePlayer = GamePlayer.getPlayer(UUID.fromString(stringUUID));

            System.out.println("owner " + gameRoom.getOwner().getPlayerName());
            System.out.println(gameRoom.getOwner().getPlayerUUID().equals(UUID.fromString(stringUUID)));

            System.out.println("player s " + gamePlayer.getPlayerName());

            ((ConnectedClient)gamePlayer.getServerClient()).sendObject(new ObjectPacket(Channel.KICK.name(),""));
            System.out.println(gameRoom.removePlayer(gamePlayer));

        } else if(channel.equals(Channel.QUESTION.name())){

            Question question = QuestionReader.getQuestion();

            String object = gson.toJson(question);

            ObjectPacket objectPacket = new ObjectPacket(Channel.QUESTION.name(), object);

            connectedClient.sendObject(objectPacket);

        } else if(channel.equals(Channel.SET_NICK.name())){

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String playerNick = (String) objectPacket.getObject();

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);
            gamePlayer.setPlayerName(playerNick);

            ObjectPacket UUID = new ObjectPacket(Channel.SET_NICK.name(), gamePlayer.getPlayerUUID().toString());

            System.out.println("nick:" + playerNick);
            connectedClient.sendObject(UUID);

        } else if(channel.equals(Channel.START_GAME.name())){

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);

            gamePlayer.getGameRoom().setAmountQuests(3);

            gamePlayer.getGameRoom().newQuestion();

        } else if(channel.equals(Channel.TIME_QUESTION_OUT.name())) {

            ObjectPacket objectPacket = (ObjectPacket) packets;
            String pointsString = (String) objectPacket.getObject();
            int points = Integer.parseInt(pointsString);

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);
            gamePlayer.setPoints(points);

            gamePlayer.getGameRoom().nextQuestion();

            System.out.println(gamePlayer.getPlayerName() + " " + gamePlayer.getPoints());

        }else if(channel.equals(Channel.EXIT_GAME.name())){

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);

            String jsonStringFromObject = gson.toJson(gamePlayer.getGameRoom().getGamePacket());

            connectedClient.sendObject(new ObjectPacket(Channel.EXIT_GAME.name(), jsonStringFromObject));

        }else if(channel.equals(Channel.LEAVE_ROOM.name())){

            GamePlayer gamePlayer = GamePlayer.getPlayer(connectedClient);

            System.out.println(gamePlayer.getGameRoom().getGamePlayerList());

            gamePlayer.getGameRoom().removePlayer(gamePlayer);
        }
    }

    private GameRoom checkRoomExist(ConnectedClient connectedClient, String token, String channel){

        GameRoom gameRoom = GameRoom.getGameRoom(token);

        if(gameRoom != null){

            return gameRoom;
        } else {
            connectedClient.sendObject(new ObjectPacket(channel, Error.NULL_ROOM.name()));
        }
        return null;
    }
}