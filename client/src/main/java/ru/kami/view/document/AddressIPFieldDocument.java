package ru.kami.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import static ru.kami.view.constant.DocumentConstants.ADDRESS_IP_PATTERN;
import static ru.kami.view.constant.DocumentConstants.MAX_LENGTH_IP_ADDRESS;

/**
 * Документ фильтрующий вводимые значение в поле ввода ip address
 */
public class AddressIPFieldDocument extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (ADDRESS_IP_PATTERN.matcher(str).matches() && getLength() < MAX_LENGTH_IP_ADDRESS) {
            super.insertString(offs, str, a);
        }
    }
}
