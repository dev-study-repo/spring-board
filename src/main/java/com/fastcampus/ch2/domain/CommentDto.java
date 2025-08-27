package com.fastcampus.ch2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class CommentDto {
    private Integer cno;        // 댓글 번호 (PK)
    private Integer bno;        // 게시글 번호 (FK -> board.bno)
    private Integer pcno;       // 부모 댓글 번호 (FK -> comment.cno, 대댓글 구현용)
    private String comment;     // 댓글 내용
    private String commenter;   // 작성자
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date reg_date; // 등록일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date up_date; //수정일

    public CommentDto() {}
    public CommentDto(Integer cno, Integer bno, Integer pcno, String comment, String commenter, Date reg_date, Date up_date) {
        this.cno = cno;
        this.bno = bno;
        this.pcno = pcno;
        this.comment = comment;
        this.commenter = commenter;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }


    @Override
    public String toString() {
        return "CommentDto{" +
                "cno=" + cno +
                ", bno=" + bno +
                ", pcno=" + pcno +
                ", comment='" + comment + '\'' +
                ", commenter='" + commenter + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(cno, that.cno) && Objects.equals(bno, that.bno) && Objects.equals(pcno, that.pcno) && Objects.equals(comment, that.comment) && Objects.equals(commenter, that.commenter) && Objects.equals(reg_date, that.reg_date) && Objects.equals(up_date, that.up_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, bno, pcno, comment, commenter, reg_date, up_date);
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public Integer getPcno() {
        return pcno;
    }

    public void setPcno(Integer pcno) {
        this.pcno = pcno;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }
}
