package com.sparta.board.user;

import com.sparta.board.CommonResponseDto;
import com.sparta.board.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserRequestDTO userRequestDto) {
		try {
			userService.signup(userRequestDto);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()));
		}

		return ResponseEntity.status(HttpStatus.CREATED.value())
			.body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponseDto> login(@RequestBody UserRequestDTO userRequestDto, HttpServletResponse response) {
		try {
			userService.login(userRequestDto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}

		response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));

		return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
	}
}
