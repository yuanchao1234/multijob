package com.yc.word.dao;

import com.yc.word.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YuanDao {
    // 登录
    List<Login> getLogin(String userID, String password, String role);

    // 获取学生个人信息
    List<Student> getSmessage(String userID);

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

    // 老师评分后的最终得分
    List<Course> getTscore(String teacherID);
    List<SelectCourse> getTscore2(String courseID);
    List<StudentTscore> getTscore3(String userID);

    // 管理员add学生
    List<Student> getAxuehao(String userID);
    int getAxuehao2(String userName, String userID, String sex, String birthyear, String grade, String college);
    int getAxuehao3(String userName, String userID);

    // 管理员add教师
    List<Teacher> getAgonghao(String userID);
    int getAgonghao2(String userID, String sex, String degree, String title, String birthyear, String grade, String college);
    int getAgonghao3(String userName, String userID);

    // 查看学生信息
    List<StmCourse> getStm(int courseID);

    // 给教师添加课程
    List<Login> getAcourse(String teacherID, String userName);
    int getAcourse2(String userName, String teacherID, String courseName, String courseTime, String classRoom, String courseWeek, String courseType, String score);
    List<Course> getAcourse3(String courseName, String teacherID);

    // 学生评论老师
    int getScomment(String studentID, String teacherID, String text);
    List<Scomment> getScomment2(String studentID, String teacherID);

    // 删除学生
    int delDelSt1(String studentID);
    int delDelSt2(String studentID);
    int delDelSt3(String studentID);
    int delDelSt4(String studentID);

    // 师生分布
    List<Login> getDistribution(String role);

}
