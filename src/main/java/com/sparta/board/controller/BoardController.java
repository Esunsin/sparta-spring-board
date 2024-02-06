package com.sparta.board.controller;

import com.sparta.board.dto.board.RequestBoardDto;
import com.sparta.board.dto.board.ResponseBoardDto;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/{userId}")
    public ResponseBoardDto createBoard(@PathVariable Long userId, @RequestBody RequestBoardDto boardDto) {
        return boardService.createBoard(boardDto,userId);
    }

    @GetMapping
    public List<ResponseBoardDto> showAll(){
        return boardService.findAll();
    }
}
