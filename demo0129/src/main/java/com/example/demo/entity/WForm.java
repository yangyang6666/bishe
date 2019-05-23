package com.example.demo.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Data
public class WForm implements Serializable {
    private String form_id;
    private String form_name;
    private String user_id;
}
