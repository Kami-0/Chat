package ru.kami.view.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * Хранит общие константы для элементов превью
 *
 * @author Kami
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PreviewConstants {
    public static final int ADDRESS_IP_FIELD_WIDTH = 160;
    public static final int ADDRESS_IP_FIELD_HEIGHT = 40;
    public static final int PREVIEW_WIDTH = 550;
    public static final int PREVIEW_HEIGHT = 400;
    public static final Pattern ADDRESS_IP_PATTERN = Pattern.compile("([0-9]{1,3}[.]){3}[0-9]{1,3}");

    public static final int PORT_FIELD_WIDTH = 60;
    public static final int PORT_FIELD_HEIGHT = 40;
    public static final Pattern PORT_PATTERN = Pattern.compile("([0-9]{1,5})");

    public static final int NICK_NAME_FIELD_WIDTH = 140;
    public static final int NICK_NAME_FIELD_HEIGHT = 40;
    public static final Pattern NICK_NAME_PATTERN = Pattern.compile("([\\w]{3,12})");

    public static final int WEIGHT_X = 0;
    public static final int WEIGHT_Y = 0;
    public static final int INSETS_TOP = 2;
    public static final int INSETS_BOTTOM = 0;
    public static final int INSETS_LEFT = 0;
    public static final int INSETS_RIGHT = 1;
    public static final int IPAD_X = 0;
    public static final int IPAD_Y = 0;

    public static final int EMPTY_BORDER_TOP = 1;
    public static final int EMPTY_BORDER_BOTTOM = 1;
    public static final int EMPTY_BORDER_LEFT = 1;
    public static final int EMPTY_BORDER_RIGHT = 1;

    public static final int WARNING_EMPTY_BORDER_TOP = 0;
    public static final int WARNING_EMPTY_BORDER_BOTTOM = 0;
    public static final int WARNING_EMPTY_BORDER_LEFT = 0;
    public static final int WARNING_EMPTY_BORDER_RIGHT = 0;
    public static final int WARNING_EMPTY_BORDER_THICKNESS = 2;

    public static final int IP_SERVER_LABEL_GRID_X = 1;
    public static final int IP_SERVER_LABEL_GRID_Y = 1;
    public static final int IP_SERVER_LABEL_GRID_WIDTH = 3;
    public static final int IP_SERVER_LABEL_GRID_HEIGHT = 1;

    public static final int IP_SERVER_FIELD_GRID_X = 1;
    public static final int IP_SERVER_FIELD_GRID_Y = 2;
    public static final int IP_SERVER_FIELD_GRID_WIDTH = 3;
    public static final int IP_SERVER_FIELD_GRID_HEIGHT = 1;

    public static final int PORT_LABEL_GRID_X = 4;
    public static final int PORT_LABEL_GRID_Y = 1;
    public static final int PORT_LABEL_GRID_WIDTH = 1;
    public static final int PORT_LABEL_GRID_HEIGHT = 1;

    public static final int PORT_FIELD_GRID_X = 4;
    public static final int PORT_FIELD_GRID_Y = 2;
    public static final int PORT_FIELD_GRID_WIDTH = 1;
    public static final int PORT_FIELD_GRID_HEIGHT = 1;

    public static final int NICK_NAME_LABEL_GRID_X = 1;
    public static final int NICK_NAME_LABEL_GRID_Y = 3;
    public static final int NICK_NAME_LABEL_GRID_WIDTH = 4;
    public static final int NICK_NAME_LABEL_GRID_HEIGHT = 1;

    public static final int NICK_NAME_FIELD_GRID_X = 1;
    public static final int NICK_NAME_FIELD_GRID_Y = 4;
    public static final int NICK_NAME_FIELD_GRID_WIDTH = 4;
    public static final int NICK_NAME_FIELD_GRID_HEIGHT = 1;

    public static final int BUTTON_GRID_X = 2;
    public static final int BUTTON_GRID_Y = 5;
    public static final int BUTTON_GRID_WIDTH = 1;
    public static final int BUTTON_GRID_HEIGHT = 1;
}
