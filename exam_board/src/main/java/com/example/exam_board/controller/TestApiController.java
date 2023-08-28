package com.example.exam_board.controller;

import com.example.exam_board.dto.TestForm;
import com.example.exam_board.entity.TestEntity;
import com.example.exam_board.service.TestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestApiController {
    @Autowired
    TestApiService testApiService;

    @GetMapping("/api/lists")
    public List<TestEntity> getLists() {
        return testApiService.viewList();
    }

    @GetMapping("/api/lists/{id}")
    public ResponseEntity<TestEntity> getOneList(@PathVariable Long id) {
        return testApiService.getOneList(id);
    }

    @PostMapping("/api/list")
    public TestEntity insert(@RequestBody TestForm form) {
        return testApiService.insertList(form);
    }

    @DeleteMapping("/api/lists/{id}")
    public ResponseEntity<TestEntity> delete(@PathVariable Long id) {
        return testApiService.deleteList(id);
    }

    @PatchMapping("/api/lists/{id}")
    public ResponseEntity<TestEntity> patch(@PathVariable Long id,
                                            @RequestBody TestForm form) {
        return testApiService.patchList(id, form);

    }
}
