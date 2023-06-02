package br.com.project.java.kafka.consumer.controller;

import br.com.project.java.kafka.consumer.model.UserModel;
import br.com.project.java.kafka.consumer.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@CrossOrigin(
    value = { "*" },
    allowedHeaders = { "Baeldung-Allowed" },
    maxAge = 900
)
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    private Flux<List<UserModel>> bridge;

    @GetMapping(value = "/consumer/user-data", produces = "text/event-stream;charset=UTF-8")
    public Flux<List<UserModel>> getUser() {

       return this.consumerService.createBridge().publish().autoConnect().cache(10).log();
    }

}
