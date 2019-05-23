package com.example.demo.entity;

import lombok.Data;

import java.util.List;


@Data
public class FormParam {
    private String user_id;
    private String form_name;
    private List<WItem> items;
}
