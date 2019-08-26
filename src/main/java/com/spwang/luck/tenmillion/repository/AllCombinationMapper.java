package com.spwang.luck.tenmillion.repository;

import com.spwang.luck.tenmillion.repository.entity.Combination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author spwang
 * @version 1.0.0
 * @date 2019/8/26 12:02
 */
@Mapper
public interface AllCombinationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Combination record);

    int insertSelective(Combination record);

    Combination selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Combination record);

    int updateByPrimaryKey(Combination record);

    Long count();

    int batchInsert(@Param("list") List<Combination> list);
}