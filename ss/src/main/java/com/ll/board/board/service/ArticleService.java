package com.ll.board.board.service;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.domain.article.ArticleComment;
import com.ll.board.board.exception.DataNotFoundException;
import com.ll.board.board.repository.ArticleCommentRepository;
import com.ll.board.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getList() {

        return articleRepository.findAll();

        // 스트림
//        List<Article> articleList = articleRepository.findAll();
//
//        articleList.forEach(article ->
//                article.getArticleCommentList()
//                        .addAll(articleCommentRepository.findByArticleAndCommentBundle(article, null))
//        );

        // 여러번 쿼리
//        List<Article> articleList = articleRepository.findAll();
//        List<ArticleComment> articleCommentList = articleCommentRepository.findAll();
//
//
//
//        articleList.forEach(article ->
//                article.getArticleCommentList().addAll(articleCommentList.stream().filter(articleComment ->
//                        articleComment.getArticle().getId().equals(article.getId()) && articleComment.getCommentBundle() == null
//                ).toList())
//        ); // 첫번째 성능이 제곱으로 느려짐 댓글마다 필터링을 해야함
    }

    public Article getArticle(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d question not found,".formatted(id)));
    }

    public void create(String title, String content, String areaName) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreate(LocalDateTime.now());
        article.setUpdate(LocalDateTime.now());
        article.setAreaName(areaName); // 지역명은 회원가입 할 때 가져오는 것, 조회수는 생각 해보자
        articleRepository.save(article);
    }



}
