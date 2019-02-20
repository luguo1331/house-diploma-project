package com.sise.house.house.api.constants;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 21:14
 */
@Data
public class HouseSuggest implements Serializable {
    private static final long serialVersionUID = 5409325905201513857L;
    private String input;
    /**
     * 默认权重
     */
    private int weight = 10;
}
