package pl.norbit.gameserver.packets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class GamePacket {

    private final ArrayList<PlayerPacket> players;
    private String token;
    private PlayerPacket owner;
    private int size, amountQuests, points;

    //player class
    public static class PlayerPacket implements Serializable {
        private String playerName;
        private final UUID playerUUUD;
        private int points;

        public PlayerPacket(UUID playerUUUD) {
            this.playerUUUD = playerUUUD;
            this.points = 0;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public UUID getPlayerUUUD() {
            return playerUUUD;
        }

        public int getPoints() {
            return points;
        }

        public void addPoint() {
            points += 1;
        }

        public void clearPoints(){
            points = 0;
        }
    }

    public GamePacket(String token) {
        this.players = new ArrayList<>();
        this.token = token;
        this.points = 0;

    }

    public String getToken() {
        return token;
    }

    public PlayerPacket getOwner() {
        return owner;
    }

    public void setOwner(PlayerPacket owner) {
        this.owner = owner;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addPlayerPacket(PlayerPacket playerPacket){
        players.add(playerPacket);
    }

    public void removePlayerPacket(PlayerPacket playerPacket){
        players.remove(playerPacket);
    }

    public int getAmountQuests() {
        return amountQuests;
    }

    public void setAmountQuests(int amountQuests) {
        this.amountQuests = amountQuests;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
