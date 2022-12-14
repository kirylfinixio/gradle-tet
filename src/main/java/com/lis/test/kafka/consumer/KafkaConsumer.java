package com.lis.test.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "#{'${lis.kafka.topic}'}")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        log.info("Received message: offset: '{}', key: '{}', value: '{}'", consumerRecord.offset(),
                consumerRecord.key(), consumerRecord.value());
    }
}
