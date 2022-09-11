package com.astonm.labelAdmin.dao.pojo.vo.permission;

import com.astonm.labelAdmin.dao.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author astonm
 * @date 2021/12/25
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserListVO extends SysUser {
    private String groupName;

    private String subGroupName;

    private String ctimeStr;

    private String utimeStr;

    private String validStr;
}
