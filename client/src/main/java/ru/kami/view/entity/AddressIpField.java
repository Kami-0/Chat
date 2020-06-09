package ru.kami.view.entity;

import ru.kami.view.document.AddressIPFieldDocument;

import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.*;
import static ru.kami.view.constant.PreviewConstants.*;

/**
 * Текстовое поле для ввода ip адреса сервера
 */
public class AddressIpField extends PreviewTextField {
    public AddressIpField() {
        setPreferredSize(new Dimension(ADDRESS_IP_FIELD_WIDTH, ADDRESS_IP_FIELD_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setDocument(new AddressIPFieldDocument());
        setFont(FONT);
        setForeground(Color.WHITE);
        setText(IP_ADDRESS);
    }

    @Override
    public boolean isValidText() {
        return ADDRESS_IP_PATTERN.matcher(getText()).matches();
    }
}
