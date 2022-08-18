package com.ll.board.board.repository;

import com.ll.board.board.domain.article.ArticleComment;
import com.ll.board.board.domain.article.ArticleHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {


}
