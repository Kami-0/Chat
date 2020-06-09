package ru.kami.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import static ru.kami.view.constant.DocumentConstants.MAX_LENGTH_NICK_NAME;
import static ru.kami.view.constant.DocumentConstants.NICK_NAME_PATTERN;

/**
 * Документ фильтрующий вводимые значение в поле ввода никнейма пользователя
 */
public class NickNameFieldDocument extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (NICK_NAME_PATTERN.matcher(str).matches() && getLength() < MAX_LENGTH_NICK_NAME) {
            super.insertString(offs, str, a);
        }
    }
}
