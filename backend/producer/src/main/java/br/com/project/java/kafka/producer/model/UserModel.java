package br.com.project.java.kafka.producer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModel implements Serializable {

    private Long id;
    private String nome;
    private Integer idade;

}
