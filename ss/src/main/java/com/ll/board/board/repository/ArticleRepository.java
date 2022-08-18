package com.ll.board.board.repository;

import com.ll.board.board.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
