package com.sparta.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String date;

    public Board(String title, String content, String writer) {

        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date());
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Review> reviews = new ArrayList<>();

    protected Board() {

    }

}
