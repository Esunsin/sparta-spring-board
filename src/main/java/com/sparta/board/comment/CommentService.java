package com.sparta.board.comment;

import java.util.concurrent.RejectedExecutionException;

import com.sparta.board.todo.Todo;
import com.sparta.board.todo.TodoRepository;
import com.sparta.board.todo.TodoService;
import com.sparta.board.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final TodoRepository todoRepository;

	public CommentResponseDTO createComment(CommentRequestDTO dto, User user) {
		Todo todo = todoRepository.findById(dto.getTodoId()).orElseThrow(() -> new NullPointerException("없는 게시물입니다."));

		Comment comment = new Comment(dto);
		comment.setUser(user);
		comment.setTodo(todo);

		commentRepository.save(comment);

		return new CommentResponseDTO(comment);
	}

	@Transactional
	public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, User user) {
		Comment comment = getUserComment(commentId, user);

		comment.setText(commentRequestDTO.getText());

		return new CommentResponseDTO(comment);
	}

	public void deleteComment(Long commentId, User user) {
		Comment comment = getUserComment(commentId, user);

		commentRepository.delete(comment);
	}

	private Comment getUserComment(Long commentId, User user) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

		if(!user.getId().equals(comment.getUser().getId())) {
			throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
		}
		return comment;
	}
}
