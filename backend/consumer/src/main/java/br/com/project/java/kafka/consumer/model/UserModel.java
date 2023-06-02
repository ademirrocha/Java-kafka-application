package br.com.project.java.kafka.consumer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModel implements Serializable {

    private Long id;
    private Integer idade;
    private String nome;


}
