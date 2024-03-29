package com.sparta.board.todo;

import java.util.List;

import com.sparta.board.user.UserDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TodoListResponseDTO {
	private UserDTO user;
	private List<TodoResponseDTO> todoList;

	public TodoListResponseDTO(UserDTO user, List<TodoResponseDTO> todoList) {
		this.user = user;
		this.todoList = todoList;
	}
}
