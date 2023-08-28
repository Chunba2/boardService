package com.example.exam_board.service;

import com.example.exam_board.dto.ArticleCommentDto;
import com.example.exam_board.dto.ArticleForm;
import com.example.exam_board.entity.Article;
import com.example.exam_board.entity.ArticleComment;
import com.example.exam_board.entity.UserAccount;
import com.example.exam_board.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    EntityManager em;

    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    public void articleSave(ArticleForm articleForm, Principal principal) {
        UserAccount account = em.find(UserAccount.class, principal.getName());

        Article article = Article.builder()
                .title(articleForm.getTitle())
                .content(articleForm.getContent())
                .userAccount(account)
                .build();
        em.persist(article);
    }

    @Transactional
    public void articleUpdate(ArticleForm articleForm, Long id) {
        Article article = em.find(Article.class, id);
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        em.persist(article);
    }

    public List<ArticleForm> viewList() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleForm> dtoLists = new ArrayList<>();
        for (Article article : articles) {
            ArticleForm articleForm = ArticleForm.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .build();
            dtoLists.add(articleForm);
        }
        dtoLists.forEach(article -> System.out.println(article));
        return dtoLists;
    }

    public Page<Article> boardList(Pageable pageable, String type, String keyword) {
        if (type == null || keyword.isBlank()) {
            return articleRepository.findAll(pageable);
        }
        switch (type) {
            case "title":
                return articleRepository.findByTitleContains(keyword, pageable);
            case "content":
                return articleRepository.findByContentContains(keyword, pageable);
            case "userId":
                return articleRepository.findByUserAccount_UserIdContains(keyword, pageable);
            case "nickname":
                return articleRepository.findByUserAccount_NicknameContains(keyword, pageable);
            default:
                //기존 List<Board>값으로 넘어가지만 페이징 설정을 해주면 Page<Board>로 넘어갑니다.
                return articleRepository.findAll(pageable);
        }
    }

    public Article getOneArticle(Long id) {
        Article article = em.find(Article.class, id);
        return article;
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    @Transactional
    public void articleCommentSave(Long id, ArticleCommentDto articleDto, Principal principal) {
        UserAccount account = em.find(UserAccount.class, principal.getName());
        Article article = em.find(Article.class, id);

        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticle(article);
        articleComment.setUserAccount(account);
        articleComment.setContent(articleDto.getContent());

        article.getArticleCommentList().add(articleComment);

        em.persist(articleComment);
    }

    @Transactional
    public void articleCommentDelete(Long articleCommentId) {
        ArticleComment articleComment = em.find(ArticleComment.class, articleCommentId);
        em.remove(articleComment);
    }
}