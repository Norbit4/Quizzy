package pl.norbit.gameserver.listeners;

import pl.norbit.gameserver.objects.GamePlayer;
import pl.norbit.server.ServerClient;
import pl.norbit.server.listeners.OnClientJoinServer;

public class OnJoinEvent implements OnClientJoinServer {

    @Override
    public void onJoinEvent(ServerClient serverClient) {

        new GamePlayer(serverClient);
    }
}
