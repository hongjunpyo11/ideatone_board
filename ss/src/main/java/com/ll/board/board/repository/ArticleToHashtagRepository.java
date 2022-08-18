package com.ll.board.board.repository;

import com.ll.board.board.domain.article.ArticleComment;
import com.ll.board.board.domain.article.ArticleToHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleToHashtagRepository extends JpaRepository<ArticleToHashtag, Long> {
}
