package ru.kami.view.entity;

import lombok.extern.slf4j.Slf4j;
import ru.kami.view.icon.ImageIconRegistry;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.InputButtonConstants.*;

/**
 * Кнопка для отправки текста из текстового поля ввода на сервер
 */
@Slf4j
public final class ChatInputButton extends JButton {
    public ChatInputButton(ChatInputTextField chatInputTextField) {
        Optional<ImageIcon> imageOptional = ImageIconRegistry.getCellImageIconMap(ICON_CODE);
        if (imageOptional.isPresent()) {
            setIcon(imageOptional.get());
        } else {
            log.error("Ошибка в получении frameIcon");
        }
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addActionListener(e -> chatInputTextField.sendMessage());
    }
}
