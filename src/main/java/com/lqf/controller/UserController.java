package com.lqf.controller;

import com.lqf.entity.User;
import com.lqf.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;

    public String login(String uname, String pwd, HttpSession session){
        User currUser = userService.login(uname, pwd);
        if(currUser!=null){
            session.setAttribute("currUser",currUser);
            return "redirect:book.do?operateType=setBookList";
        }
        return "user/login";
    }

    public String regist(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse resp){
        Object kaptcha_session_key = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptcha_session_key==null || !verifyCode.equals(kaptcha_session_key)){
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            try {
                PrintWriter writer = resp.getWriter();
                writer.println("<script type='text/javascript'>alert('验证码不正确');</script>");
                return "user/regist";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            if(verifyCode.equals(kaptcha_session_key)){
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }

        return "user/login";
    }

    public String ckUname(String uname){
        User userByUname = userService.getUserByUname(uname);
        if(userByUname!=null){
            return "json:{'uname':'1'}";
        }else{
            return "json:{'uname':'0'}";
        }
    }
}
