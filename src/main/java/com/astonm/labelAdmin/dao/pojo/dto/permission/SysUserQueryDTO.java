package com.astonm.labelAdmin.dao.pojo.dto.permission;

import lombok.Data;

/**
 * @author astonm
 * @date 2021/12/25
 * @description:
 **/
@Data
public class SysUserQueryDTO {
    private String username;

    private Integer sex;

    private Integer status;
}
