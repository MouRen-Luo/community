package com.lsg.community.Dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private String type;
}
