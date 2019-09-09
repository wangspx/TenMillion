package com.spwang.luck.tenmillion.service;

import com.alibaba.fastjson.JSON;
import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sun.misc.Queue;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author spwang Created on 2019/9/9 at 9:00
 * @version 1.0.0
 */
@Slf4j
@Service("doubleColorServiceImpl2")
public class DoubleColorServiceImpl2 implements DoubleColorService {

    @Resource
    private AllCombinationMapper allCombinationMapper;

    AtomicInteger sum = new AtomicInteger();

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    @Override
    public void generateRedCombination() {
        new RedCombination() {
            @Override
            void resultHandler(Integer index, List<String> Combination) {
            }
        }.generate();
    }

    @Override
    public void generateAllCombination() {
        LinkedBlockingDeque<Combination> product = new LinkedBlockingDeque<Combination>();

        fixedThreadPool.execute(() -> {
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
                        product.add(entity);
                    }
                }
            }.generate();
        });

        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(() -> {
                consumer(product);
            });
        }

        fixedThreadPool.execute(() -> {
            while (sum.get() < 1771088) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("sum -> {}", sum);
            }
        });
    }

    private void consumer(LinkedBlockingDeque<Combination> product) {
        List<Combination> list = new ArrayList<Combination>(1400);

        Combination poll = null;
        try {
            poll = product.poll(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (poll != null) {
            sum.incrementAndGet();
            list.add(poll);
            if (list.size() > 1000) {
                allCombinationMapper.batchInsert(list);
                list.clear();
            }
            try {
                poll = product.poll(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        allCombinationMapper.batchInsert(list);
    }
}
