package com.yc.word.controller;

import com.yc.word.pojo.*;
import com.yc.word.service.YuanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    // 已选课程
    @GetMapping("selectcourse")
    public ArrayList<Course> selectcourse(@RequestParam String studentID){
        ArrayList<Course> courses = new ArrayList<Course>();
        List<SelectCourse> list = yuanService.getSelectCourse(studentID);
        for (int i = 0; i < list.size(); i++){
            String s = String.valueOf(list.get(i).getCourseID());
            List<Course> clist = yuanService.getSelectCourse2(s);
            for (int j = 0; j < clist.size(); j++){
                courses.add(clist.get(j));
            }
        }
        return courses;
    }

    //学生成绩
    @GetMapping("score")
    public ArrayList<CourseMark> score(@RequestParam String studentID) {
        ArrayList<CourseMark> courses = new ArrayList<CourseMark>();
        List<SelectCourse> list = yuanService.getScore(studentID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMark()!=0) {
                String s = String.valueOf(list.get(i).getCourseID());
                List<CourseMark> clist = yuanService.getScore2(s);
                for (int j = 0; j < clist.size(); j++) {
                    clist.get(j).setMark(list.get(i).getMark());
                    courses.add(clist.get(j));
                }
            }
        }
        return courses;
    }

    // 学生插入课程
    @GetMapping("insertcourse")
    public Object insertcourse(@RequestParam String courseID,String userID,String mark,String yuan){
        Repass repass= new Repass();
        int yuanNum = Integer.parseInt(yuan);
        if(yuanNum == 1){
            repass.setAffectedRows(yuanService.getInsertcourse(courseID,userID,mark));
            return repass;
        }else {
            List<SelectCourse> list = yuanService.getInsertcourse2(courseID,userID);
            return list;
        }
    }

    // 获取老师个人信息
    @GetMapping("tmessage")
    public List<Teacher> tmessage(@RequestParam String userID){
        List<Login> list = yuanService.getTmessage(userID);
        List<Teacher> tlist = yuanService.getTmessage2(userID);
        tlist.get(0).setUserName(list.get(0).getUserName());
        return tlist;
    }

    // 管理员修改密码
    @PostMapping("arepass")
    public Object arepass(@RequestParam String userID,String password){
        List<Login> list = yuanService.getArepass(userID);
        Repass repass= new Repass();
        if(list.size()==1){
            repass.setAffectedRows(yuanService.getArepass2(userID,password));
            return repass;
        }else {
            return 0;
        }
    }

    // 查看老师信息
    @GetMapping("atm")
    public Object atm(@RequestParam String userID){
        ArrayList atm = new ArrayList();
        List<Teacher> tlist = yuanService.getAtm(userID);
        if(tlist.size()==1){
            List<Course> clist = yuanService.getAtm2(userID);
            tlist.get(0).setUserName(clist.get(0).getUserName());
            atm.add(tlist.get(0));
            atm.add(clist.get(0));
            return atm;
        }else {
            return 0;
        }
    }

    // 老师查看评论
    @GetMapping("tcomment")
    public Object tcomment(@RequestParam String teacherID){
        List<Tcomment> list = yuanService.getTcomment(teacherID);
        List<Student> slist = null;
        for (int i=0;i<list.size();i++){
            String studentID = list.get(i).getStudentID();
            slist = yuanService.getTcomment2(studentID);
            list.get(i).setStudent(slist.get(0).getUserName());
            list.get(i).setUrl(slist.get(0).getUrl());
        }
        return list;
    }


}
