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
    public List<Student> smessage(@RequestParam String userID){
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
        ArrayList atm2 = new ArrayList();
        // 查teacher表，为了查看盖老师的课程信息
        List<Teacher> tlist = yuanService.getAtm(userID);
        if(tlist.size()==1){
            // 查login表,为了获取名字
            List<Login> tlist2 = yuanService.getTmessage(userID);
            tlist.get(0).setUserName(tlist2.get(0).getUserName());
            atm.add(tlist.get(0));
            // 查course，为了查看该老师课程信息
            List<Course> clist = yuanService.getAtm2(userID);
            if(clist.size()>0){ // 表示该老师有课程信息
                for(int i = 0; i<clist.size(); i++){
                    atm2.add(clist.get(i));
                }
            }
            atm.add(atm2);
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
    public Object axuehao(@RequestParam String userName, String userID, String sex, String birthyear, String grade, String college){
        Repass repass= new Repass();
        // 查student表
        List<Student> list1 = yuanService.getAxuehao(userID);
        if(list1.size()==0){
            // 插入到student表
            int l1 = yuanService.getAxuehao2(userName, userID, sex, birthyear, grade, college);
            // 插入到login表
            int l2 = yuanService.getAxuehao3(userName, userID);
            if(l1 == 1&&l2 == 1){
                repass.setAffectedRows(1);
                return repass;
            }else {
                repass.setAffectedRows(2);
                return repass;
            }
        }else {
            repass.setAffectedRows(0);
            return repass;
        }
    }

    // 管理员add教师
    @GetMapping("agonghao")
    public Object agonghao(@RequestParam String userName, String userID, String sex, String degree, String title, String birthyear, String grade, String college){
        Repass repass= new Repass();
        // 查teacher表
        List<Teacher> list1 = yuanService.getAgonghao(userID);
        if(list1.size()==0){
            // 插入到teacher表
            int l1 = yuanService.getAgonghao2(userID, sex, degree, title, birthyear, grade, college);
            // 插入到login表
            int l2 = yuanService.getAgonghao3(userName, userID);
            if(l1 == 1&&l2 == 1){
                repass.setAffectedRows(1);
                return repass;
            }else {
                repass.setAffectedRows(2);
                return repass;
            }
        }else {
            repass.setAffectedRows(0);
            return repass;
        }
    }

    // 查看学生信息
    @GetMapping("stm")
    public Object stm(@RequestParam String userID){
        ArrayList arr = new ArrayList();
        ArrayList arr1 = new ArrayList();
        // 查student表，获取学生学生信息
        List<Student> list1 = yuanService.getSmessage(userID);
        // 判断该学生存不存在
        if(list1.size()==0){
            return 0;
        }else {
            arr.add(list1.get(0));
            // 查selectedcourse表，为了获取
            List<SelectCourse> list2 = yuanService.getScore(userID);
            for (int i=0; i<list2.size(); i++){
                // 查course表，获取学生课程信息
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
        Repass repass= new Repass();
        // 查login表，看看有没有这个用户
        List<Login> list1 = yuanService.getAcourse(teacherID, userName);
        // 判断没有此用户
        if(list1.size()==0){
            repass.setAffectedRows(0);
            return repass;
        }else {
            // 查course表,看看此课程是否已添加
            List<Course> list2 = yuanService.getAcourse3(courseName, teacherID);
            // 判断此课程是否已添加
            if(list2.size()==0){
                // 插入到course表
                int i = yuanService.getAcourse2(userName, teacherID, courseName,courseTime, classRoom , courseWeek, courseType, score);
                repass.setAffectedRows(i);
                return repass;
            }else {
                repass.setAffectedRows(2);
                return repass;
            }
        }
    }

    // 学生评论老师
    @GetMapping("scomment")
    public Object scomment(@RequestParam String studentID, String teacherID, String text, String id){
        Repass repass= new Repass();
        if(Integer.parseInt(id)==1){// 1表示插入
            // 将评论插入到comment表
            int num = yuanService.getScomment(studentID, teacherID, text);
            repass.setAffectedRows(num);
            return repass;
        }else{
            // 查询comment表
            List<Scomment> list1 = yuanService.getScomment2(studentID, teacherID);
            // 查询Student表
            List<Student> list2 = yuanService.getAxuehao(studentID);
            for (int i = 0; i<list1.size(); i++){
                list1.get(i).setUrl(list2.get(0).getUrl());
                list1.get(i).setUserName(list2.get(0).getUserName());
            }
            return list1;
        }
    }

    // 删除学生
    @GetMapping("delSt")
    public Object delSt(@RequestParam String studentID){
        Repass repass= new Repass();
        // 查login表
        List<Login> list = yuanService.getArepass(studentID);
        if(list.size()!=0){
            // 删除login的数据
            int num1 =  yuanService.delDelSt1(studentID);
            // 删除student的数据
            int num2 =  yuanService.delDelSt2(studentID);
            // 删除selectedcourse的数据
            int num3 =  yuanService.delDelSt3(studentID);
            // 删除comment的数据
            int num4 =  yuanService.delDelSt4(studentID);
            repass.setAffectedRows(1);
            return repass;
        }else {
            repass.setAffectedRows(0);
            return repass;
        }
    }

    // 删除教工
    @GetMapping("DelTt")
    public Object DelTt(@RequestParam String teacherID){
        Repass repass= new Repass();
        // 查login表
        List<Login> list = yuanService.getArepass(teacherID);
        if(list.size()!=0){
            // 查course，为了查看该老师课程信息
            List<Course> clist = yuanService.getAtm2(teacherID);
            for(int i=0; i<clist.size(); i++){
                String s = String.valueOf(clist.get(i).getCourseID());
                // 删除该老师selectedcourse表的相关课程
                int num1 = yuanService.delDelTt1(s);
            }
            // 删除comment表中，对应的老师的评论
            int num2 = yuanService.delDelTt2(teacherID);
            // 删除teacher表中
            int num3 = yuanService.delDelTt3(teacherID);
            // 删除course表中
            int num4 = yuanService.delDelTt4(teacherID);
            // 删除login表中，对应的老师的登录信息
            int num5 =  yuanService.delDelSt1(teacherID);
            repass.setAffectedRows(1);
            return repass;
        }else {
            repass.setAffectedRows(0);
            return repass;
        }
    }

    // 师生分布
    @GetMapping("distribution")
    public Object distribution(){
        ArrayList arr = new ArrayList();
        // 查login表
        List<Login> list1 =  yuanService.getDistribution("1");// 学生
        List<Login> list2 =  yuanService.getDistribution("2");// 教师
        arr.add(list1.size());// 学生
        arr.add(list2.size());// 教师
        return arr;
    }
}
