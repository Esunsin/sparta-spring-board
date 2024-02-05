package com.sparta.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "board")
    private List<Board> boards = new ArrayList<>();
}
