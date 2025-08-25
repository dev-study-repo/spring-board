package com.fastcampus.ch2.controller;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list") //게시글 전체 조회
    public String list(@RequestParam(defaultValue = "1") int page, Model model) throws Exception {
        try{
            //List<BoardDto> boards = boardService.getBoardList(); //전체 목록
            Map<String, Object> result = boardService.getBoardPage(page,10);
            model.addAttribute("boards", result.get("boards"));
            model.addAttribute("pageSize", result.get("pageSize"));
            model.addAttribute("page", result.get("page"));
            model.addAttribute("total", result.get("total"));
        }catch(Exception e){
            model.addAttribute("errors", "게시판 정보를 가져오는 중 오류가 발생했습니다.");
        }
        return "boardList";
    }

    @GetMapping("/{bno}") //게시글 상세 조회
    public String detailBoard(@PathVariable int bno, Model model) throws Exception {
        BoardDto board = boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/write") //게시글 작성 페이지
    public String writeForm() {
        return "boardWrite";
    }
    @PostMapping("/write") //게시글 작성
    public String writeBoard(String title, String content, HttpSession session) throws Exception {
        BoardDto board = new BoardDto();
        board.setTitle(title);
        board.setContent(content);
        String writer = session.getAttribute("id").toString();
        board.setWriter(writer);
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{bno}") //게시글 수정 페이지
    public String modifyForm(@PathVariable int bno, Model model) throws Exception {
        BoardDto board = boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "boardModify";
    }

}
