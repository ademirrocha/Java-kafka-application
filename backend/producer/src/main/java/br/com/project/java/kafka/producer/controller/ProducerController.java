package br.com.project.java.kafka.producer.controller;

import br.com.project.java.kafka.producer.model.UserModel;
import br.com.project.java.kafka.producer.services.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(value = "/producer")
@Slf4j
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("user-data")
    public void send(@RequestBody List<UserModel> user) {
        producerService.send(user);
    }

}
