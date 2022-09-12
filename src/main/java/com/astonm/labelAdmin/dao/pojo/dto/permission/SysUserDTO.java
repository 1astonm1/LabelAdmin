package com.astonm.labelAdmin.dao.pojo.dto.permission;

import lombok.Data;

/**
 * @author astonm
 * @date 2022/9/12 19:27
 * @description 用户信息创建、修改dto
 */
@Data
public class SysUserDTO {
    /**
     *  id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 密码（md5加密后）
     */
    private String password;

    /**
     * 用户邮箱地址
     */
    private String email;

    /**
     * 组别id
     */
    private Integer groupId;

    /**
     * 子组别
     */
    private Integer subGroup;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 所属系统 1-图片标注
     */
    private Integer systemId;

    /**
     * 最后登录时间
     */
    private Integer lastLoginTime;

    /**
     *  创建时间
     */
    private Integer ctime;

    /**
     * 更新时间
     */
    private Integer utime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 最后修改人
     */
    private String updateUser;

    /**
     * 是否禁用 1-正常 2-禁用
     */
    private Integer valid;

    /**
     *  修改类型 1-用户更新自己的用户信息 2-用户修改密码 3-管理员修改用户组别、子组别、所属系统等信息
     */
    private Integer updateType;

    /**
     *  修改操作人
     */
    private String updateUserName;
}
