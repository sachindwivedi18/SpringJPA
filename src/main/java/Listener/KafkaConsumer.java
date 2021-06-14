package Listener;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Employee;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_example", groupId = "group-id" ,  containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    /*
    @KafkaListener(topics = "Kafka_example", groupId = "group-id",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Employee user) {
        System.out.println("Consumed JSON Message: " + user);
    }*/
}