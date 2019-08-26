package com.spwang.luck.tenmillion.service;

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
@Service
public class DoubleColorServiceImpl implements DoubleColorService {

    private Integer index = 1;

    private ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    @Resource
    private KafkaTemplate<String, Combination> kafkaTemplate;

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate2;

    @Override
    public void generateRedCombination() {
        final Integer i = 0;
        new RedCombination() {
            @Override
            void resultHandler(List<String> Combination) {
                log.info(StringUtils.join(Combination, ""));
//                kafkaTemplate2.send("red", i[0]++, StringUtils.join(Combination, ""));
            }
        }.generate();
    }

    @Override
    public void generateAllCombination() {
//        list.forEach(red -> {
//            fixedThreadPool.execute(()-> {
//                for (int j = 1; j <= 16; j++) {
//                    Combination entity = new Combination();
//                    entity.setSort(index++);
//                    entity.setCombination(String.format("%s%02d", red, j));
//                    entity.setCreateTime(new Date());
//                    kafkaTemplate.send("luck", entity);
//                }
//                log.debug(red);
//            });
//        });
    }
//
//    private void start() {
//        log.debug("InitializingData...");
//
//        fixedThreadPool.execute(()->{
//            redCombination.forEach(red -> {
//                for (int j = 1; j <= 16; j++) {
//                    Combination entity = new Combination();
//                    entity.setSort(index++);
//                    entity.setCombination(String.format("%s%02d", red, j));
//                    entity.setCreateTime(new Date());
//                    kafkaTemplate.send("luck", entity);
//                }
//                log.debug(red);
//            });
//            log.debug("kafka done...");
//        });
//    }
}
