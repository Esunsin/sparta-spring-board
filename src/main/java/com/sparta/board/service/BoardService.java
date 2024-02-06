package com.sparta.board.service;

import com.sparta.board.dto.board.RequestBoardDto;
import com.sparta.board.dto.board.ResponseBoardDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.User;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseBoardDto createBoard(RequestBoardDto requestBoardDto, Long userId){
        User user = userRepository.findById(userId).orElseThrow();

        Board board = new Board(requestBoardDto.getTitle(), requestBoardDto.getContent(),user.getUsername());
        Board saved = boardRepository.save(board);
        user.addBoard(saved);

        ResponseBoardDto boardDto = new ResponseBoardDto(saved.getTitle(), saved.getContent(), saved.getWriter(),saved.getDate());
        return boardDto;
    }
    public List<ResponseBoardDto> findAll(){
        List<Board> all = boardRepository.findAll();
        List<ResponseBoardDto> boardDtos = new ArrayList<>();
        for (Board board : all) {
            boardDtos.add(new ResponseBoardDto(board.getTitle(), board.getContent(), board.getWriter(), board.getDate()));
        }
        return boardDtos;
    }
}
