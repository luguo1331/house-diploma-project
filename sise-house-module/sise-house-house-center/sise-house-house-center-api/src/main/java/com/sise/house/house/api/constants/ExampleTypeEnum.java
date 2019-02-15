package com.sise.house.house.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 枚举类示例
 * @Auther: 冲之
 * @Date: 2019/1/30 16:10
 */
@Getter
@AllArgsConstructor
public enum ExampleTypeEnum {
    /**
     * 示例
     */
    EXAMPLE_TYPE_ENUM(0, "EXAMPLE_TYPE_ENUM");

    private Integer code;
    private String text;
}
