package com.astonm.labelAdmin;

import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.dao.entity.SysUser;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserDTO;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.service.permission.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author astonm
 * @date 2022/9/18 16:37
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        SysUserDTO sysUser = new SysUserDTO();
        sysUser.setUserName("test");
        sysUser.setPhoneNumber("13988398883");
        sysUser.setPassword("aaawsssed==");
        sysUser.setEmail("1293847785@qq.com");
        sysUser.setRoleId(1);
        ResponseData resp = userService.createUser(sysUser);
        System.out.println(resp.getMsg());
    }

    public void updateUserTest() {

    }

    public void delUserTest() {

    }

    @Test
    public void UserListTest() {
        SysUserQueryDTO dto = new SysUserQueryDTO();
        dto.setPageNum(1);
        dto.setPageSize(10);
        ResponseData resp = userService.list(dto);
        Object data = resp.getData();
        System.out.println(data);
    }
}
