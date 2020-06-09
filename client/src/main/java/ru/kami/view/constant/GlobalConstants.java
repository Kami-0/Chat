package ru.kami.view.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * Хранит общие константы для всего чата
 *
 * @author Kami
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalConstants {
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 8189;

    public static final Color BACKGROUND_COLOR = new Color(107, 107, 107);
    public static final int FONT_SIZE = 18;
    private static final String FONT_NAME = "TimesRoman";
    private static final int FONT_STYLE = Font.BOLD;
    public static final Font FONT = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
}
