package com.yc.word.service;

import com.yc.word.pojo.Course;
import com.yc.word.pojo.Login;
import com.yc.word.pojo.Smessage;

import java.util.List;

public interface YuanService {
    // 登录
    List<Login> getLogin(String userID, String password, String role);
    // 获取学生个人信息
    List<Smessage> getSmessage(String userID);

    int getRepass(String userID, String password);
    // 修改密码
    List<Course> getCourse();
    //老师的课程
    List<Course> getTcourse(String teacherID);

    int getTpingfeng(String courseID,String studentID,String mark);

}
