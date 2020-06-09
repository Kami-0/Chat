package ru.kami.view.entity;

import ru.kami.view.document.NickNameFieldDocument;

import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.GlobalConstants.FONT;
import static ru.kami.view.constant.PreviewConstants.*;

/**
 * Текстовое поле для ввода никнейма пользователя
 */
public class NickNameField extends PreviewTextField {
    public NickNameField() {
        setBackground(BACKGROUND_COLOR);
        setDocument(new NickNameFieldDocument());
        setFont(FONT);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(NICK_NAME_FIELD_WIDTH, NICK_NAME_FIELD_HEIGHT));
    }

    @Override
    public boolean isValidText() {
        return NICK_NAME_PATTERN.matcher(getText()).matches();
    }
}
