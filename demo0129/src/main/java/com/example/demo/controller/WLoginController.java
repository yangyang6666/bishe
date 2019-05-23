package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.HttpResult;
import com.example.demo.common.Utils;
import com.example.demo.entity.*;
import com.example.demo.serivice.WLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: demo0129
 * @description:
 * @author: yy
 * @create: 2019-05-21 17:00
 **/
@Controller
@RequestMapping("/test")
public class WLoginController {
    @Autowired
    private WLoginService service;


    @PostMapping("/login")
    @ResponseBody
    public HttpResult login(@RequestBody  WUser wUser, HttpServletResponse response){
        boolean ifSuccess=service.login(wUser,response);
        if(ifSuccess==true){
            return new HttpResult(200,"登陆成功",true);
        }
        return new HttpResult(200,"登陆失败，用户名或者密码不正确",false);

    }

    @PostMapping("/reg")
    @ResponseBody
    public HttpResult reg(@RequestBody WUser wUser){
        boolean ifSuccess=service.reg(wUser);
        if(ifSuccess==true){
            return new HttpResult(200,"注册成功",true);
        }
        return new HttpResult(200,"注册失败，用户名已存在",false);
    }

    @PostMapping("/getFormListByUserId")
    @ResponseBody
    public HttpResult getFormListByUserId( HttpServletRequest request){
        WUser wUser=new WUser();
        if(wUser.getUser_id()==null){
            Cookie[] cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user_id")){
                    wUser.setUser_id(cookie.getValue());
                }
            }
        }
        if(wUser.getUser_id()==null){
            return new HttpResult(200,"未获取到user_id",wUser);
        }
        List<WForm> wFormList=service.getFormByUserId(wUser);
        return new HttpResult(200,"获取成功",wFormList);
    }

    @PostMapping("/addForm")
    @ResponseBody
    public HttpResult saveForm(@RequestBody FormParam param,HttpServletRequest request){
        String user_id=param.getUser_id();
        if(user_id==null){
            Cookie[] cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user_id")){
                    user_id=cookie.getValue();
                }
            }
        }
        String form_name=param.getForm_name();
        WForm wForm=new WForm();
        String form_id=Utils.getUUID();
        wForm.setForm_id(form_id);
        wForm.setForm_name(form_name);
        wForm.setUser_id(user_id);
        int i=service.addForm(wForm);
        if(i==0){
            return new HttpResult(200,"插入Form失败",wForm);
        }
        List<WItem> items=param.getItems();
        if(items!=null){
            for(WItem wItem:items){
                wItem.setForm_id(form_id);
                wItem.setItem_id(Utils.getUUID());
                service.addItem(wItem);
            }
        }
        return new HttpResult(200,"插入成功",wForm);
    }

    @GetMapping("/{form_id}")
    @ResponseBody
    public HttpResult getForm(@PathVariable String form_id){
        WForm form=new WForm();
        form.setForm_id(form_id);
        List<WForm> forms=service.getForm(form);
        if(forms==null||forms.isEmpty()){
            return new HttpResult(200,"表单不存在",form);
        }
        form=forms.get(0);
        List<WItem> items=service.getItemByFormId(form);
        FormParam result=new FormParam();
        result.setForm_name(form.getForm_name());
        result.setItems(items);
        result.setUser_id(form.getUser_id());
        return new HttpResult(200,"获取成功",result);
    }


    @PostMapping("/addAnswers")
    @ResponseBody
    public HttpResult addAnswer(@RequestBody WAnswerParam answers){
        try{
            for(WAnswer wAnswer:answers.getAnswerList()){
                service.addAnswer(wAnswer);
            }
        }catch (Exception e){
            return new HttpResult(200,"添加失败"+e.getMessage(),false);
        }
        return  new HttpResult(200,"添加成功",true);
    }


    @PostMapping("/removeForm")
    @ResponseBody
    public HttpResult removeForm(@RequestBody WForm wForm){
        try{
            service.removeForm(wForm);
        }catch(Exception e){
            return new HttpResult(200,"删除失败"+e.getMessage(),false);
        }
        return new HttpResult(200,"删除成功",true);
    }

//    @PostMapping("/getAnswerAndItems")
//    @ResponseBody
//    public HttpResult getExcel(@RequestBody WForm wForm){
//        JSONObject o=new JSONObject();
//        List<WForm> forms=service.getForm(wForm);
//        if(forms==null||forms.isEmpty()){
//            return new HttpResult(200,"表单不存在",null);
//        }
//        o.put("题目名字",forms.get(0).getForm_name());
//        service.getItemByFormId()
//    }





}
