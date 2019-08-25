package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.repository.entity.Combination;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author spwang on 2019-08-25 at 23:22
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class KafkaListener implements MessageListener<String, Combination> {
    @Override
    public void onMessage(ConsumerRecord<String, Combination> stringCombinationConsumerRecord) {
        Combination value = stringCombinationConsumerRecord.value();

    }
}
