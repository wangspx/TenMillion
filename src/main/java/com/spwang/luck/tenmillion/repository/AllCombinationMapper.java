package com.spwang.luck.tenmillion.repository;

import com.spwang.luck.tenmillion.repository.entity.AllCombination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author spwang 2019/7/20 1:16 PM
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface AllCombinationMapper {
    Long count();

    int deleteByPrimaryKey(Long id);

    int insert(AllCombination record);

    int insertSelective(AllCombination record);

    int insertList(@Param("list")List<AllCombination> list);

    AllCombination selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AllCombination record);

    int updateByPrimaryKey(AllCombination record);
}
