package pl.norbit.gameclient.utils;

import javax.swing.*;
import java.awt.*;

public class GameUtil {
    public final static Color GREEN_BUTTON, MAIN_BACKGROUND, RED_BUTTON, GREEN_BUTTON_ENTER, RED_BUTTON_ENTER,
            GUI_BACKGROUND, BUTTON_FOREGROUND;
    public final static ImageIcon LOGO, LEADER_ICON, COPY_ICON;
    public final static Font BUTTON_FONT;
    public final static int SCREEN_WIDTH, SCREEN_HEIGHT;

    static {
        String imgFolderPath = "img/";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        GREEN_BUTTON = new Color(90, 136, 108);
        GREEN_BUTTON_ENTER = new Color(90, 183, 120);

        RED_BUTTON = new Color(147, 74, 74);
        RED_BUTTON_ENTER = new Color(169, 42, 55);

        MAIN_BACKGROUND = new Color(157, 166, 157);
        GUI_BACKGROUND = Color.DARK_GRAY;

        BUTTON_FOREGROUND = Color.white;

        BUTTON_FONT = new Font("ARIAL", Font.BOLD, 25);

        LOGO = new ImageIcon(imgFolderPath + "logo.png");
        LEADER_ICON = new ImageIcon(imgFolderPath + "leader.png");
        COPY_ICON = new ImageIcon( imgFolderPath + "copy.png");

        SCREEN_WIDTH = (int) screenSize.getWidth();
        SCREEN_HEIGHT = (int) screenSize.getHeight();
    }
}
