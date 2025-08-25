package com.fastcampus.ch2.controller;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) throws Exception {
        try{
            //List<BoardDto> boards = boardService.getBoardList(); //전체 목록
            Map<String, Object> result = boardService.getBoardPage(page,10);
            model.addAttribute("boards", result.get("boards"));
            model.addAttribute("pageSize", result.get("pageSize"));
            model.addAttribute("page", result.get("page"));
            model.addAttribute("total", result.get("total"));
        }catch(Exception e){
            model.addAttribute("errormsg", "게시판 정보를 가져오는 중 오류가 발생했습니다.");
        }
        return "boardList";
    }


}
