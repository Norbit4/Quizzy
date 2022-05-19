package pl.norbit.gameserver.objects;


import com.google.gson.Gson;
import pl.norbit.gameserver.packets.GamePacket;
import pl.norbit.server.ServerClient;

import java.util.*;

public class GamePlayer {
    private final static List<GamePlayer> gamePlayerList;
    private static final Gson gson;
    private GameRoom gameRoom;
    private final ServerClient serverClient;
    private final UUID playerUUID;
    private String playerName;
    private final GamePacket.PlayerPacket playerPacket;
    private int points;

    static {
        gamePlayerList = new ArrayList<>();
        gson = new Gson();
    }

    public GamePlayer(ServerClient connectedClient) {
        this.playerUUID = UUID.randomUUID();
        this.serverClient = connectedClient;

        this.playerPacket = new GamePacket.PlayerPacket(playerUUID);
        this.points = 0;

        gamePlayerList.add(this);
    }

    //static
    public static List<GamePlayer> getGamePlayerList() {
        return gamePlayerList;
    }

    public static GamePlayer getPlayer(ServerClient serverClient){

        Optional<GamePlayer> optionalGamePlayer = getGamePlayerList()
                .stream()
                .filter(gamePlayer -> gamePlayer.getServerClient().getClientUUID().equals(serverClient.getClientUUID()))
                .findFirst();

        return optionalGamePlayer.orElse(null);
    }

    public static GamePlayer getPlayer(UUID playerUUID){

        Optional<GamePlayer> optionalGamePlayer = getGamePlayerList()
                .stream()
                .filter(gamePlayer -> gamePlayer.getPlayerUUID().equals(playerUUID))
                .findFirst();

        return optionalGamePlayer.orElse(null);
    }

    public static void removePlayer(GamePlayer gamePlayer){

        getGamePlayerList().remove(gamePlayer);
    }

    //this class
    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points += 1;
        playerPacket.addPoint();
    }

    public void clearPoints(){
        points = 0;
        playerPacket.clearPoints();
    }

    public ServerClient getServerClient() {
        return serverClient;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.playerPacket.setPlayerName(playerName);
    }

    public GamePacket.PlayerPacket getPlayerPacket() {
        return playerPacket;
    }

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}