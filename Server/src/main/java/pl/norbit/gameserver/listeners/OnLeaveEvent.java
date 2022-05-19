package pl.norbit.gameserver.listeners;

import pl.norbit.gameserver.objects.GamePlayer;
import pl.norbit.gameserver.objects.GameRoom;
import pl.norbit.server.ServerClient;
import pl.norbit.server.listeners.OnClientLeaveServer;

public class OnLeaveEvent implements OnClientLeaveServer {

    @Override
    public void onLeaveEvent(ServerClient serverClient) {

        GamePlayer gamePlayer = GamePlayer.getPlayer(serverClient);

        System.out.println(gamePlayer.getPlayerName());
        GameRoom gameRoom =  gamePlayer.getGameRoom();

        if(gameRoom != null) {
            gamePlayer.getGameRoom().removePlayer(gamePlayer);
        }

        GamePlayer.removePlayer(gamePlayer);
    }
}
