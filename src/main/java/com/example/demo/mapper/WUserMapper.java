package com.example.demo.mapper;

import com.example.demo.entity.WUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WUserMapper {
    public List<WUser> login(WUser user);

    public Integer reg(WUser user);

    public List<WUser> getByUsername(WUser user);
}
