package com.spwang.luck.tenmillion.service;

import com.alibaba.fastjson.JSON;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author spwang 2019/7/20 12:56 PM
 * @version 0.0.2
 * @since 0.0.1
 */
@Slf4j
@Service("doubleColorServiceImpl")
public class DoubleColorServiceImpl implements DoubleColorService {

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    @Resource
    private KafkaTemplate<String, Combination> kafkaTemplate;

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate2;

    @Override
    public void generateRedCombination() {
        new RedCombination() {
            @Override
            void resultHandler(Integer index, List<String> Combination) {
                kafkaTemplate2.send("red", index, StringUtils.join(Combination, ""));
            }
        }.generate();
    }

    @Override
    public void generateAllCombination() {
        new RedCombination() {
            @Override
            void resultHandler(Integer index, List<String> Combination) {
                int currentIndex = index * 16;
                String red = StringUtils.join(Combination, "");

                for (int j = 1; j <= 16; j++) {
                    Combination entity = new Combination();
                    entity.setSort(currentIndex++);
                    entity.setCombination(String.format("%s%02d", red, j));
                    entity.setCreateTime(new Date());
                    fixedThreadPool.execute(() -> {
                        kafkaTemplate.send("luck", entity);
                    });
                }
            }
        }.generate();
    }
}
