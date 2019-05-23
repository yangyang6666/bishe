package com.example.demo.mapper;

import com.example.demo.entity.WAnswer;
import com.example.demo.entity.WItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WAnswerMapper {
    public List<WAnswer> getByItemId(WItem wItem);

    public Integer addAnswer(WAnswer wAnswer);
}
