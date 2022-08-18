package com.ll.board.board.repository;

import com.ll.board.board.domain.article.ArticleImg;
import com.ll.board.board.domain.article.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
