package br.com.project.java.kafka.producer.services;

import br.com.project.java.kafka.producer.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProducerService {

    @Value("${kafka.topic-user-data}")
    private String topic;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;


    private final KafkaTemplate<String, List<UserModel>> kafkaTemplate;


    public void send(final @RequestBody List<UserModel> order) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.flush();
        kafkaTemplate.getDefaultTopic();

        kafkaTemplate.send(topic, mensageKey, order);

    }


}
