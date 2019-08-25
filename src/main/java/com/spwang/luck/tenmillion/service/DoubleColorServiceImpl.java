package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private List<String> tmp = new ArrayList<>();

    private List<String> redCombination = new ArrayList<>(1107568);

    private ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    @Resource
    private KafkaTemplate<String, Combination> kafkaTemplate;

    @Override
    public void generateAllCombination() {
        start();
    }

    private void start() {
        log.debug("InitializingData...");
        String[] redBox = new String[33];
        for (int i = 0; i < 33; i++) {
            redBox[i] = String.format("%02d", i + 1);
        }

        int k = 6;
        combine(0, k, redBox);
        log.debug("generate red combination done...");

        fixedThreadPool.execute(()->{
            redCombination.forEach(red -> {
                for (int j = 1; j <= 16; j++) {
                    Combination entity = new Combination();
                    entity.setSort(index++);
                    entity.setCombination(String.format("%s%02d", red, j));
                    entity.setCreateTime(new Date());
                    kafkaTemplate.send("luck", entity);
                }
                log.debug(red);
            });
            log.debug("kafka done...");
        });
    }

    private void combine(int index, int k, String... arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmp.add(arr[i]);
                redCombination.add(StringUtils.join(tmp, ""));
                tmp.remove(arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmp.add(arr[i]);
                combine(i + 1, k - 1, arr);
                tmp.remove(arr[i]);
            }
        }
    }
}
