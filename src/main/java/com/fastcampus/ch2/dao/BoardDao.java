package com.fastcampus.ch2.dao;

import java.util.*;
import com.fastcampus.ch2.domain.*;


public interface BoardDao {
	BoardDto select(Integer bno) throws Exception; //게시글 1개 불러오기
    List<BoardDto> selectAll() throws Exception; //게시글 전체 불러오기
    List<BoardDto> selectPage(Map<String,Integer> map) throws Exception; //게시글 페이징
    int count() throws Exception; //전체 게시글 수
    int insert(BoardDto dto) throws Exception; //게시글 작성
    int delete(Integer bno) throws Exception; //게시글 삭제
    int update(BoardDto dto) throws Exception; //게시글 수정


//    int increaseViewCnt(Integer bno) throws Exception;

//    int deleteAll() throws Exception;


//    int searchResultCnt(SearchCondition sc) throws Exception;
//    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

}
