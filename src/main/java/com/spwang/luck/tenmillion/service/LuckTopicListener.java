package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.repository.AllCombinationMapper;
import com.spwang.luck.tenmillion.repository.entity.Combination;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author spwang on 2019-08-25 at 23:22
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
//@Service
public class LuckTopicListener {

    @Resource
    private AllCombinationMapper allCombinationMapper;

    /**
     * <p>
     * 开启kafka消费者批量处理
     * </p>
     *
     * ContainerFactory默认配置
     * <br>
     * {@link org.springframework.boot.autoconfigure.kafka.KafkaAnnotationDrivenConfiguration#kafkaListenerContainerFactory}
     *
     * @param kafkaConsumerFactory kafka配置
     * @return 返回kafka批量处理bean
     */
    @Bean
    KafkaListenerContainerFactory<?> batchContainerFactory(@Autowired ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory);
        //开启批量读取
        factory.setBatchListener(true);
        return factory;
    }


    @KafkaListener(id = "0", topics = "luck",containerFactory = "batchContainerFactory")
    public void listen(List<ConsumerRecord<String, Combination>> records) throws Exception {
        List<Combination> list = records.stream().map(ConsumerRecord::value).collect(Collectors.toList());
        allCombinationMapper.batchInsert(list);
        log.debug("{} -> 提交一次事务，数据总条数：{}", Thread.currentThread().getName(), list.size());
    }

}
