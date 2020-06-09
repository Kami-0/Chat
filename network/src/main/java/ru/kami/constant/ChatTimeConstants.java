package ru.kami.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChatTimeConstants {
    public static final int MILLISECONDS_PER_HOUR = 3_600_000;
    public static final int YEAR_INDEX = 0;
    public static final int MONTH_INDEX = 1;
    public static final int WEEK_OF_MONTH_INDEX = 2;
    public static final int HOUR_INDEX = 3;
    public static final int MINUTE_INDEX = 4;
    public static final int SECOND_INDEX = 5;
}
