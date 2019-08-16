package com.spwang.luck.tenmillion.repository.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 *
 * @author spwang
 * @date 2019/8/14 11:39
 * @version 1.0.0
 */
@Data
public class History extends BaseEntity {
    /**
    * 期号
    */
    private Integer number;

    /**
    * 组合id
    */
    private Integer combinationId;

    /**
    * 组合
    */
    private String combination;

    /**
    * 开奖日期
    */
    private LocalDateTime openDate;
}