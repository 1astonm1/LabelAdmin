package com.astonm.labelAdmin.service.permission;

import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserDTO;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;

public interface UserService {

    /**
     * 查询用户信息列表接口
     *
     * @param dto 用户查询信息dto
     * @return ResponseData
     */
    ResponseData list(SysUserQueryDTO dto);


    /**
     * 创建用户
     *
     * @param dto 创建用户信息dto
     * @return ResponseData
     */
    ResponseData createUser(SysUserDTO dto);

    /**
     * 更新用户信息
     *
     * @param dto 更新用户信息dto
     * @return ResponseData
     */
    ResponseData updateUser(SysUserDTO dto);


    /**
     * 禁用用户
     *
     * @param userId 禁用用户id
     * @param opUser
     * @return ResponseData
     */
    ResponseData deleteUser(Long userId, String opUser);

}
