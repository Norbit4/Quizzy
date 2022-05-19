package pl.norbit.gameclient.game;

import pl.norbit.client.GameClient;

import java.util.UUID;

public class GameInfo {

    private static String token, playerNick;
    private static int size, points;
    private static UUID playerUUID;
    private static GameClient gameClient;

    static {
        points = 0;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GameInfo.token = token;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        GameInfo.size = size;
    }

    public static String getPlayerNick() {
        return playerNick;
    }

    public static UUID getPlayerUUID() {
        return playerUUID;
    }

    public static void setPlayerUUID(UUID playerUUID) {
        GameInfo.playerUUID = playerUUID;
    }

    public static void setPlayerNick(String playerNick) {
        GameInfo.playerNick = playerNick;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        GameInfo.points = points;
    }

    public static void setGameClient(GameClient gameClient) {
        GameInfo.gameClient = gameClient;
    }

    public static GameClient getGameClient() {
        return gameClient;
    }
}

