package ru.kami.view.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.kami.view.constant.GlobalConstants.FONT;
import static ru.kami.view.constant.UserListConstants.*;

/**
 * Панель онлайн пользователей
 *
 * @author Kami
 */
class ChatUserListPanel extends JPanel {
    private final Map<String, JLabel> userMap = new HashMap<>();
    private final JPanel wrapper = new JPanel();
    private int currentHeight;

    ChatUserListPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        wrapper.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        wrapper.setBackground(USER_LIST_BACKGROUND_COLOR);
        JScrollPane scroll = new JScrollPane(wrapper,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JLabel header = new JLabel("online");
        header.setPreferredSize(new Dimension(PANEL_WIDTH, HEADER_PANEL_HEIGHT));
        header.setFont(FONT);
        header.setForeground(Color.PINK);
        wrapper.add(header);
        add(scroll);
        currentHeight = HEADER_PANEL_HEIGHT;
    }

    /**
     * Добавить пользователя на панель
     *
     * @param userName имя пользователя
     */
    synchronized void addUser(String userName) {
        JLabel label = new JLabel(userName);
        label.setPreferredSize(new Dimension(PANEL_WIDTH, USER_PANEL_HEIGHT));
        label.setFont(FONT);
        label.setForeground(Color.WHITE);
        wrapper.add(label);
        userMap.put(userName, label);
        currentHeight += USER_PANEL_HEIGHT;
    }

    /**
     * Удалить пользователя с панели
     *
     * @param userName имя пользователя
     */
    synchronized void removeUser(String userName) {
        userMap.get(userName).setVisible(false);
        userMap.remove(userName);
        currentHeight -= USER_PANEL_HEIGHT;
    }

    /**
     * Удалить всех пользователяей с панели
     */
    synchronized void removeAllUsers() {
        List<String> userNickNameList = new ArrayList<>();
        userMap.forEach((nickName, label) -> userNickNameList.add(nickName));
        userNickNameList.forEach(this::removeUser);
    }
}
