package pl.norbit.gameserver;

import pl.norbit.gameserver.commands.CommandTask;
import pl.norbit.gameserver.listeners.OnJoinEvent;
import pl.norbit.gameserver.listeners.OnLeaveEvent;
import pl.norbit.gameserver.listeners.OnMessageEvent;
import pl.norbit.server.GameServer;

public class ServerMain {

    public static void main(String[] args) {

        GameServer gameServer = new GameServer(1235);

        gameServer.registerListener(new OnMessageEvent());
        gameServer.registerListener(new OnJoinEvent());
        gameServer.registerListener(new OnLeaveEvent());

        gameServer.start();

        Thread commandThread = new Thread(new CommandTask(gameServer));
        commandThread.start();
    }
}
