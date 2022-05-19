package pl.norbit.gameserver.commands;

import pl.norbit.gameserver.objects.GameRoom;
import pl.norbit.server.GameServer;

import java.util.Scanner;

public class CommandTask implements Runnable{
    private final GameServer gameServer;
    private final Scanner scanner;
    private static final String errorPrefix;

    static {
        errorPrefix = "CMD: ";
    }

    public CommandTask(GameServer gameServer) {
        this.gameServer = gameServer;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (gameServer.isRunning()){
            String command = scanner.nextLine();

            if(command.equalsIgnoreCase("stop")){
                gameServer.close();
                System.exit(0);
            } else{
                String [] cmdArray = command.split("");
                if(cmdArray[0].equalsIgnoreCase("room")){

                    if(cmdArray[1].equalsIgnoreCase("get")){

                        if(cmdArray[2].equalsIgnoreCase("all")){
                            GameRoom.getGameRooms().forEach(gameRoom -> {
                                System.out.println("-----------------------------");
                                System.out.println("TOKEN:" + gameRoom.getToken());
                                System.out.println("PLAYERS: " + gameRoom.getGamePlayerList().size());
                                System.out.println("OWNER: " + gameRoom.getOwner().getPlayerName());
                                System.out.println("-----------------------------");
                            });
                        }else {
                            GameRoom gameRoom = GameRoom.getGameRoom(cmdArray[2]);

                            if(gameRoom != null){
                                System.out.println("-----------------------------");
                                System.out.println("TOKEN:" + gameRoom.getToken());
                                System.out.println("PLAYERS: " + gameRoom.getGamePlayerList().size());
                                System.out.println("OWNER: " + gameRoom.getOwner().getPlayerName());
                                System.out.println("-----------------------------");
                            }else{
                                System.out.println("invalid token");
                            }
                        }

                    } else{
                        System.out.println("cmd <arg> <arg>");
                    }

                }else{
                    System.out.println(errorPrefix + "invalid command");
                }
            }
        }
    }
}
