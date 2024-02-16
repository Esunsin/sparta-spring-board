package com.sparta.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    //private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        //String password = passwordEncoder.encode(userRequestDto.getPassword());

        if(userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("already exist username");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

}
