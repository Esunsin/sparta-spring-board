package com.sparta.board.dto.board;

import com.sparta.board.entity.Review;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseBoardDto {
    private String title;
    private String content;
    private String writer;
    private String date;
    public ResponseBoardDto(String title, String content, String writer,String date) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
    }



}
