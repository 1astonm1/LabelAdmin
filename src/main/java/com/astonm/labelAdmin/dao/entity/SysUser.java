package com.astonm.labelAdmin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Mybatis-Plus Generation
 * @since 2022-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 密码（md5加密后）
     */
    @TableField("password")
    private String password;

    /**
     * 用户邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 组别id
     */
    @TableField("group_id")
    private Integer groupId;

    /**
     * 子组别
     */
    @TableField("sub_group")
    private Integer subGroup;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 所属系统 1-图片标注
     */
    @TableField("system_id")
    private Integer systemId;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Integer lastLoginTime;

    /**
     *  创建时间
     */
    @TableField("ctime")
    private Integer ctime;

    /**
     * 更新时间
     */
    @TableField("utime")
    private Integer utime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 最后修改人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 是否禁用 1-正常 2-禁用
     */
    @TableField("valid")
    private Integer valid;


}
