package org.example.juc_YangGe.more;

import lombok.Getter;

/**
 * @Author: Derek
 * @DateTime: 2020/9/4 15:54
 * @Description: Test Enum
 */
public enum T_Enum {

    ONE(1,"魏国"),
    TWO(2,"蜀国"),
    THREE(3,"吴国");

    @Getter private Integer code;
    @Getter private String name;

    T_Enum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
