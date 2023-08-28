package com.example.exam_board.api;

import com.example.exam_board.dto.ArticleApiDto;
import com.example.exam_board.dto.TestForm;
import com.example.exam_board.entity.Article;
import com.example.exam_board.entity.TestEntity;
import com.example.exam_board.repository.ArticleRepository;
import groovyjarjarantlr4.v4.runtime.dfa.EdgeMap;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleApiService {
    @Autowired
    ArticleRepository repository;

    @Autowired
    EntityManager em;

    public List<ArticleApiDto> viewList() {
        List<Article> articles = repository.findAll();
        List<ArticleApiDto> lists = new ArrayList<>();

        for (Article article : articles) {
            ArticleApiDto apiDto = new ArticleApiDto();
            apiDto.setId(article.getId());
            apiDto.setTitle(article.getTitle());
            apiDto.setContent(article.getContent());
            lists.add(apiDto);
        }
        return lists;
    }

    public ResponseEntity<ArticleApiDto> getOneList(Long id) {
        Article article = repository.findById(id).orElse(null);
        ArticleApiDto dto = new ArticleApiDto();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        if(article == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Transactional
    public ResponseEntity<Article> insertList(ArticleApiDto dto) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        em.persist(article);

        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @Transactional
    public ResponseEntity<ArticleApiDto> deleteList(Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @Transactional
    public ResponseEntity<ArticleApiDto> patchList(Long id, ArticleApiDto dto) {
        Article article = repository.findById(id).orElse(null);
        if ( id != dto.getId() || article == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            article.setId(dto.getId());
            article.setTitle(dto.getTitle());
            article.setContent(dto.getContent());
            Article article1 = repository.save(article);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }
}
