package com.yc.word.dao;

import com.yc.word.pojo.Course;
import com.yc.word.pojo.Login;
import com.yc.word.pojo.Smessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YuanDao {
    List<Login> getLogin(String userID, String password, String role);
    List<Smessage> getSmessage(String userID);
    int getRepass(String userID, String password);
    List<Course> getCourse();
    List<Course> getTcourse(String teacherID);
    int getTpingfeng(String courseID,String studentID,String mark);
}
