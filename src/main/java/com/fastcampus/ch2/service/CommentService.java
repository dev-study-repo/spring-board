package com.fastcampus.ch2.service;

import com.fastcampus.ch2.domain.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentList(Integer bno) throws Exception; //해당 게시글의 댓글 조회
}
