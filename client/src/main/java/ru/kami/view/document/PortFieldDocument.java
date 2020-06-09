package ru.kami.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import static ru.kami.view.constant.DocumentConstants.MAX_LENGTH_PORT;
import static ru.kami.view.constant.DocumentConstants.PORT_PATTERN;

/**
 * Документ фильтрующий вводимые значение в поле ввода port
 */
public class PortFieldDocument extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (PORT_PATTERN.matcher(str).matches() && getLength() < MAX_LENGTH_PORT) {
            super.insertString(offs, str, a);
        }
    }
}
