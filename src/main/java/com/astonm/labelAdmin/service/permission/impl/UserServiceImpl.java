package com.astonm.labelAdmin.service.permission.impl;

import com.astonm.labelAdmin.common.constant.CommonConst;
import com.astonm.labelAdmin.common.enums.BaseEnum;
import com.astonm.labelAdmin.common.enums.UserGroupEnums;
import com.astonm.labelAdmin.common.enums.UserStatusEnums;
import com.astonm.labelAdmin.common.response.PageBean;
import com.astonm.labelAdmin.common.response.ResponseData;
import com.astonm.labelAdmin.common.response.ResponsePageData;
import com.astonm.labelAdmin.common.utils.CheckUtils;
import com.astonm.labelAdmin.common.utils.DateUtils;
import com.astonm.labelAdmin.dao.entity.SysUser;
import com.astonm.labelAdmin.dao.mapper.permission.SysUserMapper;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserDTO;
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;
import com.astonm.labelAdmin.dao.service.permission.SysUserService;
import com.astonm.labelAdmin.service.permission.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author astonm
 * @date 2021/7/4
 * @description
 **/

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ResponseData list(SysUserQueryDTO dto) {
        List<SysUserListVO> result = new ArrayList<>();
        // 分页查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (!CheckUtils.isBlankOrNull(dto.getUsername())) {
            queryWrapper.like(SysUser::getUserName, dto.getUsername());
        }
        if (!CheckUtils.isBlankOrNull(dto.getEmail())) {
            queryWrapper.eq(SysUser::getEmail, dto.getEmail());
        }
        if (!CheckUtils.isBlankOrNull(dto.getSystemId())) {
            queryWrapper.eq(SysUser::getSystemId, dto.getSystemId());
        }
        if (!CheckUtils.isBlankOrNull(dto.getGroupId()) && dto.getSubGroupId() != null && !dto.getSubGroupId().isEmpty()) {
            queryWrapper.eq(SysUser::getGroupId, dto.getGroupId()).in(SysUser::getSubGroup, dto.getSubGroupId());

        }
        if (!CheckUtils.isBlankOrNull(dto.getStartTime()) && !CheckUtils.isBlankOrNull(dto.getEndTime())) {
            queryWrapper.between(SysUser::getCtime, dto.getStartTime(), dto.getEndTime());
        }
        if (!CheckUtils.isBlankOrNull(dto.getValid())) {
            queryWrapper.eq(SysUser::getValid, dto.getValid());
        }
        IPage<SysUser> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        IPage<SysUser> record = sysUserMapper.selectPage(iPage, queryWrapper);
        // 组装用户信息
        for (SysUser user : record.getRecords()) {
            SysUserListVO vo = new SysUserListVO();
            BeanUtils.copyProperties(user, vo);
            // todo 组别关系需要数据库维护
            vo.setGroupName(BaseEnum.getEnumName(UserGroupEnums.class, vo.getGroupId()));
            vo.setSubGroupName("");
            vo.setCtimeStr(DateUtils.formatDateTime(new Date(vo.getCtime())));
            vo.setUtimeStr(DateUtils.formatDate(new Date(vo.getUtime())));
            vo.setValidStr(BaseEnum.getEnumName(UserStatusEnums.class, vo.getValid()));
        }

        PageBean pageBean = new PageBean();
        pageBean.setTotal(record.getTotal());
        pageBean.setSize(dto.getPageSize());
        pageBean.setCurrent(dto.getPageNum());

        return ResponsePageData.success().setData(result).setPaging(pageBean);
    }

    @Override
    public ResponseData createUser(SysUserDTO dto) {
        // 校验用户信息

        // 创建用户对象并赋值
        SysUser sysUser = new SysUser();
        // 存储到数据库中
        return null;
    }

    @Override
    public ResponseData updateUser(SysUserDTO dto) {
        // 查出用户信息
        SysUser user = sysUserService.getUserById(dto.getId());
        if (user == null) {
            return ResponseData.failure("要修改的用户不存在或已被禁用");
        }
        Integer type = dto.getUpdateType();
        // 允许更新的字段赋值到对象中
        if (type.equals(CommonConst.USER_UPDATE_TYPE_INFORMATION)) {
            if (!CheckUtils.isBlankOrNull(dto.getUserName())) {
                user.setUserName(dto.getUserName());
            }
            if (!CheckUtils.isBlankOrNull(dto.getPhoneNumber())) {
                user.setPhoneNumber(dto.getPhoneNumber());
            }
            if (!CheckUtils.isBlankOrNull(dto.getEmail())) {
                user.setEmail(dto.getEmail());
            }
        } else if (type.equals(CommonConst.USER_UPDATE_TYPE_PASSWORD)) {
            if (!CheckUtils.isBlankOrNull(dto.getPassword())) {
                user.setPassword(dto.getPassword());
            }
        } else if (type.equals(CommonConst.USER_UPDATE_TYPE_GROUP)) {
            if (!CheckUtils.isBlankOrNull(dto.getGroupId())) {
                user.setGroupId(dto.getGroupId());
            }
            if (!CheckUtils.isBlankOrNull(dto.getSubGroup())) {
                user.setSubGroup(dto.getSubGroup());
            }
            if (!CheckUtils.isBlankOrNull(dto.getRoleId())) {
                user.setRoleId(dto.getRoleId());
            }
            if (!CheckUtils.isBlankOrNull(dto.getSystemId())) {
                user.setSystemId(dto.getSystemId());
            }
        }
        if (!CheckUtils.isBlankOrNull(dto.getUpdateUserName())) {
            user.setUpdateUser(dto.getUpdateUserName());
        }
        user.setUtime(DateUtils.getCurrentTimeStamp());
        // 存储更新信息
        boolean result = sysUserService.updateById(user);
        return result ? ResponseData.success("用户信息更新成功") : ResponseData.failure("用户信息更新失败，请稍后再试");
    }

    @Override
    public ResponseData deleteUser(Long userId, String opUser) {
        // 查出用户信息
        SysUser user = sysUserService.getUserById(userId);
        if (user == null) {
            return ResponseData.failure("要修改的用户不存在或已被禁用");
        }
        // 软删除
        user.setValid(CommonConst.USER_DELETE);
        user.setUtime(DateUtils.getCurrentTimeStamp());
        user.setUpdateUser(opUser);
        boolean result = sysUserService.updateById(user);
        return result ? ResponseData.success("用户禁用成功") : ResponseData.failure("用户禁用失败，请稍后再试");
    }

}
