package ru.kami.view.entity;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.GlobalConstants.FONT;
import static ru.kami.view.constant.PreviewConstants.*;

public abstract class PreviewTextField extends JTextField {
    PreviewTextField() {
        setBackground(BACKGROUND_COLOR);
        setFont(FONT);
        setForeground(Color.WHITE);
        cancelWarning();
    }

    public void onWarning() {
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, WARNING_EMPTY_BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(
                        WARNING_EMPTY_BORDER_TOP,
                        WARNING_EMPTY_BORDER_LEFT,
                        WARNING_EMPTY_BORDER_BOTTOM,
                        WARNING_EMPTY_BORDER_RIGHT)));
    }

    public void cancelWarning() {
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(
                        EMPTY_BORDER_TOP,
                        EMPTY_BORDER_LEFT,
                        EMPTY_BORDER_BOTTOM,
                        EMPTY_BORDER_RIGHT)));
    }

    public abstract boolean isValidText();
}
