package com.lsg.community.Cache;

import com.lsg.community.Dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> objects = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","java","html"));
        objects.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Spring","flask","django","koa"));
        objects.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","负载均衡","缓存 tomcat"));
        objects.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","oracle","sqlserver"));
        objects.add(db);
        return objects;
    }

    public static String filterinvalid(String tags){
        String[] split = StringUtils.split(tags, "，");
        List<TagDTO> tagDTOS = get();
        List<String> collect = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !collect.contains(t)).collect(Collectors.joining("，"));
        return invalid;
    }
}
