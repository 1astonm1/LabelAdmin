package com.astonm.labelAdmin.service.permission.impl;

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
import com.astonm.labelAdmin.dao.pojo.dto.permission.SysUserQueryDTO;
import com.astonm.labelAdmin.dao.pojo.vo.permission.SysUserListVO;
import com.astonm.labelAdmin.service.permission.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
}
