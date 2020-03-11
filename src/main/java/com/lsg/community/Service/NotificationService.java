package com.lsg.community.Service;

import com.lsg.community.Dto.NotificationDTO;
import com.lsg.community.Dto.PaginationDTO;
import com.lsg.community.Exception.CustomizeErrorCode;
import com.lsg.community.Exception.CustomizeException;
import com.lsg.community.Mapper.NotificationMapper;
import com.lsg.community.Mapper.UserMapper;
import com.lsg.community.Model.Notification;
import com.lsg.community.Model.NotificationExample;
import com.lsg.community.Model.User;
import com.lsg.community.Model.UserExample;
import com.lsg.community.enums.NotificationStatusEnum;
import com.lsg.community.enums.NotificationTypeEnum;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long id, Integer page, Integer size) {
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
        if (notificationList.size() == 0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long id) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id)
                .andStatusEqualTo(NotificationStatusEnum.UNREND.getStatus());
        return notificationMapper.countByExample(example);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (notification.getReceiver()!=user.getId()){
            throw  new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
