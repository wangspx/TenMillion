package com.spwang.luck.tenmillion.service;

/**
 * @author spwang Created on 2019/9/10 at 8:04
 * @version 1.0.0
 */
public interface SpiderService<T> {
    /**
     * 爬虫获取数据
     *
     * @return 返回处理后的数据
     */
    T execute();
}
