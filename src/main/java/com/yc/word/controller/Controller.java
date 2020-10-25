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
            List<Login> tlist2 = yuanService.getTmessage(userID);
            tlist.get(0).setUserName(tlist2.get(0).getUserName());
            atm.add(tlist.get(0));
            List<Course> clist = yuanService.getAtm2(userID);
            if(clist.size()>0){ // 表示该老师有课程信息
                atm.add(clist.get(0));
            }
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

    // 老师评分后的最终得分
    @GetMapping("tscore")
    public ArrayList tscore(@RequestParam String teacherID, int panduan){
        ArrayList arr = new ArrayList();
        List<Course> list1 = yuanService.getTscore(teacherID);
        List<SelectCourse> list2 = yuanService.getTscore2(String.valueOf(list1.get(0).getCourseID()));
        for(int i = 0; i<list2.size(); i++){
            String userID = String.valueOf(list2.get(i).getStudentID());
            List<StudentTscore> list3 = yuanService.getTscore3(userID);
            list3.get(0).setCourseID(list1.get(0).getCourseID());
            list3.get(0).setCourseName(list1.get(0).getCourseName());
            list3.get(0).setCourseType(list1.get(0).getCourseType());
            if(panduan==0){
                if(list2.get(i).getMark()==0.0){
                    list3.get(0).setMark(list2.get(i).getMark());
                    arr.add(list3.get(0));
                }
            }else {
                if(list2.get(i).getMark() > 0){
                    list3.get(0).setMark(list2.get(i).getMark());
                    arr.add(list3.get(0));
                }
            }
        }
        return arr;
    }

    // 管理员add学生
    @GetMapping("axuehao")
    public int axuehao(@RequestParam String userName, String userID, String sex, String birthyear, String grade, String college){
        List<Student> list1 = yuanService.getAxuehao(userID);
        if(list1.size()==0){
            int l1 = yuanService.getAxuehao2(userName, userID, sex, birthyear, grade, college);
            int l2 = yuanService.getAxuehao3(userName, userID);
            if(l1 == 1&&l2 == 1){
                return 0;
            }else {
                return 2;
            }
        }else {
            return 1;
        }
    }

    // 管理员add教师
    @GetMapping("agonghao")
    public Object agonghao(@RequestParam String userName, String userID, String sex, String degree, String title, String birthyear, String grade, String college){
        List<Teacher> list1 = yuanService.getAgonghao(userID);
        if(list1.size()==0){
            int l1 = yuanService.getAgonghao2(userID, sex, degree, title, birthyear, grade, college);
            int l2 = yuanService.getAgonghao3(userName, userID);
            if(l1 == 1&&l2 == 1){
                return 0;
            }else {
                return 2;
            }
        }else {
            return 1;
        }
    }

    // 查看学生信息
    @GetMapping("stm")
    public Object stm(@RequestParam String userID){
        ArrayList arr = new ArrayList();
        ArrayList arr1 = new ArrayList();
        List<Smessage> list1 = yuanService.getSmessage(userID);
        if(list1.size()==0){
            return 0;
        }else {
            arr.add(list1.get(0));
            List<SelectCourse> list2 = yuanService.getScore(userID);
            for (int i=0; i<list2.size(); i++){
                List<StmCourse> list3 = yuanService.getStm(list2.get(i).getCourseID());
                list3.get(0).setMark(list2.get(i).getMark());
                arr1.add(list3.get(0));
            }
            arr.add(arr1);
            return arr;
        }
    }

    // 给教师添加课程
    @GetMapping("acourse")
    public Object acourse(@RequestParam String userName, String teacherID, String courseName, String courseTime, String classRoom, String courseWeek, String courseType, String score){
        List<Login> list1 = yuanService.getAcourse(teacherID, userName);
        if(list1.size()==0){// 表示没有此用户
            return 0;
        }else {
            int i = yuanService.getAcourse2(userName, teacherID, courseName,courseTime, classRoom , courseWeek, courseType, score);
            return i;
        }
    }

    // 学生评论老师
    @GetMapping("scomment")
    public Object scomment(@RequestParam String studentID, String teacherID, String text, String id){
        Repass repass= new Repass();
        if(Integer.parseInt(id)==1){
            int num = yuanService.getScomment(studentID, teacherID, text);
            repass.setAffectedRows(num);
            return repass;
        }else{
            List<Scomment> list1 = yuanService.getScomment2(studentID, teacherID);
            List<Student> list2 = yuanService.getAxuehao(studentID);
            for (int i = 0; i<list1.size(); i++){
                list1.get(i).setUrl(list2.get(0).getUrl());
                list1.get(i).setUserName(list2.get(0).getUserName());
            }
            return list1;
        }
    }

}
