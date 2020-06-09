package ru.kami.view.ui;

import ru.kami.controller.Controller;
import ru.kami.view.entity.AddressIpField;
import ru.kami.view.entity.NickNameField;
import ru.kami.view.entity.PortField;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.WEST;
import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;
import static ru.kami.view.constant.PreviewConstants.*;

class PreviewPanel extends JPanel {
    private final Controller controller;
    private final AddressIpField addressIpField;
    private final PortField portField;
    private final NickNameField nickNameField;

    PreviewPanel(Controller controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(PREVIEW_WIDTH, PREVIEW_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setVisible(false);
        JLabel addressIpLabel = new JLabel("IP ADDRESS");
        JLabel portLabel = new JLabel("PORT");
        JLabel nickNameLabel = new JLabel("NICKNAME");
        this.addressIpField = new AddressIpField();
        this.portField = new PortField();
        this.nickNameField = new NickNameField();
        JButton button = new JButton("connect");
        button.addActionListener(e -> connectToServer());
        add(addressIpLabel, getContainerGridBagConstrains(
                IP_SERVER_LABEL_GRID_X,
                IP_SERVER_LABEL_GRID_Y,
                IP_SERVER_LABEL_GRID_WIDTH,
                IP_SERVER_LABEL_GRID_HEIGHT));
        add(addressIpField, getContainerGridBagConstrains(
                IP_SERVER_FIELD_GRID_X,
                IP_SERVER_FIELD_GRID_Y,
                IP_SERVER_FIELD_GRID_WIDTH,
                IP_SERVER_FIELD_GRID_HEIGHT));
        add(portLabel, getContainerGridBagConstrains(
                PORT_LABEL_GRID_X,
                PORT_LABEL_GRID_Y,
                PORT_LABEL_GRID_WIDTH,
                PORT_LABEL_GRID_HEIGHT));
        add(portField, getContainerGridBagConstrains(
                PORT_FIELD_GRID_X,
                PORT_FIELD_GRID_Y,
                PORT_FIELD_GRID_WIDTH,
                PORT_FIELD_GRID_HEIGHT));
        add(nickNameLabel, getContainerGridBagConstrains(
                NICK_NAME_LABEL_GRID_X,
                NICK_NAME_LABEL_GRID_Y,
                NICK_NAME_LABEL_GRID_WIDTH,
                NICK_NAME_LABEL_GRID_HEIGHT));
        add(nickNameField, getContainerGridBagConstrains(
                NICK_NAME_FIELD_GRID_X,
                NICK_NAME_FIELD_GRID_Y,
                NICK_NAME_FIELD_GRID_WIDTH,
                NICK_NAME_FIELD_GRID_HEIGHT));
        add(button, getContainerGridBagConstrains(
                BUTTON_GRID_X,
                BUTTON_GRID_Y,
                BUTTON_GRID_WIDTH,
                BUTTON_GRID_HEIGHT));
    }

    /**
     * Метод генерирует формат расположения для GridBagLayout
     *
     * @param gridX      задать расположение по оси х
     * @param gridY      задать расположение по оси y
     * @param gridWidth  задать размер элемента по оси x
     * @param gridHeight задать размер элемента по оси y
     * @return формат расположения для элемента swing
     */
    private GridBagConstraints getContainerGridBagConstrains(int gridX, int gridY, int gridWidth, int gridHeight) {
        return new GridBagConstraints(gridX, gridY, gridWidth, gridHeight,
                WEIGHT_X, WEIGHT_Y, WEST, WEST,
                new Insets(INSETS_TOP, INSETS_LEFT, INSETS_BOTTOM, INSETS_RIGHT),
                IPAD_X, IPAD_Y);
    }

    private synchronized void connectToServer() {
        addressIpField.cancelWarning();
        portField.cancelWarning();
        nickNameField.cancelWarning();
        if (!portField.isValidText()) {
            portField.onWarning();
            return;
        }
        if (!addressIpField.isValidText()) {
            addressIpField.onWarning();
            return;
        }
        if (!nickNameField.isValidText()) {
            nickNameField.onWarning();
            return;
        }
        controller.handleUserClickedOnConnectionToServer(
                addressIpField.getText(),
                Integer.parseInt(portField.getText()),
                nickNameField.getText());
    }
}
