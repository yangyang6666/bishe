package com.example.demo.mapper;

import com.example.demo.entity.WForm;
import com.example.demo.entity.WUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WFormMapper {
    public List<WForm> getByUserId(WUser wUser);

    public Integer addForm(WForm wForm);

    public void deleteForm(WForm wForm);

    public List<WForm> getByFormId(WForm wForm);
}
