package com.yc.word.service;

import com.yc.word.pojo.*;

import java.util.List;

public interface YuanService {
    // 登录
    List<Login> getLogin(String userID, String password, String role);

    // 获取学生个人信息
    List<Smessage> getSmessage(String userID);

    // 修改密码
    int getRepass(String userID, String password);

    // 展现学生所有课程
    List<Course> getCourse();

    //老师的课程
    List<Course> getTcourse(String teacherID);

    // 老师评分
    int getTpingfeng(String courseID,String studentID,String mark);

    // 展现对应学生的课程
    List<SelectCourse> getSelectCourse(String studentID);
    List<Course> getSelectCourse2(String courseID);

    // 学生成绩
    List<SelectCourse> getScore(String studentID);
    List<CourseMark> getScore2(String courseID);

    // 学生插入课程
    int getInsertcourse(String courseID,String userID,String mark);
    List<SelectCourse> getInsertcourse2(String courseID, String userID);

    // 获取老师个人信息
    List<Login> getTmessage(String userID);
    List<Teacher> getTmessage2(String userID);

    // 管理员修改密码
    List<Login> getArepass(String userID);
    int getArepass2(String userID,String password);

    // 查看老师信息
    List<Teacher> getAtm(String userID);
    List<Course> getAtm2(String userID);

    // 老师查看评论
    List<Tcomment> getTcomment(String teacherID);
    List<Student> getTcomment2(String studentID);
}
