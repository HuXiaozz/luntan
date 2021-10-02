package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.entity.BmsComment;
import com.example.demo.model.entity.UmsUser;
import com.example.demo.model.vo.CommentVO;

import java.util.List;

public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);




}

