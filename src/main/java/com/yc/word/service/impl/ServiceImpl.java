package com.yc.word.service.impl;

import com.yc.word.dao.YuanDao;
import com.yc.word.pojo.Course;
import com.yc.word.pojo.Login;
import com.yc.word.pojo.Smessage;
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
}
