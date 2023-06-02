package br.com.project.java.kafka.consumer.model;

import java.util.List;

public interface UserEventListener {
    void onData(List<UserModel> event);
    void processComplete();
}