package pl.norbit.gameserver.tasks;

import com.google.gson.Gson;
import pl.norbit.gameserver.enums.Channel;
import pl.norbit.gameserver.objects.GameRoom;
import pl.norbit.gameserver.objects.Question;
import pl.norbit.gameserver.question.QuestionReader;
import pl.norbit.server.ConnectedClient;
import pl.norbit.server.objects.ObjectPacket;


public class GameRoomTask implements Runnable {
    private final static Gson gson;
    private int i;
    private final GameRoom gameRoom;
    private boolean c;

    static {
        gson = new Gson();
    }

    public GameRoomTask(GameRoom gameRoom) {
        this.i = 29;
        this.gameRoom = gameRoom;
        this.c = true;
    }

    @Override
    public void run() {
        sendQuestion();

        while (c) {

            if(i != 30) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(!gameRoom.getGamePlayerList().isEmpty()) {

                if (i >= 0) {
                    sendTimer(i);
                }

                if (i == 0) {
                    gameRoom.setAmountQuests(gameRoom.getAmountQuests() - 1);
                    gameRoom.setTaskRunning(false);
                    c = false;
                    break;
                }
                i--;

            }else{
                c = false;
                gameRoom.setTaskRunning(false);
                break;
            }
        }
    }

    public void sendTimer(int timer){
        gameRoom.getGamePlayerList().forEach(gamePlayer -> {

            System.out.println(gamePlayer.getPlayerName());

            ConnectedClient connectedClient  = (ConnectedClient) gamePlayer.getServerClient();

            connectedClient.sendObject(new ObjectPacket(Channel.TIMER.name(), String.valueOf(timer)));
        });
    }

    public void sendQuestion(){
        Question question = QuestionReader.getQuestion();

        gameRoom.getGamePlayerList().forEach(gamePlayer -> {
            ConnectedClient connectedClient  = (ConnectedClient) gamePlayer.getServerClient();

            String object = gson.toJson(question);

            ObjectPacket objectPacket = new ObjectPacket(Channel.QUESTION.name(), object);

            connectedClient.sendObject(objectPacket);
        });
    }
}