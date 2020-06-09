package ru.kami.view.entity;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.*;
import static ru.kami.view.constant.TextAreaConstants.AREA_HEIGHT;
import static ru.kami.view.constant.TextAreaConstants.AREA_WIDTH;

/**
 * Текстовое поле чата с сообщениями
 *
 * @author Kami
 */
public class ChatTextArea extends JTextArea {
    private int currentHeight = AREA_HEIGHT;

    public ChatTextArea() {
        setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFont(FONT);
        setForeground(Color.WHITE);
        setEditable(false);
        setLineWrap(true);
        DefaultCaret caret = (DefaultCaret) getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * Увеличить размер высоты текстового поля на размер шрифта текстового поля
     */
    public void addHeightTextArea() {
        //добавить 6 пикселей к шрифту из-за границ между сообщениями
        currentHeight += FONT_SIZE + 6;
        this.setPreferredSize(new Dimension(AREA_WIDTH, currentHeight));
    }
}
