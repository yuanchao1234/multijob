package com.yc.word.dao;

import com.yc.word.pojo.*;
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

    List<SelectCourse> getSelectCourse(String studentID);
    List<Course> getSelectCourse2(String courseID);

    List<SelectCourse> getScore(String studentID);
    List<CourseMark> getScore2(String courseID);

    int getInsertcourse(String courseID,String userID,String mark);
    List<SelectCourse> getInsertcourse2(String courseID, String userID);

    List<Login> getTmessage(String userID);
    List<Teacher> getTmessage2(String userID);

    List<Login> getArepass(String userID);
    int getArepass2(String userID,String password);

    List<Teacher> getAtm(String userID);
    List<Course> getAtm2(String userID);

    List<Tcomment> getTcomment(String teacherID);
    List<Student> getTcomment2(String studentID);

}
