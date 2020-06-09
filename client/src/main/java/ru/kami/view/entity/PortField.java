package ru.kami.view.entity;

import ru.kami.view.document.PortFieldDocument;

import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.*;
import static ru.kami.view.constant.PreviewConstants.*;

/**
 * Текстовое поле для ввода порта сервера
 */
public class PortField extends PreviewTextField {
    public PortField() {
        setBackground(BACKGROUND_COLOR);
        setDocument(new PortFieldDocument());
        setFont(FONT);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(PORT_FIELD_WIDTH, PORT_FIELD_HEIGHT));
        setText(String.valueOf(PORT));
    }

    @Override
    public boolean isValidText() {
        return PORT_PATTERN.matcher(getText()).matches();
    }
}
