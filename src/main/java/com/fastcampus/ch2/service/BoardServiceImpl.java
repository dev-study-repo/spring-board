package com.fastcampus.ch2.service;

import com.fastcampus.ch2.dao.BoardDao;
import com.fastcampus.ch2.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDao boardDao;

    @Override //게시글 전체 조회
    public List<BoardDto> getBoardList() throws Exception{
        return boardDao.selectAll();
    }

    @Override //게시글 상세 조회
    public BoardDto getBoard(int bno) throws Exception {
        return boardDao.select(bno);
    }

    @Override //게시글 조회 - 페이지
    public Map<String, Object> getBoardPage(int page, int pageSize) throws Exception {
        int offset = (page - 1) * pageSize;
        Map<String, Integer> map = new HashMap<>();
        map.put("offset", offset);
        map.put("pageSize", pageSize);

        List<BoardDto> boards = boardDao.selectPage(map);
        int total = countAllBoards();

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("boards", boards);
        result.put("page", page);
        result.put("pageSize", pageSize);

        return result;
    }

    @Override //전체 게시글 수
    public int countAllBoards() throws Exception {
        return boardDao.count();
    }

    @Override //게시글 작성
    public int insertBoard(BoardDto boardDto) throws Exception {
        return boardDao.insert(boardDto);
    }

    @Override //게시글 삭제
    public int deleteBoard(int bno) throws Exception {
        return boardDao.delete(bno);
    }

}
