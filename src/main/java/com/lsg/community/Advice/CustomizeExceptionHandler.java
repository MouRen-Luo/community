package com.lsg.community.Advice;

import com.alibaba.fastjson.JSON;
import com.lsg.community.Dto.ResultDTO;
import com.lsg.community.Exception.CustomizeErrorCode;
import com.lsg.community.Exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model,
                  HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            ResultDTO resultDTO = null;
            if (ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            }else{
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
            }
            return null;
        }else{
            if (ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }
}
