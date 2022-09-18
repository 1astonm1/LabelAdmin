package com.astonm.labelAdmin.dao.service.permission;

import com.astonm.labelAdmin.dao.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Mybatis-Plus Generation
 * @since 2022-09-12
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户Id查询生效中的用户信息
     *
     * @param userId 用户id
     * @return SysUser
     */
    SysUser getUserById(Long userId);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getUserByUsername(String username);

}
