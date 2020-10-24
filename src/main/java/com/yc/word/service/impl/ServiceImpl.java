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

    @Override
    public List<Login> getLogin(String userID, String password, String role) {
        return yuanDao.getLogin(userID,password,role);
    }

    @Override
    public List<Smessage> getSmessage(String userID){
        return yuanDao.getSmessage(userID);
    }

    @Override
    public int getRepass(String userID, String password){
        return yuanDao.getRepass(userID,password);
    }

    @Override
    public List<Course> getCourse(){
        return yuanDao.getCourse();
    }

    @Override
    public List<Course> getTcourse(String teacherID){
        return yuanDao.getTcourse(teacherID);
    }

    @Override
    public int getTpingfeng(String courseID,String studentID,String mark){
        return yuanDao.getTpingfeng(courseID,studentID,mark);
    }

    @Override
    public List<SelectCourse> getSelectCourse(String studentID){
        return yuanDao.getSelectCourse(studentID);
    }
    @Override
    public List<Course> getSelectCourse2(String courseID){
        return yuanDao.getSelectCourse2(courseID);
    }

    @Override
    public List<SelectCourse> getScore(String studentID){
        return yuanDao.getScore(studentID);
    }
    @Override
    public List<CourseMark> getScore2(String courseID){
        return yuanDao.getScore2(courseID);
    }

    @Override
    public int getInsertcourse(String courseID,String userID,String mark){
        return yuanDao.getInsertcourse(courseID,userID,mark);
    }
    @Override
    public List<SelectCourse> getInsertcourse2(String courseID, String userID){
        return yuanDao.getInsertcourse2(courseID,userID);
    }

    @Override
    public List<Login> getTmessage(String userID){
        return yuanDao.getTmessage(userID);
    }
    @Override
    public List<Teacher> getTmessage2(String userID){
        return yuanDao.getTmessage2(userID);
    }

    @Override
    public List<Login> getArepass(String userID){
        return yuanDao.getArepass(userID);
    }
    @Override
    public int getArepass2(String userID,String password){
        return yuanDao.getArepass2(userID,password);
    }

    @Override
    public List<Teacher> getAtm(String userID){
        return yuanDao.getAtm(userID);
    }
    @Override
    public List<Course> getAtm2(String userID){
        return yuanDao.getAtm2(userID);
    }

    @Override
    public List<Tcomment> getTcomment(String teacherID){
        return yuanDao.getTcomment(teacherID);
    }
    @Override
    public List<Student> getTcomment2(String studentID){
        return  yuanDao.getTcomment2(studentID);
    }
}
