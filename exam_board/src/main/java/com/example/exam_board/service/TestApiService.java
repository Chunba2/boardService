package com.example.exam_board.service;

import com.example.exam_board.dto.TestForm;
import com.example.exam_board.entity.TestEntity;
import com.example.exam_board.repository.TestEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestApiService {
    @Autowired
    TestEntityRepository testEntityRepository;

    public List<TestEntity> viewList() {
        return testEntityRepository.findAll();
    }

    public ResponseEntity<TestEntity> getOneList(Long id) {
        TestEntity entity = testEntityRepository.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @Transactional
    public TestEntity insertList(TestForm form) {
        TestEntity entity = new TestEntity();
        entity.setMemo(form.getMemo());
        return testEntityRepository.save(entity);
    }

    public ResponseEntity<TestEntity> deleteList(Long id) {
        TestEntity entity = testEntityRepository.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            testEntityRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    public ResponseEntity<TestEntity> patchList(Long id, TestForm form) {
        TestEntity entity = testEntityRepository.findById(id).orElse(null);
        if (id != form.getId() || entity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            entity.setMemo(form.getMemo());
            TestEntity updatedEntity = testEntityRepository.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
        }
    }
}
