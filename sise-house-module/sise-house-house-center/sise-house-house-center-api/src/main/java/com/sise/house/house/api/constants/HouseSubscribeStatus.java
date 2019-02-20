package com.sise.house.house.api.constants;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/20 01:16
 */
public enum HouseSubscribeStatus {
    NO_SUBSCRIBE(0), // 未预约
    IN_ORDER_LIST(1), // 已加入待看清单
    IN_ORDER_TIME(2), // 已经预约看房时间
    FINISH(3),// 已完成预约
    CANCEL_ORDER(4);

    private int value;

    HouseSubscribeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HouseSubscribeStatus of(int value) {
        for (HouseSubscribeStatus status : HouseSubscribeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return HouseSubscribeStatus.NO_SUBSCRIBE;
    }
}
