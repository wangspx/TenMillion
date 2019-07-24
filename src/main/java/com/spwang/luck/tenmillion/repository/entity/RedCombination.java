package com.spwang.luck.tenmillion.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author spwang 2019-07-21 12:10
 * @version 0.0.1
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedCombination extends BaseEntity {
    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;
}
