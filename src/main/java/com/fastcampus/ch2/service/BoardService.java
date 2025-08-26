package com.fastcampus.ch2.service;

import com.fastcampus.ch2.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardDto> getBoardList() throws Exception; //전체 게시글 조회
    BoardDto getBoard(int bno) throws Exception; //게시글 상세 조회
    Map<String, Object> getBoardPage(int page, int pageSize) throws Exception; //전체 게시글 조회 - 페이지
    int countAllBoards() throws Exception; //전체 게시글 수
    int insertBoard(BoardDto boardDto) throws Exception; //게시글 작성
    int deleteBoard(int bno) throws Exception; //게시글 삭제
}
