package com.spwang.luck.tenmillion;

import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.service.DoubleColorServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author spwang 2019/7/20 1:27 PM
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class InitializingData implements InitializingBean {

    /** 所有双色球组合数量 */
    private static final int ALL_COMBINATION_LENGTH = 17721088;

    @Resource
    private DoubleColorServiceImpl service;

    @Resource
    private AllCombinationMapper allCombinationMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (allCombinationMapper.count() != ALL_COMBINATION_LENGTH) {
            service.generateAllCombination();
        }
    }
}
