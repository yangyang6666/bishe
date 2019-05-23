package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WAnswer implements Serializable {
    private String answer_id;
    private String answer;
    private String item_id;
}
