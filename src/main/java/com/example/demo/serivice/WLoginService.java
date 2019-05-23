package com.example.demo.serivice;

import com.example.demo.common.Utils;
import com.example.demo.entity.WAnswer;
import com.example.demo.entity.WForm;
import com.example.demo.entity.WItem;
import com.example.demo.entity.WUser;
import com.example.demo.mapper.WAnswerMapper;
import com.example.demo.mapper.WFormMapper;
import com.example.demo.mapper.WItemMapper;
import com.example.demo.mapper.WUserMapper;
import com.example.demo.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * @program: demo0129
 * @description:
 * @author: yy
 * @create: 2019-05-21 16:57
 **/
@Service
public class WLoginService {
    @Resource
    private WUserMapper mapper;

    @Resource
    private WFormMapper formMapper;

    @Resource
    private WItemMapper wItemMapper;

    @Resource
    private WAnswerMapper answerMapper;

    public boolean login(WUser wUser, HttpServletResponse response){
        List<WUser> result=mapper.login(wUser);
        if(AssertUtil.isVal(result)){
            Cookie cookie=new Cookie("user_id",result.get(0).getUser_id());
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    public boolean reg(WUser wUser){
        List<WUser> exists=mapper.getByUsername(wUser);
        if(AssertUtil.isVal(exists)){
            return false;
        }

        wUser.setUser_id(Utils.getUUID());
        Integer i=mapper.reg(wUser);
        if(i!=0){
            return true;
        }
        return false;
    }

    public List<WForm> getFormByUserId(WUser wUser){
        List<WForm> result=formMapper.getByUserId(wUser);
        return result;
    }

    public List<WForm> getForm(WForm wForm){
        return formMapper.getByFormId(wForm);
    }

    public Integer addForm(WForm wForm){
        return formMapper.addForm(wForm);
    }

    public Integer addItem(WItem wItem){
        return wItemMapper.addItem(wItem);
    }

    public List<WItem> getItemByFormId(WForm form){
        return wItemMapper.getByFormId(form);
    }

    public Integer addAnswer(WAnswer answer){
        answer.setAnswer_id(Utils.getUUID());
        return answerMapper.addAnswer(answer);
    }

    public void  removeForm(WForm wForm){
        wItemMapper.deleteItemByFormId(wForm);
        formMapper.deleteForm(wForm);
    }


}
