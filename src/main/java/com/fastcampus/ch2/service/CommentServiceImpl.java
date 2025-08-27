package com.fastcampus.ch2.service;

import com.fastcampus.ch2.dao.CommentDao;
import com.fastcampus.ch2.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override //해당 게시글의 댓글 조회
    public List<CommentDto> getCommentList(Integer bno) throws Exception {
        return commentDao.selectAll(bno);
    }

    @Override //댓글 작성
    public void insertComment(CommentDto commentDto) throws Exception {
        commentDao.insert(commentDto);
    }

    @Override //댓글 수정
    public void updateComment(CommentDto commentDto) throws Exception {
        commentDao.update(commentDto);
    }
}
