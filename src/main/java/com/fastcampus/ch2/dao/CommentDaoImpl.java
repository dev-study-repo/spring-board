package com.fastcampus.ch2.dao;

import com.fastcampus.ch2.domain.CommentDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SqlSessionTemplate session;
    private static String namespace = "com.fastcampus.ch2.mapper.CommentMapper.";

    @Override //해당 게시글의 댓글 조회
    public List<CommentDto> selectAll(Integer bno) throws Exception {
        return session.selectList(namespace + "selectAll", bno);
    }

    @Override //댓글 작성
    public void insert(CommentDto commentDto) throws Exception {
        session.insert(namespace + "insert", commentDto);
    }

    @Override //댓글 수정
    public void update(CommentDto commentDto) throws Exception {
        session.update(namespace + "update", commentDto);
    }
}
