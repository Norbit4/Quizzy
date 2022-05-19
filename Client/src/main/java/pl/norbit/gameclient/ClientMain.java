package pl.norbit.gameclient;

import pl.norbit.gameclient.game.GameFrame;

public class ClientMain {
    public static void main(String[] args) {

        GameFrame gameFrame = new GameFrame();
        gameFrame.openGame();
    }
}