package ru.kami.view.ui;

import ru.kami.view.entity.ChatTextArea;

import javax.swing.*;
import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.GlobalConstants.FONT_SIZE;
import static ru.kami.view.constant.TextAreaConstants.AREA_HEIGHT;
import static ru.kami.view.constant.TextAreaConstants.AREA_WIDTH;

/**
 * Класс обертка для ChatTextArea в JPanel
 *
 * @author Kami
 */
class ChatTextAreaPanel extends JPanel {
    private final ChatTextArea chatTextArea;
    private int currentTextHeight = 0;

    ChatTextAreaPanel() {
        setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        this.chatTextArea = new ChatTextArea();

        JScrollPane scroll = new JScrollPane(chatTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        add(scroll);
    }

    void append(String string) {
        //добавить 6 пикселей к шрифту из-за границ между сообщениями
        currentTextHeight += FONT_SIZE + 6;
        if (currentTextHeight > AREA_HEIGHT - FONT_SIZE) {
            chatTextArea.addHeightTextArea();
        }
        chatTextArea.append(string);
        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
    }
}

