package com.example.exam_board.controller;

import com.example.exam_board.api.ArticleApiService;
import com.example.exam_board.dto.ArticleApiDto;
import com.example.exam_board.dto.TestForm;
import com.example.exam_board.entity.Article;
import com.example.exam_board.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired
    ArticleApiService service;

    @GetMapping("/api/articles")
    public List<ArticleApiDto> getLists(){
        return service.viewList();
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleApiDto> getOneList(@PathVariable Long id){
        return service.getOneList(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> insert(@RequestBody ArticleApiDto dto ){
        return service.insertList(dto);
    }




    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleApiDto> delete(@PathVariable Long id){
        return service.deleteList(id);
    }


    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleApiDto> Patch(@PathVariable Long id,
                                               @RequestBody ArticleApiDto dto){
        return service.patchList(id,dto);
    }
}
