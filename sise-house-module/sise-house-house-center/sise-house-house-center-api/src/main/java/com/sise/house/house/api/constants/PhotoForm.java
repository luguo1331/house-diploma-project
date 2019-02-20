package com.sise.house.house.api.constants;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 15:20
 */
@Data
public class PhotoForm implements Serializable {
    private static final long serialVersionUID = -2294817542809826199L;
    private String path;

    private int width;

    private int height;
}
