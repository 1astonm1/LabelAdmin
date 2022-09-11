package com.astonm.labelAdmin.controller.permission;

import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.common.utils.CheckUtils;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;
import com.astonm.labelAdmin.service.permission.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author astonm
 * @date 2022/09/12
 * @description 用户管理模块接口
 **/
@Api(tags = "用户管理模块")
@Slf4j
@RestController
@RequestMapping("/base/permission/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户查询接口", httpMethod = "POST")
    @PostMapping("/list")
    public ResponseData pageList(@RequestBody SysUserQueryDTO dto) {
        if (!CheckUtils.isBlankOrNull(dto.getPageSize()) || !CheckUtils.isBlankOrNull(dto.getPageNum())) {
            return ResponseData.failure("缺少分页信息");
        }
        return userService.list(dto);
    }
}
