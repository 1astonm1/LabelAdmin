package com.astonm.labelAdmin.dao.pojo.vo.permission;

import lombok.Data;

/**
 * @author astonm
 * @date 2021/12/25
 * @description:
 **/
@Data
public class SysUserListVO {

    private Integer id;

    private String username;

    private Integer sex;

    private String sexStr;

    private Integer status;
}
