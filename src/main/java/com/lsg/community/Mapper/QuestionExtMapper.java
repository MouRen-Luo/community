package com.lsg.community.Mapper;

import com.lsg.community.Dto.QuestionQueryDTO;
import com.lsg.community.Model.Question;
import com.lsg.community.Model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countByContent(QuestionQueryDTO questionQueryDTO);

    List<Question> selectByContent(QuestionQueryDTO questionQueryDTO);
}