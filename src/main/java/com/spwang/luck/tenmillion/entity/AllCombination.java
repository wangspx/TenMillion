package com.spwang.luck.tenmillion.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author spwang
 * @version 1.0.0
 * @date 2019/7/22 12:03
 */
@Data
public class AllCombination extends BaseEntity {
    /**
     * 6个红球和1个篮球的组合
     */
    private String combination;
}