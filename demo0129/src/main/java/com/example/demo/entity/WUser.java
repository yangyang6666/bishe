package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class WUser implements Serializable {
    private String user_id;
    private String user_name;
    private String user_pwd;
    private String user_phone;
    private String user_email;

}
