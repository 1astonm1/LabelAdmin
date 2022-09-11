package com.astonm.labelAdmin.service;

import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;

import java.util.List;

public interface UserService {

    List<SysUserListVO> list(SysUserQueryDTO dto);

}
