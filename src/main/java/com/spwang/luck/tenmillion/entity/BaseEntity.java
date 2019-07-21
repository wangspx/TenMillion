package com.spwang.luck.tenmillion.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author spwang 2019-07-21 16:18
 * @version 0.0.1
 * @since 0.0.1
 */
@Data
public class BaseEntity {
    /** 自增长主键 */
    protected Long id;
    /** 创建时间 */
    protected Date createTime;
    /** 更新时间 */
    protected Date updateTime = new Date();
    /** 数据状态 0：可用，其余不可用 */
    protected Integer status = 0;
}
