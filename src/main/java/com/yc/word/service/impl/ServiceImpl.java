package com.yc.word.service.impl;

import com.yc.word.dao.YuanDao;
import com.yc.word.pojo.*;
import com.yc.word.service.YuanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceImpl implements YuanService {
    @Resource
    private YuanDao yuanDao;

    // 登录
    @Override
    public List<Login> getLogin(String userID, String password, String role) {
        return yuanDao.getLogin(userID,password,role);
    }
    // 获取学生个人信息
    @Override
    public List<Student> getSmessage(String userID){
        return yuanDao.getSmessage(userID);
    }
    // 修改密码
    @Override
    public int getRepass(String userID, String password){
        return yuanDao.getRepass(userID,password);
    }
    // 展现学生所有课程
    @Override
    public List<Course> getCourse(){
        return yuanDao.getCourse();
    }
    //老师的课程
    @Override
    public List<Course> getTcourse(String teacherID){
        return yuanDao.getTcourse(teacherID);
    }
    // 老师评分
    @Override
    public int getTpingfeng(String courseID,String studentID,String mark){
        return yuanDao.getTpingfeng(courseID,studentID,mark);
    }
    // 展现对应学生的课程
    @Override
    public List<SelectCourse> getSelectCourse(String studentID){
        return yuanDao.getSelectCourse(studentID);
    }
    @Override
    public List<Course> getSelectCourse2(String courseID){
        return yuanDao.getSelectCourse2(courseID);
    }
    // 学生成绩
    @Override
    public List<SelectCourse> getScore(String studentID){
        return yuanDao.getScore(studentID);
    }
    @Override
    public List<CourseMark> getScore2(String courseID){
        return yuanDao.getScore2(courseID);
    }
    // 学生插入课程
    @Override
    public int getInsertcourse(String courseID,String userID,String mark){
        return yuanDao.getInsertcourse(courseID,userID,mark);
    }
    @Override
    public List<SelectCourse> getInsertcourse2(String courseID, String userID){
        return yuanDao.getInsertcourse2(courseID,userID);
    }
    // 获取老师个人信息
    @Override
    public List<Login> getTmessage(String userID){
        return yuanDao.getTmessage(userID);
    }
    @Override
    public List<Teacher> getTmessage2(String userID){
        return yuanDao.getTmessage2(userID);
    }
    // 管理员修改密码
    @Override
    public List<Login> getArepass(String userID){
        return yuanDao.getArepass(userID);
    }
    @Override
    public int getArepass2(String userID,String password){
        return yuanDao.getArepass2(userID,password);
    }
    // 查看老师信息
    @Override
    public List<Teacher> getAtm(String userID){
        return yuanDao.getAtm(userID);
    }
    @Override
    public List<Course> getAtm2(String userID){
        return yuanDao.getAtm2(userID);
    }
    // 老师查看评论
    @Override
    public List<Tcomment> getTcomment(String teacherID){
        return yuanDao.getTcomment(teacherID);
    }
    @Override
    public List<Student> getTcomment2(String studentID){
        return  yuanDao.getTcomment2(studentID);
    }
    // 老师评分后的最终得分
    @Override
    public List<Course> getTscore(String teacherID){
        return yuanDao.getTscore(teacherID);
    }
    @Override
    public List<SelectCourse> getTscore2(String courseID){
        return yuanDao.getTscore2(courseID);
    }
    @Override
    public List<StudentTscore> getTscore3(String userID){
        return yuanDao.getTscore3(userID);
    }
    // 管理员add学生
    @Override
    public List<Student> getAxuehao(String userID){
        return yuanDao.getAxuehao(userID);
    }
    @Override
    public int getAxuehao2(String userName, String userID, String sex, String birthyear, String grade, String college){
        return yuanDao.getAxuehao2(userName, userID, sex, birthyear, grade, college);
    }
    @Override
    public int getAxuehao3(String userName, String userID){
        return yuanDao.getAxuehao3(userName,userID);
    }
    // 管理员add教师
    @Override
    public List<Teacher> getAgonghao(String userID){
        return yuanDao.getAgonghao(userID);
    }
    @Override
    public int getAgonghao2(String userID, String sex, String degree, String title, String birthyear, String grade, String college){
        return yuanDao.getAgonghao2(userID, sex, degree, title, birthyear, grade, college);
    }
    @Override
    public int getAgonghao3(String userName, String userID){
        return yuanDao.getAgonghao3(userName, userID);
    }
    // 查看学生信息
    @Override
    public List<StmCourse> getStm(int courseID){
        return yuanDao.getStm(courseID);
    }
    // 给教师添加课程
    @Override
    public List<Login> getAcourse(String teacherID, String userName){
        return yuanDao.getAcourse(teacherID, userName);
    }
    @Override
    public int getAcourse2(String userName, String teacherID, String courseName, String courseTime, String classRoom, String courseWeek, String courseType, String score){
        return yuanDao.getAcourse2(userName, teacherID, courseName,courseTime, classRoom , courseWeek, courseType, score);
    }
    @Override
    public List<Course> getAcourse3(String courseName, String teacherID){
        return yuanDao.getAcourse3(courseName, teacherID);
    }
    // 学生评论老师
    @Override
    public int getScomment(String studentID, String teacherID, String text){
        return yuanDao.getScomment(studentID, teacherID, text);
    }
    @Override
    public List<Scomment> getScomment2(String studentID, String teacherID){
        return yuanDao.getScomment2(studentID, teacherID);
    }
    // 删除学生
    @Override
    public int delDelSt1(String studentID){
        return yuanDao.delDelSt1(studentID);
    }
    @Override
    public int delDelSt2(String studentID){
        return yuanDao.delDelSt2(studentID);
    }
    @Override
    public int delDelSt3(String studentID){
        return yuanDao.delDelSt3(studentID);
    }
    @Override
    public int delDelSt4(String studentID){
        return yuanDao.delDelSt4(studentID);
    }

    // 删除教工
    @Override
    public int delDelTt1(String courseID){
        return yuanDao.delDelTt1(courseID);
    }
    @Override
    public int delDelTt2(String teacherID){
        return yuanDao.delDelTt2(teacherID);
    }
    @Override
    public int delDelTt3(String teacherID){
        return yuanDao.delDelTt3(teacherID);
    }
    @Override
    public int delDelTt4(String teacherID){
        return yuanDao.delDelTt4(teacherID);
    }
    // 师生分布
    @Override
    public List<Login> getDistribution(String role){
        return yuanDao.getDistribution(role);
    }
}
