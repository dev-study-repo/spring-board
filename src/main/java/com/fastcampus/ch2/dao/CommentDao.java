package com.fastcampus.ch2.dao;

import com.fastcampus.ch2.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    List<CommentDto> selectAll(Integer bno) throws Exception; //해당 게시글의 댓글 조회
    void insert(CommentDto commentDto) throws Exception; //댓글 작성
    void update(CommentDto commentDto) throws Exception; //댓글 수정
}
