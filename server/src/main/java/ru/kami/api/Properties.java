package ru.kami.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Properties {
    private static final int PORT_DEFAULT = 8189;
    private static final int PORT_MIN = 0;
    private static final int PORT_MAX = 65535;
    private static final String PATH_TO_PROPERTIES = Properties.class.getResource("/server.properties").getPath();
    private static final java.util.Properties properties = new java.util.Properties();
    @Getter
    private static int port = PORT_DEFAULT;

    static {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            port = Integer.parseInt(properties.getProperty("server.port"));
            if (port < PORT_MIN || port > PORT_MAX) {
                log.error("Not valid parameters PORT: {}", port);
                port = PORT_DEFAULT;
            }
        } catch (IOException e) {
            log.error("Could not open server.properties file", e);
        } catch (NumberFormatException e) {
            log.error("Not valid parameters PORT: {}", properties.getProperty("server.port"));
        }
    }

}
