package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login, HttpSession session) {

        Integer username = login.getUsername();
        String password = login.getPassword();

        Admin adminRes = loginService.adminLogin(username, password);
        if (adminRes != null) {
            // 存储管理员登录信息
            session.setAttribute("userId", adminRes.getAdminId());
            session.setAttribute("userType", "admin");
            return ApiResultHandler.buildApiResult(200, "请求成功", adminRes);
        }

        Teacher teacherRes = loginService.teacherLogin(username, password);
        if (teacherRes != null) {
            // 存储教师登录信息
            session.setAttribute("userId", teacherRes.getTeacherId());
            session.setAttribute("userType", "teacher");
            return ApiResultHandler.buildApiResult(200, "请求成功", teacherRes);
        }

        Student studentRes = loginService.studentLogin(username, password);
        if (studentRes != null) {
            // 存储学生登录信息
            session.setAttribute("userId", studentRes.getStudentId());
            session.setAttribute("userType", "student");
            return ApiResultHandler.buildApiResult(200, "请求成功", studentRes);
        }

        // 登录失败，清除session
        session.setAttribute("userId", null);
        session.setAttribute("userType", null);

        return ApiResultHandler.buildApiResult(400, "请求失败", null);
    }
}
