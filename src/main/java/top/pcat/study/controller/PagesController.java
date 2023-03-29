package top.pcat.study.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PagesController {

    @GetMapping("/yixuan/{userId}")
    public String yixuan(String userId){
        return "yixuan";
    }

    @GetMapping("/pages/subject")
    public String subject(){
        return "subject";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
