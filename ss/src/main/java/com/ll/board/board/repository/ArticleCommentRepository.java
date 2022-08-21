package com.ll.board.board.repository;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.domain.article.ArticleComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    List<ArticleComment> findByArticleAndCommentBundle(Article article, ArticleComment commentBundle);
}
