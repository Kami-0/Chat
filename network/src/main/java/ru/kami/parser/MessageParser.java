package ru.kami.parser;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.kami.code.CodeEnum;

import static ru.kami.constant.MessageParserConstants.LINE_START_INDEX;

/**
 * Класс для парсинга строки на код и сообщение
 *
 * @author Kami
 */
@Slf4j
@Getter
public final class MessageParser {
    private static final String EMPTY_STRING = "";
    private final CodeEnum code;
    private final String message;

    public MessageParser(String value) {
        this.code = getCodeFromValue(value);
        this.message = getMessageFromValue(value, code);
    }

    /**
     * @param value строка из которой нужно достать код
     * @return код сообщения
     */
    private CodeEnum getCodeFromValue(String value) {
        if (value == null) {
            return CodeEnum.EXCEPTION;
        }
        int indexCode = value.indexOf(CodeEnum.getCodeEnding());
        //Если не нашлось окончания кода в сообщении
        if (indexCode == -1) {
            return CodeEnum.EXCEPTION;
        }
        String codeString = value.substring(LINE_START_INDEX, indexCode);
        CodeEnum code;
        try {
            code = CodeEnum.valueOf(codeString);
        } catch (IllegalArgumentException e) {
            return CodeEnum.EXCEPTION;
        }
        return code;
    }

    /**
     * @param value строка от которой нужно отделить код
     * @param code  код который надо отделить
     * @return сообщение без кода
     */
    private String getMessageFromValue(String value, CodeEnum code) {
        if (code.equals(CodeEnum.EXCEPTION)) {
            return EMPTY_STRING;
        }
        String codeInMessage = code.getValueInMessage();
        return value.replace(codeInMessage, EMPTY_STRING);
    }
}
