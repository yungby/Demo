package com.andy.vs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class IndicatorService {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     * 注入KafkaTemplate
     *
     * @param kafkaTemplate kafka模版类
     */
    @Autowired
    public IndicatorService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "#{kafkaTopicName}", groupId = "#{topicGroupId}")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        log.info("kafka processMessage start");
        log.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());
        //properties.getProperties();
        if (record.topic().equals("Topic1")) {
            log.info("Topic1 -- " + record.toString());
        } else if (record.topic().equals("Topic2")) {
            log.info("Topic2 -- " + record.toString());
        } else if (record.topic().equals("Topic3")) {
            log.info("Topic3 -- " + record.toString());
        } else if (record.topic().equals("my-replicated-topic")) {
            log.info(record.topic() + " -- " + record.toString());
        } else {
            log.info("kafka other");
        }

        log.info("kafka processMessage end");
    }

    public void sendMessage(String topic, String data) {
        log.info("kafka sendMessage start");

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}", topic, data);
            }
        });
        log.info("kafka sendMessage end");
    }
}
