package com.lsg.community.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagintion(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        //页码显示
        pages.add(page);
        for (int i=1;i<=2;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        showPrevious=page == 1 ? false:true;
        //是否展示下一页
        showNext=page == totalPage ? false:true;
        //是否展示首页
        showFirstPage=pages.contains(1)? false:true;
        //是否展示尾页
        showEndPage =pages.contains(totalPage) ? false:true;
    }
}