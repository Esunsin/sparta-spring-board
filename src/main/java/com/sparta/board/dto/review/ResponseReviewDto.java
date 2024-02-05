package com.sparta.board.dto.review;

import lombok.Getter;

@Getter
public class ResponseReviewDto {
    private String content;
    private String writer;

    public ResponseReviewDto(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}
