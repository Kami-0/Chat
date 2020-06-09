package ru.kami.view.ui;

import lombok.extern.slf4j.Slf4j;
import ru.kami.controller.Controller;
import ru.kami.view.icon.ImageIconRegistry;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

import static java.awt.GridBagConstraints.WEST;
import static ru.kami.view.constant.ChatWindowConstants.*;
import static ru.kami.view.constant.GlobalConstants.BACKGROUND_COLOR;

@Slf4j
public class ChatWindow extends JFrame implements ChatView {
    private final Controller controller;
    private final ChatTextAreaPanel chatTextAreaPanel;
    private final JPanel chatContainer;
    private PreviewPanel previewContainer;
    private boolean isVisiblePreview = false;
    private ChatInputFieldPanel chatInputFieldPanel;
    private ChatUserListPanel chatUserListPanel;

    public ChatWindow(Controller controller) {
        this.controller = controller;
        renderFrame();
        renderPreviewContainer();
        setPreviewContainerVisibility(true);
        setVisible(true);
        this.chatContainer = new JPanel(new GridBagLayout());
        this.chatTextAreaPanel = new ChatTextAreaPanel();
    }

    /**
     * Метод рендерит фрейм чата
     */
    private void renderFrame() {
        log.info("Frame rendering started ..");
        setTitle(APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(LOCATION_X, LOCATION_Y);
        setResizable(false);
        setBackground(BACKGROUND_COLOR);
        Optional<Image> imageOptional = ImageIconRegistry.getCellImageMap(FRAME_ICON_CODE);
        if (imageOptional.isPresent()) {
            setIconImage(imageOptional.get());
        } else {
            log.error("Ошибка в получении frameIcon");
        }
        log.info("Frame rendering has ended ..");
    }

    /**
     * Метод рендерит превью чата
     */
    private void renderPreviewContainer() {
        log.info("Preview rendering started ..");
        previewContainer = new PreviewPanel(controller);
        add(previewContainer);
        log.info("Preview rendering has ended ..");
    }


    /**
     * Метод настраивает видимость previewContainer
     *
     * @param visibility true если включить видимость, false выключить
     */
    private void setChatContainerVisibility(boolean visibility) {
        chatContainer.setVisible(visibility);
        pack();
        log.info("Set chat visibility: " + visibility);
    }

    /**
     * Метод настраивает видимость chatContainer
     *
     * @param visibility true если включить видимость, false выключить
     */
    private void setPreviewContainerVisibility(boolean visibility) {
        previewContainer.setVisible(visibility);
        pack();
        isVisiblePreview = visibility;
        log.info("Set preview visibility: " + visibility);
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

    /**
     * Метод при ошибке подключения к серверу
     */
    @Override
    public void failedToConnectToServer() {
        JOptionPane.showMessageDialog(previewContainer, "Could not connect to server", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Метод рендерит чат
     */
    private void renderChatContainer() {
        log.info("Chat rendering started ..");
        chatUserListPanel = new ChatUserListPanel();
        chatContainer.add(chatTextAreaPanel, getContainerGridBagConstrains(
                TEXT_AREA_GRID_X,
                TEXT_AREA_GRID_Y,
                TEXT_AREA_GRID_WIDTH,
                TEXT_AREA_GRID_HEIGHT));
        chatContainer.add(chatInputFieldPanel, getContainerGridBagConstrains(
                INPUT_FIELD_GRID_X,
                INPUT_FIELD_GRID_Y,
                INPUT_FIELD_GRID_WIDTH,
                INPUT_FIELD_GRID_HEIGHT));
        chatContainer.add(chatUserListPanel, getContainerGridBagConstrains(
                USER_LIST_GRID_X,
                USER_LIST_GRID_Y,
                USER_LIST_GRID_WIDTH,
                USER_LIST_GRID_HEIGHT));
        chatContainer.setVisible(false);
        add(chatContainer);
        log.info("Chat rendering has ended ..");
    }

    /**
     * Метод если произошло подключение к серверу
     */
    @Override
    public void onConnectionReady() {
        chatInputFieldPanel = new ChatInputFieldPanel(controller);
    }

    /**
     * Метод на удаление всех юзеров из списка юзеров
     */
    @Override
    public void onDisconnectAllUsers() {
        chatUserListPanel.removeAllUsers();
    }

    /**
     * Метод печатающий сообщение в окне чата
     *
     * @param message сообщение
     */
    @Override
    public synchronized void printMessage(String message) {
        SwingUtilities.invokeLater(() -> chatTextAreaPanel.append(message + System.lineSeparator()));
    }

    /**
     * Метод если занят никнейм на сервере
     */
    @Override
    public void onBusyNickname() {
        JOptionPane.showMessageDialog(previewContainer, "Nickname is busy", "Warning", JOptionPane.WARNING_MESSAGE);
        if (!isVisiblePreview) {
            setPreviewContainerVisibility(true);
            setChatContainerVisibility(false);
        }
    }

    /**
     * Метод если авторизация на сервере успешна
     */
    @Override
    public void onSuccessfulAuthorization() {
        renderChatContainer();
        setPreviewContainerVisibility(false);
        setChatContainerVisibility(true);
    }

    /**
     * Метод на добавление нового юзера с писок юзеров
     *
     * @param nickName никнейм юзера
     */
    @Override
    public void addUserOnline(String nickName) {
        chatUserListPanel.addUser(nickName);
    }

    /**
     * Метод на удаление юзера из списка юзеров
     *
     * @param nickName никнейм юзера
     */
    @Override
    public void addUserOffline(String nickName) {
        chatUserListPanel.removeUser(nickName);
    }

}
