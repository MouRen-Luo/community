package com.lsg.community.Dto;

import com.lsg.community.Model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User user;
    private String outerTitle;
    private String type;
}
