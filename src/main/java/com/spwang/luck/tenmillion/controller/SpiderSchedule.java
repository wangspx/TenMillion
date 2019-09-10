package com.spwang.luck.tenmillion.controller;

import com.spwang.luck.tenmillion.repository.entity.History;
import com.spwang.luck.tenmillion.service.DoubleColorSpiderService;
import com.spwang.luck.tenmillion.service.SpiderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author spwang Created on 2019/9/10 at 8:39
 * @version 1.0.0
 */
@Configuration
public class SpiderSchedule {

    @Scheduled
    public void DoubleColorSpider() {
        SpiderService<History> spiderService = new DoubleColorSpiderService<History>() {

            @Override
            public String getSource() {
                return null;
            }

            @Override
            public History handleResult(String source) {
                return null;
            }
        };
        spiderService.execute();
    }
}
