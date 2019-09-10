package com.spwang.luck.tenmillion.service;

/**
 * @author spwang Created on 2019/9/10 at 8:12
 * @version 1.0.0
 */
public abstract class DoubleColorSpiderService<T> implements SpiderService<T> {
    @Override
    public T execute() {
        String content = getSource();
        return handleResult(content);
    }

    public abstract String getSource();

    public abstract T handleResult(String content);
}
