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

    List<Course> getTscore(String teacherID);
    List<SelectCourse> getTscore2(String courseID);
    List<StudentTscore> getTscore3(String userID);

    List<Student> getAxuehao(String userID);
    int getAxuehao2(String userName, String userID, String sex, String birthyear, String grade, String college);
    int getAxuehao3(String userName, String userID);

    List<Teacher> getAgonghao(String userID);
    int getAgonghao2(String userID, String sex, String degree, String title, String birthyear, String grade, String college);
    int getAgonghao3(String userName, String userID);

    List<StmCourse> getStm(int courseID);

    List<Login> getAcourse(String teacherID, String userName);
    int getAcourse2(String userName, String teacherID, String courseName, String courseTime, String classRoom, String courseWeek, String courseType, String score);

    int getScomment(String studentID, String teacherID, String text);
    List<Scomment> getScomment2(String studentID, String teacherID);

    int delDelSt1(String studentID);
    int delDelSt2(String studentID);
    int delDelSt3(String studentID);
    int delDelSt4(String studentID);

    List<Login> getDistribution(String role);

}
