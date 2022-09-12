package com.astonm.labelAdmin.dao.service.permission.imp;

import com.astonm.labelAdmin.common.constant.CommonConst;
import com.astonm.labelAdmin.dao.entity.SysUser;
import com.astonm.labelAdmin.dao.mapper.permission.SysUserMapper;
import com.astonm.labelAdmin.dao.service.permission.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Mybatis-Plus Generation
 * @since 2022-09-12
 */
@Service
public class SysUserServiceImp extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getUserById(Long userId) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, userId)
                .eq(SysUser::getValid, CommonConst.USER_NORMAL)
                .last("limit 1");
        return this.getOne(queryWrapper);
    }
}
