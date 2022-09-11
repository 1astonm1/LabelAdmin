package com.astonm.labelAdmin.dao.pojo.dto;

import com.astonm.labelAdmin.common.utils.CheckUtils;

/**
 * @author astonm
 * @date 2022/9/12 0:46
 * @description 包含分页信息的DTO
 */
public class BaseDTO {
    private Integer pageSize;

    private Integer pageNum;

    public Integer getOffset() {
        if (!CheckUtils.isBlankOrNull(this.pageNum) && !CheckUtils.isBlankOrNull(this.pageSize) && this.pageNum > 0) {
            return pageSize * (pageNum - 1);
        } else {
            return 0;
        }
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
