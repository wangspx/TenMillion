package com.spwang.luck.tenmillion.service;

import com.alibaba.fastjson.JSON;
import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author spwang on 2019-08-25 at 23:22
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class LuckTopicListener {

    private final List<Combination> list = new ArrayList<>(1200);

    @Resource
    private AllCombinationMapper allCombinationMapper;

    @KafkaListener(id = "0", topics = "luck")
    public void listen(ConsumerRecord<String, Combination> record) throws Exception {
        list.add(record.value());
        if (list.size() > 1000) {
            synchronized (list) {
                allCombinationMapper.insertList(list);
                log.debug("{} -> 提交一次事务，数据总条数：{}", Thread.currentThread().getName(), list.size());
                list.clear();
            }
        }
    }

}
