package com.sise.house.house.api.constants;

/**
 * @Description: 行政级别 枚举类
 * @Auther: 冲之
 * @Date: 2019/2/18 15:28
 */
public enum Level {
    CITY("city"),
    REGION("region");

    private String value;

    Level(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Level of(String value) {
        for (Level level : Level.values()) {
            if (level.getValue().equals(value)) {
                return level;
            }
        }

        throw new IllegalArgumentException();
    }
}
