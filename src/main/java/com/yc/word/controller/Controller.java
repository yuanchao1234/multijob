package com.yc.word.controller;

import com.yc.word.pojo.Course;
import com.yc.word.pojo.Login;
import com.yc.word.pojo.Repass;
import com.yc.word.pojo.Smessage;
import com.yc.word.service.YuanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Controller {

    @Resource
    private YuanService yuanService;

    // 登录
    @PostMapping("login")
    public List<Login> login(@RequestParam String userID, String password, String role){
        return yuanService.getLogin(userID,password,role);
    }
    // 获取学生个人信息
    @GetMapping("smessage")
    public List<Smessage> smessage(@RequestParam String userID){
        return yuanService.getSmessage(userID);
    }

    // 修改密码
    @PostMapping("repass")
    public Repass repass(@RequestParam String userID, String password){
        Repass repass= new Repass();
        repass.setAffectedRows(yuanService.getRepass(userID,password));
        return repass;
    }

    // 展现学生所有课程
    @GetMapping("course")
    public List<Course> course(){
        return yuanService.getCourse();
    }

    //老师的课程
    @GetMapping("tcourse")
    public List<Course> tcourse(@RequestParam String teacherID){
        return yuanService.getTcourse(teacherID);
    }

    //老师评分
    @GetMapping("tpingfeng")
    public Repass tpingfeng(@RequestParam String courseID,String studentID,String mark){
        Repass repass= new Repass();
        repass.setAffectedRows(yuanService.getTpingfeng(courseID,studentID,mark));
        return repass;
    }
}
