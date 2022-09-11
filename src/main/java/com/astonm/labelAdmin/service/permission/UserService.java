package com.astonm.labelAdmin.service.permission;

import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;

import java.util.List;

public interface UserService {

    ResponseData list(SysUserQueryDTO dto);

}
