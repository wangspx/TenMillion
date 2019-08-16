package com.spwang.luck.tenmillion.repository;

import com.spwang.luck.tenmillion.repository.entity.History;

/**
 * 
 *
 * @author spwang
 * @date 2019/8/14 11:39
 * @version 1.0.0
 */
public interface HistoryMapper {
    int insert(History record);

    int insertSelective(History record);
}