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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;



    @Transactional
    public ResponseUserDto join(RequestUserDto requestUserDto) {
        //중복체크
        duplicationValidateByName(requestUserDto.getUsername());

        User user = new User(requestUserDto.getUsername(), requestUserDto.getPassword());
        User saved = userRepository.save(user);
        return new ResponseUserDto(saved.getUsername());
    }

    public List<ResponseUserDto> findAll(){
        List<User> users = userRepository.findAll();
        List<ResponseUserDto> responseUserDtos = new ArrayList<>();
        for (User user : users) {
            responseUserDtos.add(new ResponseUserDto(user.getUsername()));
        }
        return responseUserDtos;
    }

    //중복체크(DB에 중복 회원이 있는지 확인)
    private void duplicationValidateByName(String name){
        Optional<User> byName = userRepository.findByUsername(name);
        if(byName.isPresent()){
            throw new IllegalArgumentException("존재하는 회원입니다.");
        }
    }
}
