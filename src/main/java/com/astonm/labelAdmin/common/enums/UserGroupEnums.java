package com.astonm.labelAdmin.common.enums;

import lombok.Getter;

/**
 * @author astonm
 * @date 2022/9/12 1:43
 * @description
 */
@Getter
public enum UserGroupEnums implements BaseEnum {
    /**
     *  内部组
     */
    INTERNAL(1, "内部组"),
    ;

    private Integer code;
    private String name;

    UserGroupEnums(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
