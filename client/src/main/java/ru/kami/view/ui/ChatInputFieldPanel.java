package ru.kami.view.ui;

import ru.kami.controller.Controller;
import ru.kami.view.entity.ChatInputButton;
import ru.kami.view.entity.ChatInputTextField;

import javax.swing.*;
import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.InputTextFieldConstants.PANEL_HEIGHT;
import static ru.kami.view.constant.InputTextFieldConstants.PANEL_WIDTH;

/**
 * Класс обертка для ChatInputTextField в JPanel
 *
 * @author Kami
 */
class ChatInputFieldPanel extends JPanel {
    ChatInputFieldPanel(Controller controller) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        ChatInputTextField chatInputTextField = new ChatInputTextField(controller);
        add(chatInputTextField);
        ChatInputButton chatInputButton = new ChatInputButton(chatInputTextField);
        add(chatInputButton);
    }

}
