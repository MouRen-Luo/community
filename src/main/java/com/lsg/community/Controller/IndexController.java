package com.lsg.community.Controller;

import com.lsg.community.Dto.PaginationDTO;
import com.lsg.community.Mapper.UserMapper;
import com.lsg.community.Model.User;
import com.lsg.community.Service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "content",required = false)String content,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        PaginationDTO pagination = questionService.list(content,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("content",content);
        return "index";
    }
}
