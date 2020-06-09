package ru.kami.view.entity;

import ru.kami.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.GlobalConstants.FONT;
import static ru.kami.view.constant.InputTextFieldConstants.FIELD_HEIGHT;
import static ru.kami.view.constant.InputTextFieldConstants.FIELD_WIDTH;

/**
 * Текстовое поля ввода сообщений клиентом в чат
 *
 * @author Kami
 */
public final class ChatInputTextField extends JTextField implements ActionListener {
    private final Controller controller;

    public ChatInputTextField(Controller controller) {
        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        setFont(FONT);
        setForeground(Color.WHITE);
        setBackground(BACKGROUND_COLOR);
        addActionListener(this);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sendMessage();
    }

    /**
     * Метод для отправки сообщения на сервер из поля ввода
     */
    public void sendMessage() {
        String msg = ChatInputTextField.this.getText();
        if (msg.isEmpty()) {
            return;
        }
        this.setText(null);
        controller.handleUserClickedOnSendToMessage(msg);
    }
}
