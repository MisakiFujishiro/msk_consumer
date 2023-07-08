package com.msa.aws.msk.msk_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @KafkaListener(topics="Topic_from_java")
    public void receiveMessage(ConsumerRecord<String, String> record){
        System.out.println("Received message:");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
    }

}
