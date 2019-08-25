package com.spwang.luck.tenmillion.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author spwang
 * @version 1.0.0
 * @date 2019/7/22 12:03
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