package com.lsg.community.Service;

import com.lsg.community.Dto.NotificationDTO;
import com.lsg.community.Dto.PaginationDTO;
import com.lsg.community.Dto.QuestionDTO;
import com.lsg.community.Mapper.CommentMapper;
import com.lsg.community.Mapper.NotificationMapper;
import com.lsg.community.Model.Notification;
import com.lsg.community.Model.NotificationExample;
import com.lsg.community.Model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public static PaginationDTO llist(Long id, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        NotificationExample countexample = new NotificationExample();
        countexample.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int)notificationMapper.countByExample(countexample);
        totalPage= totalCount % size == 0 ? (totalCount/size):(totalCount/size+1);
        if (totalPage<=0){
            totalPage=1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagintion(totalPage,page);
        //获得当前页码
        Integer offset = size*(page-1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }
}
