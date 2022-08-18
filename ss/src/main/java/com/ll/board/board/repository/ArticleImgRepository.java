package com.ll.board.board.repository;

import com.ll.board.board.domain.article.ArticleComment;
import com.ll.board.board.domain.article.ArticleImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleImgRepository extends JpaRepository<ArticleImg, Long> {
}
