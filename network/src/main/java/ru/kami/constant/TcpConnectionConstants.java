package ru.kami.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TcpConnectionConstants {
    public static final Charset ENCODING = StandardCharsets.UTF_8;
}
