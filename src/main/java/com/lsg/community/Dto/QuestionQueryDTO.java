package com.lsg.community.Dto;

import lombok.Data;

@Data
public class QuestionQueryDTO {
    private String content;
    private Integer page;
    private Integer size;
}
