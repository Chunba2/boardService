package com.example.exam_board.repository;

import com.example.exam_board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContains(String keyword, Pageable pageable);

    Page<Article> findByContentContains(String keyword, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContains(String keyword, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContains(String keyword, Pageable pageable);
}

