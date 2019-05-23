package com.example.demo.mapper;

import com.example.demo.entity.WForm;
import com.example.demo.entity.WItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WItemMapper {
    public List<WItem> getByItemId(WItem wItem);

    public List<WItem> getByFormId(WForm wForm);

    public Integer addItem(WItem wItem);

    public void deleteItemByFormId(WForm wForm);
}
