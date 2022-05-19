package pl.norbit.gameclient.game.buttons;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.enums.Channel;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.gameclient.tasks.Checker;
import pl.norbit.gameclient.utils.GameUtil;
import pl.norbit.server.objects.ObjectPacket;

import javax.swing.*;
import java.awt.*;

public class NickSetButton extends JButton {

    public NickSetButton(GameFrame gameFrame, GameClient gameClient,JTextField nickField) {

        this.setBackground(GameUtil.GREEN_BUTTON);
        this.setForeground(GameUtil.BUTTON_FOREGROUND);
        this.setSize(300,60);
        this.setFocusPainted(false);
        this.setText("Join");
        this.setFont(GameUtil.BUTTON_FONT);
        this.addMouseListener(new ButtonAnimation(this, GameUtil.GREEN_BUTTON_ENTER));

        this.addActionListener(e -> {
            String playerNick = nickField.getText();
            gameClient.connect("54.38.54.161", 1235);

            if(nickIsCorrect(playerNick)){

                joinToServer(gameFrame, gameClient, playerNick);
            } else {
                System.out.println("bledny nick");
                nickField.setText("");
            }
        });
    }

    private boolean nickIsCorrect(String nick){

        if(!nick.isEmpty()){
            return nick.length() >= 5;
        }
        return false;
    }

    private void joinToServer(GameFrame gameFrame, GameClient gameClient, String playerNick){

        if(gameClient.isConnected()) {

            gameClient.sendObject(new ObjectPacket(Channel.SET_NICK.name(), playerNick));
            GameInfo.setPlayerNick(playerNick);

            Thread thread = new Thread(new Checker(gameFrame, gameClient));
            thread.start();

        }else{
            System.out.println("nie polaczono");
        }
    }
}
