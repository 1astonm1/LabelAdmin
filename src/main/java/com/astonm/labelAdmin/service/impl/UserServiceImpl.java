package com.astonm.labelAdmin.service.impl;

import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;
import com.astonm.labelAdmin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author astonm
 * @date 2021/7/4
 * @description
 **/

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public List<SysUserListVO> list(SysUserQueryDTO dto) {

        return null;
    }
}
