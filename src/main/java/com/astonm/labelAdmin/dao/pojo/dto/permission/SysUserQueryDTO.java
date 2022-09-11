package com.astonm.labelAdmin.dao.pojo.dto.permission;

import com.astonm.labelAdmin.dao.pojo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author astonm
 * @date 2021/12/25
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserQueryDTO extends BaseDTO {
    private String username;

    private String email;

    private String groupId;

    private Integer systemId;

    private List<Integer> subGroupId;

    private Integer startTime;

    private Integer endTime;

    private Integer valid;
}
