package br.com.project.java.kafka.consumer.services;


import br.com.project.java.kafka.consumer.model.UserEventListener;
import br.com.project.java.kafka.consumer.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topic-user-data}", groupId = "${spring.kafka.consumer.group-id}", id = "reactive-consumer-user" )
    public void consumer(final ConsumerRecord<String, List<UserModel>> consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partion: " + consumerRecord.partition());
        log.info("User: {} ", consumerRecord.value());
        ModelMapper modelMapper = new ModelMapper();
        List<UserModel> list = modelMapper.map(consumerRecord.value(), new TypeToken<List<UserModel>>() {}.getType());
        onEvent(list);
    }

    private UserEventListener listener;

    public Flux<List<UserModel>> createBridge() {

        return Flux.create(sink -> { // (2)
            register(new UserEventListener() {
                @Override
                public void processComplete() {
                    sink.complete();
                }
                @Override
                public void onData(List<UserModel> data) {
                    sink.next(data);
                }
            });
        });
    }

    public void onEvent(List<UserModel> event) {
        if (listener != null) {
            listener.onData(event);
        }
    }

    public void onComplete() {
        if (listener != null) {
            listener.processComplete();
        }
    }

    public void register(UserEventListener listener) {
        this.listener = listener;
    }


}