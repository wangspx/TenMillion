package com.spwang.luck.tenmillion.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author spwang 2019/7/20 12:51 PM
 * @version 0.0.1
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class AllCombination extends BaseEntity {
    /** 6个红球和1个篮球的组合 */
    private String combination;
}
