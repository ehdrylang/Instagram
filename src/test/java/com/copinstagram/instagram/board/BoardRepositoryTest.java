package com.copinstagram.instagram.board;

import com.copinstagram.instagram.board.model.entity.Board;
import com.copinstagram.instagram.board.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    public void cleanup(){
        boardRepository.deleteAll();
    }
    @Test
    public void givenBoardEntityRepository_whenSaveAndRetreiveEntity_thenOK(){
        //given
        Board givenBoard = Board.builder()
                .content("content")
                .author("author")
                .build();
        //when
        Board thenBoard = boardRepository.save(givenBoard);
        Optional<Board> optReadBoard = boardRepository.findById(thenBoard.getId());
        Board readBoard = optReadBoard.orElseGet(()->Board.builder()
                .content("")
                .author("")
                .build());
        //then
        assertThat(readBoard.getAuthor(), is(givenBoard.getAuthor()));
        assertThat(thenBoard.getContent(), is(givenBoard.getContent()));
    }
}
