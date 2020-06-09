package ru.kami.view.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * Хранит общие константы для всех документов
 *
 * @author Kami
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocumentConstants {
    public static final Pattern ADDRESS_IP_PATTERN = Pattern.compile("[\\d+.]+");
    public static final int MAX_LENGTH_IP_ADDRESS = 15;

    public static final Pattern PORT_PATTERN = Pattern.compile("\\d+");
    public static final int MAX_LENGTH_PORT = 5;

    public static final Pattern NICK_NAME_PATTERN = Pattern.compile("\\w+");
    public static final int MAX_LENGTH_NICK_NAME = 12;
}
