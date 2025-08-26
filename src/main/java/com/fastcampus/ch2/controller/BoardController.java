package com.fastcampus.ch2.controller;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.UserDto;
import com.fastcampus.ch2.service.BoardService;
import com.fastcampus.ch2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UserService userService;

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
    @PostMapping(value = "/write", produces = "text/plain;charset=UTF-8") //게시글 작성
    public ResponseEntity<String> writeBoard(@RequestBody BoardDto boardDto, HttpSession session) throws Exception {
        try {
            BoardDto board = new BoardDto();
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setWriter(boardDto.getWriter());

            boardService.insertBoard(board);
            return ResponseEntity.ok("게시글 등록이 완료되었습니다.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시글 작성 중 오류가 발생햇습니다.");
        }
    }

    @GetMapping("/modify/{bno}") //게시글 수정 페이지
    public String modifyForm(@PathVariable int bno, Model model) throws Exception {
        BoardDto board = boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "boardModify";
    }

    @PostMapping(value = "/remove/{bno}", produces = "text/plain;charset=UTF-8") //게시글 삭제
    public ResponseEntity<String> removeBoard(@PathVariable int bno) throws Exception {
        try {
            boardService.deleteBoard(bno);
            return ResponseEntity.ok("삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 중 오류가 발생했습니다.");
        }
    }
}
