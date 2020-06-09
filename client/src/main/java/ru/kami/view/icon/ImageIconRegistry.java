package ru.kami.view.icon;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для получения иконок чата
 *
 * @author Kami
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageIconRegistry {
    private static final Map<String, Image> imageMap = new HashMap<>();
    private static final Map<String, ImageIcon> imageIconMap = new HashMap<>();

    static {
        imageMap.put("frameIcon", Toolkit.getDefaultToolkit().getImage(ImageIconRegistry.class.getResource("/icon/frameIcon.jpg")));
        imageIconMap.put("inputButton", new ImageIcon(ImageIconRegistry.class.getResource("/icon/inputButton.png")));
    }

    /**
     * Метод возвращает иконку по ее коду
     *
     * @param code код иконки
     * @return optional с иконкой
     */
    public static Optional<Image> getCellImageMap(String code) {
        return Optional.ofNullable(imageMap.get(code));
    }

    public static Optional<ImageIcon> getCellImageIconMap(String code) {
        return Optional.ofNullable(imageIconMap.get(code));
    }
}
