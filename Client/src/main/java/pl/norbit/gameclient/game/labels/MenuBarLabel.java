package pl.norbit.gameclient.game.labels;

import pl.norbit.client.GameClient;
import pl.norbit.gameclient.game.GameFrame;
import pl.norbit.gameclient.game.GameInfo;
import pl.norbit.gameclient.game.buttons.QuitRoomButton;
import pl.norbit.gameclient.game.buttons.StartGameButton;
import pl.norbit.gameclient.game.mouseisteners.ButtonAnimation;
import pl.norbit.gameclient.packets.GamePacket;
import pl.norbit.gameclient.utils.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.UUID;

public class MenuBarLabel extends JLabel {

    public MenuBarLabel(GameFrame gameFrame, GameClient gameClient, GamePacket gamePacket) {
        Font font = new Font("SansSerif",Font.BOLD, 30);

        JLabel text = new JLabel();
        text.setHorizontalAlignment(CENTER);
        text.setFont(font);
        text.setForeground(GameUtil.BUTTON_FOREGROUND);
        text.setSize(200,100);
        text.setText(gamePacket.getToken());
        text.setLocation(0,0);

        JButton copyButton = new JButton();
        Image image = GameUtil.COPY_ICON.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);

        QuitRoomButton quitButton = new QuitRoomButton(gameFrame, gameClient);
        quitButton.setLocation(GameUtil.SCREEN_WIDTH - (60 + 50 + quitButton.getWidth()), 30);

        StartGameButton startGameButton = new StartGameButton(gameFrame, gameClient);
        startGameButton.setLocation(GameUtil.SCREEN_WIDTH - (startGameButton.getWidth() + 80 + 60 +
                        quitButton.getWidth()), 30);

        UUID ownerUUID = gamePacket.getOwner().getPlayerUUUD();

        if(ownerUUID.equals(GameInfo.getPlayerUUID()) && gamePacket.getPlayers().size() > 1){

            startGameButton.setEnabled(true);
        }

        copyButton.setFont(GameUtil.BUTTON_FONT);
        copyButton.setSize(40,40);
        copyButton.setFocusable(false);
        copyButton.setBackground(GameUtil.GREEN_BUTTON);
        copyButton.setLocation(190,30);
        copyButton.setIcon(imageIcon);
        copyButton.addMouseListener(new ButtonAnimation(copyButton, GameUtil.GREEN_BUTTON_ENTER));
        copyButton.addActionListener(e -> {

            StringSelection clipboardString = new StringSelection(gamePacket.getToken());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            clipboard.setContents(clipboardString, null);
        });

        this.add(startGameButton);
        this.add(quitButton);
        this.add(copyButton);
        this.add(text);
        this.setLayout(null);
        this.setBackground(GameUtil.GUI_BACKGROUND);
        this.setVisible(true);
        this.setSize(new Dimension(GameUtil.SCREEN_WIDTH - 60, 100));
        this.setOpaque(true);
    }
}
