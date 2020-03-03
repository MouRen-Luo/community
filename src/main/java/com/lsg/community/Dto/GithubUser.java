package com.lsg.community.Dto;

import lombok.Data;

@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    //图片
    private String avatar_url;
}
