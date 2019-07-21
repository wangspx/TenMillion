package com.spwang.luck.tenmillion.Repository;

import com.spwang.luck.tenmillion.entity.RedCombination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author spwang 2019-07-21 12:01
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface RedCombinationRepository {
    Long count();

    Long insertList(@Param("list") List<RedCombination> list);
}
