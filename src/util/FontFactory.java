package util;

import java.awt.*;

public class FontFactory {

    public static final int textSize = 25;
    private static final String messageFontName = "Century Gothic";
    public static Font
            MESSAGE = new Font(messageFontName, 0, textSize),
            BOLD_MESSAGE = new Font(messageFontName, 1, textSize),
            ITALIC_MESSAGE = new Font(messageFontName, 2, textSize),
            BOLD_ITALIC_MESSAGE = new Font(messageFontName, 3, textSize),
            CHAT_FONT = new Font(messageFontName, 0, textSize);
}
