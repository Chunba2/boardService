package com.example.exam_board.repository;

import com.example.exam_board.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;


@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

//    @Test
//    @DisplayName("타이틀로 검색")
//    void findByTitleContains() {
//        Page<Article> articles = articleRepository.findByTitleContains("era");
//        for (Article article : articles){
//            System.out.println(article);
//        }
//    }
}