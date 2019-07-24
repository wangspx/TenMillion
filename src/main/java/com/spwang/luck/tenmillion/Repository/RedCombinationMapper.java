package com.spwang.luck.tenmillion.Repository;

import com.spwang.luck.tenmillion.Repository.entity.RedCombination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author spwang 2019-07-21 12:01
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface RedCombinationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RedCombination record);

    int insertSelective(RedCombination record);

    int insertList(@Param("list")List<RedCombination> list);

    RedCombination selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedCombination record);

    int updateByPrimaryKey(RedCombination record);

	Long count();
}
