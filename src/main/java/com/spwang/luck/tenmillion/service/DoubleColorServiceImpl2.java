package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
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

    private AtomicInteger sum = new AtomicInteger();

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);

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
        Stack<Combination> product = new Stack<>();

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
                        product.push(entity);
                    }

                    if (product.size() > 20000) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.generate();
        });

        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    consumer(product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void consumer(Stack<Combination> product) throws InterruptedException {
        List<Combination> list = new ArrayList<>(1400);

        boolean isNotDone = true;

        while (isNotDone) {
            sum.incrementAndGet();

            if (product.empty()) {
                Thread.sleep(3000);
                isNotDone = !product.empty();
            }
            list.add(product.pop());

            if (list.size() > 1000) {
                allCombinationMapper.batchInsert(list);
                list.clear();
                log.info("product -> {}", product.size());
            }
        }
        if (list.size() > 0) {
            allCombinationMapper.batchInsert(list);
        }
        log.info("consumer {} is stop", Thread.currentThread().getName());
    }
}
