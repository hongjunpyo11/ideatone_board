package com.ll.board.board.service;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.domain.article.ArticleComment;
import com.ll.board.board.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;

    public void create(Article article, String content) {
        ArticleComment articleComment = new ArticleComment();
        articleComment.setComment(content);
        articleComment.setCreate(LocalDateTime.now());
        article.addArticleComment(articleComment);
        articleCommentRepository.save(articleComment);
    }
}
