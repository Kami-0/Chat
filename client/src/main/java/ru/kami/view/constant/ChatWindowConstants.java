package ru.kami.view.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * Хранит константы для класса ChatWindow
 *
 * @author Kami
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChatWindowConstants {
    public static final String FRAME_ICON_CODE = "frameIcon";
    public static final String APP_NAME = "Telegraph";
    public static final int LOCATION_X;
    public static final int LOCATION_Y;
    public static final int WEIGHT_X = 0;
    public static final int WEIGHT_Y = 0;
    public static final int INSETS_TOP = 2;
    public static final int INSETS_BOTTOM = 0;
    public static final int INSETS_LEFT = 0;
    public static final int INSETS_RIGHT = 1;
    public static final int IPAD_X = 0;
    public static final int IPAD_Y = 0;
    public static final int TEXT_AREA_GRID_X = 1;
    public static final int TEXT_AREA_GRID_Y = 0;
    public static final int TEXT_AREA_GRID_WIDTH = 5;
    public static final int TEXT_AREA_GRID_HEIGHT = 5;
    public static final int INPUT_FIELD_GRID_X = 1;
    public static final int INPUT_FIELD_GRID_Y = 5;
    public static final int INPUT_FIELD_GRID_WIDTH = 5;
    public static final int INPUT_FIELD_GRID_HEIGHT = 1;
    public static final int USER_LIST_GRID_X = 0;
    public static final int USER_LIST_GRID_Y = 0;
    public static final int USER_LIST_GRID_WIDTH = 1;
    public static final int USER_LIST_GRID_HEIGHT = 6;

    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        LOCATION_X = dimension.width / 2 - 250;
        LOCATION_Y = dimension.height / 2 - 250;
    }

}
