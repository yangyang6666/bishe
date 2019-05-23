package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WItem implements Serializable{
    private String item_id;
    private String item_name;
    private String form_id;
}
