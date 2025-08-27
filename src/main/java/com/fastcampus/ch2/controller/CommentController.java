package com.fastcampus.ch2.controller;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{bno}")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> comment(@PathVariable("bno") Integer bno) throws Exception {
        List<CommentDto> comments = commentService.getCommentList(bno);
        System.out.println(comments);
        return ResponseEntity.ok(comments);
    }
}
