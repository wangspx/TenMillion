package com.spwang.luck.tenmillion;
import com.spwang.luck.tenmillion.Repository.AllCombinationRepository;
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
public class InitData implements InitializingBean {
    @Resource
    private DoubleColorServiceImpl service;
    @org.springframework.beans.factory.annotation.Autowired
    private AllCombinationRepository allCombinationRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        service.generateAllCombination();
    }
}
