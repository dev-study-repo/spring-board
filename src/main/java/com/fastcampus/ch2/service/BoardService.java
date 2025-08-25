package com.fastcampus.ch2.service;

import com.fastcampus.ch2.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardDto> getBoardList() throws Exception;
    BoardDto getBoard(int bno) throws Exception;
    Map<String, Object> getBoardPage(int page, int pageSize) throws Exception;
    int countAllBoards() throws Exception; //전체 게시글 수
}
