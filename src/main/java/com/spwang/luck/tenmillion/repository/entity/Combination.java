package com.spwang.luck.tenmillion.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author spwang
 * @date 2019/8/26 12:02
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Combination extends BaseEntity implements Serializable {
    /**
     * 组合排序
     */
    private Integer sort;
    /**
     * 6个红球和1个篮球的组合
     */
    private String combination;
}