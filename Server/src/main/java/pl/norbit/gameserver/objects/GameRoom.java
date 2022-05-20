package pl.norbit.gameserver.objects;

import com.google.gson.Gson;
import pl.norbit.gameserver.enums.Channel;
import pl.norbit.gameserver.enums.EndGameType;
import pl.norbit.gameserver.packets.GamePacket;
import pl.norbit.gameserver.tasks.GameRoomTask;
import pl.norbit.gameserver.token.GameToken;
import pl.norbit.server.ConnectedClient;
import pl.norbit.server.objects.ObjectPacket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class GameRoom {
    private final static ArrayList<GameRoom> gameRoomList;
    private final static Gson gson;
    private final ArrayList<GamePlayer> gamePlayerList;
    private GamePacket gamePacket;
    private String token;
    private GamePlayer owner;
    private int size, amountQuests, clientsReady;
    private boolean taskRunning;

    static{
        gameRoomList = new ArrayList<>();
        gson = new Gson();
    }

    public GameRoom(GamePlayer owner, int size) {
        this.gamePlayerList = new ArrayList<>();
        this.gamePlayerList.add(owner);
        this.owner = owner;
        this.size = 4;
        this.token = GameToken.generate();
        this.clientsReady = 0;
        this.amountQuests = 1;

        owner.setGameRoom(this);
        gameRoomList.add(this);

        setGamePacket();
    }

    private void setGamePacket(){
        gamePacket = new GamePacket(token);
        gamePacket.setOwner(owner.getPlayerPacket());
        gamePacket.addPlayerPacket(owner.getPlayerPacket());
        gamePacket.setSize(size);
    }

    //static
    public static GameRoom getGameRoom(String token){
        Optional<GameRoom> optionalGameRoom =  gameRoomList
                .stream()
                .filter(gameRoom -> gameRoom.getToken().equals(token))
                .findFirst();

        return optionalGameRoom.orElse(null);
    }

    public static ArrayList<GameRoom> getGameRooms(){
        return gameRoomList;
    }

    //this class
    public void nextQuestion(){
        clientsReady++;
        if(clientsReady == gamePlayerList.size()){
            newQuestion();
            clientsReady = 0;
        }
    }

    public void newQuestion() {

        if(gamePlayerList.size() > 1) {
            if (amountQuests > 0) {

                Thread thread = new Thread(new GameRoomTask(this));
                thread.start();
                setTaskRunning(true);

            } else {
                endGame(EndGameType.NORMAL);
            }
        } else {
            endGame(EndGameType.PLAYER_LEFT);
        }
    }

    public ArrayList<GamePlayer> getGamePlayerList() {
        return gamePlayerList;
    }

    public GamePacket getGamePacket() {
        return gamePacket;
    }

    public int addPlayer(GamePlayer gamePlayer){

        if(gamePlayerList.size() < size){
            gamePlayerList.add(gamePlayer);
            gamePlayer.setGameRoom(this);
            gamePacket.addPlayerPacket(gamePlayer.getPlayerPacket());

            updateGameLobby();
            return 1;
        }
        return 0;
    }

    public int removePlayer(GamePlayer gamePlayer){

        if(gamePlayerList.contains(gamePlayer)) {
            gamePlayerList.remove(gamePlayer);
            gamePlayer.setGameRoom(null);
            gamePacket.removePlayerPacket(gamePlayer.getPlayerPacket());

            updateGameLobby();
            return 1;
        }
        return 0;
    }

    private void updateGameLobby(){

        if (!gamePlayerList.isEmpty()) {
            if (!taskRunning) {
                gamePlayerList.forEach(gamePlayer -> {

                    ConnectedClient connectedClient = (ConnectedClient) gamePlayer.getServerClient();

                    System.out.println(GamePlayer.getPlayer(connectedClient).getPlayerName());

                    String jsonStringFromObject = gson.toJson(getGamePacket());

                    ObjectPacket objectPacket = new ObjectPacket(Channel.UPDATE_LOBBY.name(), jsonStringFromObject);

                    connectedClient.sendObject(objectPacket);
                });
            }
        } else{
            gameRoomList.remove(this);
        }
    }

    private void endGame(EndGameType endGameType){
        if(endGameType.equals(EndGameType.NORMAL)){

            GamePlayer gamePlayer = findWinner();

            sendEndInfo(gamePlayer);

        }else if (endGameType.equals(EndGameType.PLAYER_LEFT)){
            getGamePlayerList().forEach(gamePlayer -> {

                ConnectedClient connectedClient = (ConnectedClient) gamePlayer.getServerClient();

                String jsonStringFromObject = gson.toJson(gamePlayer.getGameRoom().getGamePacket());

                ObjectPacket objectPacket = new ObjectPacket(Channel.END_GAME.name(), jsonStringFromObject);

                connectedClient.sendObject(objectPacket);
            });
        }
    }

    private GamePlayer findWinner(){
        Optional<GamePlayer> gamePlayerOptional = getGamePlayerList()
                .stream()
                .max(Comparator.comparing(GamePlayer::getPoints));
        return gamePlayerOptional.orElse(null);
    }

    private void sendEndInfo(GamePlayer gamePlayer){

        getGamePlayerList().forEach(gamePlayer1 -> {
            ConnectedClient connectedClient = (ConnectedClient) gamePlayer1.getServerClient();

            String jsonStringFromObject = gson.toJson(gamePlayer1.getGameRoom().getGamePacket());

            ObjectPacket objectPacket = new ObjectPacket(Channel.END_GAME.name(), jsonStringFromObject);

            connectedClient.sendObject(objectPacket);
            System.out.println("sending to" + gamePlayer1.getPlayerName() + "" + gamePlayer1.getPoints());
        });
    }

    public GamePlayer getOwner() {
        return owner;
    }

    public void setOwner(GamePlayer owner) {
        this.owner = owner;
        this.gamePacket.setOwner(owner.getPlayerPacket());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.gamePacket.setSize(size);
    }

    public int getAmountQuests() {
        return amountQuests;
    }

    public void setAmountQuests(int amountQuests) {
        this.amountQuests = amountQuests;
        this.gamePacket.setAmountQuests(amountQuests);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        this.gamePacket.setToken(token);
    }

    public boolean isTaskRunning() {
        return taskRunning;
    }

    public void setTaskRunning(boolean taskRunning) {
        this.taskRunning = taskRunning;
    }
}