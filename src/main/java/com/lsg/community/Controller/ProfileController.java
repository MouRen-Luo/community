package com.lsg.community.Controller;

import com.lsg.community.Dto.PaginationDTO;
import com.lsg.community.Mapper.UserMapper;
import com.lsg.community.Model.User;
import com.lsg.community.Service.NotificationService;
import com.lsg.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size){
        User user =(User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        }else if ("replies".equals(action)){
            PaginationDTO paginationDTO = NotificationService.llist(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
