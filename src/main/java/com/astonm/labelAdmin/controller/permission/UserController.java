package com.astonm.labelAdmin.controller.permission;

import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.common.utils.CheckUtils;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserDTO;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.service.permission.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author astonm
 * @date 2022/09/12
 * @description 用户管理模块接口
 **/
@Api(tags = "用户管理模块")
@Slf4j
@RestController
@RequestMapping("/label/permission/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户查询接口", httpMethod = "POST")
    @PostMapping("/list")
    public ResponseData pageList(@RequestBody SysUserQueryDTO dto) {
        try {
            if (CheckUtils.isBlankOrNull(dto.getPageSize()) || CheckUtils.isBlankOrNull(dto.getPageNum())) {
                return ResponseData.failure("缺少分页信息");
            }
            return userService.list(dto);
        } catch (Exception e) {
            log.error("用户查询接口异常", e);
            return ResponseData.failure("用户查询异常，请稍后再试");
        }
    }

    @ApiOperation(value = "用户创建接口", httpMethod = "POST")
    @PostMapping("/create")
    public ResponseData create(@RequestBody SysUserDTO dto) {
        try {
            return userService.createUser(dto);
        } catch (Exception e) {
            log.error("用户创建接口异常", e);
            return ResponseData.failure("用户创建异常，请稍后再试");
        }
    }

    @ApiOperation(value = "用户信息更新接口", httpMethod = "POST")
    @PostMapping("/update")
    public ResponseData update(@RequestBody SysUserDTO dto) {
        try {
            if (CheckUtils.isBlankOrNull(dto.getId()) || CheckUtils.isBlankOrNull(dto.getUpdateType())) {
                return ResponseData.failure("缺少必要信息");
            }
            return userService.updateUser(dto);
        } catch (Exception e) {
            log.error("用户更新接口异常", e);
            return ResponseData.failure("用户更新异常，请稍后再试");
        }
    }

    @ApiOperation(value = "用户禁用（软删除）接口", httpMethod = "GET")
    @GetMapping("/delete")
    public ResponseData delete(@RequestParam Long userId, @RequestParam String opUser) {
        try {
            if (CheckUtils.isBlankOrNull(userId) || CheckUtils.isBlankOrNull(opUser)) {
                return ResponseData.failure("缺少用户信息");
            }
            return userService.deleteUser(userId, opUser);
        } catch (Exception e) {
            log.error("用户更新接口异常", e);
            return ResponseData.failure("用户更新异常，请稍后再试");
        }
    }
}
