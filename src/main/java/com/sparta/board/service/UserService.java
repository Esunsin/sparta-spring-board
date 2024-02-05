package com.sparta.board.service;

import com.sparta.board.dto.user.RequestUserDto;
import com.sparta.board.dto.user.ResponseUserDto;
import com.sparta.board.entity.User;
import com.sparta.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public ResponseUserDto join(RequestUserDto requestUserDto) {
        User user = new User(requestUserDto.getUsername(), requestUserDto.getPassword());
        User saved = userRepository.save(user);
        ResponseUserDto responseUserDto= new ResponseUserDto(saved.getUsername());
        return responseUserDto;
    }

    public List<ResponseUserDto> findAll(){
        List<User> users = userRepository.findAll();
        List<ResponseUserDto> responseUserDtos = new ArrayList<>();
        for (User user : users) {
            responseUserDtos.add(new ResponseUserDto(user.getUsername()));
        }
        return responseUserDtos;
    }

    public User findById(Long id) {
        User byId = userRepository.findById(id).orElseThrow();
        return byId;
    }
}
