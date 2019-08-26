package com.spwang.luck.tenmillion.service;

/**
 * @author spwang 2019-07-21 11:08
 * @version 0.0.1
 * @since 0.0.1
 */
public interface DoubleColorService {

    void generateRedCombination();
    /**
     * 生成双色球所有的组合可能性，并保存到数据
     */
    void generateAllCombination();
}
