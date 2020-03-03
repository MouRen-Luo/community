package com.lsg.community.Controller;

import com.lsg.community.Mapper.QuestionMapper;
import com.lsg.community.Model.Question;
import com.lsg.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPblish(Question question,
                           HttpServletRequest request,
                           Model model){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if(question.getTitle() == null || question.getTitle()==""){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        if(question.getDescription() == null || question.getDescription()==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        User user =(User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
