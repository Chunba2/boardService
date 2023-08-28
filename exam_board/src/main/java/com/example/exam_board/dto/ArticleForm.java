package com.example.exam_board.dto;

import com.example.exam_board.entity.UserAccount;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArticleForm {
    private Long id;
    private UserAccount userAccount;
    private String title;
    private String content;

}
