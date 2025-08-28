package com.fastcampus.ch2.controller;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //댓글 조회
    @GetMapping("/{bno}")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> comment(@PathVariable("bno") Integer bno) throws Exception {
        List<CommentDto> comments = commentService.getCommentList(bno);
        return ResponseEntity.ok(comments);
    }

    //댓글 작성
    @PostMapping(value="/{bno}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) throws Exception {
        try {
            commentService.insertComment(commentDto);
            return ResponseEntity.ok("댓글이 등록되었습니다!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("댓글 작성 중 오류가 발생했습니다.");
        }
    }

    //댓글 수정
    @PostMapping(value = "/modify", produces = "text/plain; charset=UTF-8")
    public ResponseEntity<String> modifyComment(@RequestBody CommentDto commentDto) throws Exception {
        try {
            commentService.updateComment(commentDto);
            return ResponseEntity.ok("댓글이 수정되었습니다.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("댓글 수정 중 오류가 발생하였습니다.");
        }
    }

    //댓글 삭제
    @DeleteMapping(value = "/{cno}", produces = "text/plain; charset=UTF-8")
    public ResponseEntity<String> deleteComment(@PathVariable("cno") Integer cno) throws Exception {
        try {
            commentService.deleteComment(cno);
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("댓글 삭제 중 오류가 발생했습니다.");
        }
    }
}
