package com.lsg.community.Mapper;

import com.lsg.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,image_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{imageUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

    @Select("select * from user where id=#{creator}")
    User findById(Integer creator);
}
