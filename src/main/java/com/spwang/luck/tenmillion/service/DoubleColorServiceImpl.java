package com.spwang.luck.tenmillion.service;

import com.alibaba.fastjson.JSON;
import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.AllCombination;
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

    private ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    @Resource
    private AllCombinationMapper allCombinationMapper;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

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
    }

    private void combine( int index, int k, String... arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmp.add(arr[i]);
                String red = StringUtils.join(tmp, "");
//                result.add(red);
                for (int j = 1; j <= 16; j++) {
                    AllCombination entity = new AllCombination();
                    entity.setSort(index++);
                    entity.setCombination(String.format("%s%02d",red, j));
                    entity.setCreateTime(new Date());
                    System.out.println(entity.getCombination());
                    kafkaTemplate.send("test", JSON.toJSONString(entity));
                }
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
//
//    private class Producer implements Runnable {
//        private List<String> list;
//        private BlockingQueue<AllCombination> product;
//
//        Producer(BlockingQueue<AllCombination> product, List<String> list) {
//            this.list = list;
//            this.product = product;
//        }
//
//        public void run() {
//            AtomicInteger sum = new AtomicInteger();
//            this.list.forEach(s -> {
//                for (int i = 1; i <= 16; i++) {
//                    AllCombination entity = new AllCombination();
//                    entity.setCombination(s + String.format("%02d", i));
//                    entity.setCreateTime(new Date());
//                    try {
//                        product.put(entity);
//                        sum.getAndIncrement();
//                    } catch (InterruptedException e) {
//                        log.error("队列插入数据失败", e);
//                    }
//                }
//            });
//            log.debug("一共生产了{}数据", sum.get());
//        }
//    }
//
//    private class Consumer implements Runnable {
//        private BlockingQueue<AllCombination> product;
//
//        Consumer(BlockingQueue<AllCombination> product) {
//            this.product = product;
//        }
//
//        public void run() {
//            List<AllCombination> list = new ArrayList<>(1032);
//            boolean done = false;
//            while (!done) {
//                try {
//                    AllCombination product = this.product.poll(3, TimeUnit.SECONDS);
//                    if (product != null) {
//                        list.add(product);
//                    } else {
//                        done = true;
//                    }
//                } catch (InterruptedException e) {
//                    log.error("队列取值异常", e);
//                }
//                if (list.size() >= 1000) {
//                    allCombinationMapper.insertList(list);
//                    log.debug("{} -> 提交一次事务，数据总条数：{}", Thread.currentThread().getName(), list.size());
//                    list.clear();
//                }
//            }
//            if (list.size() > 0) {
//                allCombinationMapper.insertList(list);
//                log.debug("{} -> 提交一次事务，数据总条数：{}", Thread.currentThread().getName(), list.size());
//            }
//        }
//    }
}
