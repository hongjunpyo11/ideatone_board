package com.ll.board.board.domain;


import com.ll.board.board.domain.article.*;
import com.ll.board.board.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
class BoardTest {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleImgRepository articleImgRepository;
    @Autowired
    ArticleHashtagRepository articleHashtagRepository;
    @Autowired
    ArticleCommentRepository articleCommentRepository;
    @Autowired
    ArticleToHashtagRepository articleToHashtagRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public  void toTest () throws Exception{

        User user1 = userRepository.save(User.builder().id(1L).username("user1").build());
        Article article = articleRepository.save(Article.builder().id(1L).content("testContent").title("testTitle").update(LocalDateTime.now()).hitCount(1).deleteYn('n').create(LocalDateTime.now()).build());
        ArticleImg test = articleImgRepository.save(ArticleImg.builder().article(article).id(1L).build());
        ArticleComment comment1 = articleCommentRepository.save(ArticleComment.builder().id(1L).user(user1).article(article).commentBundle(null).build());
        ArticleComment comment2 = articleCommentRepository.save(ArticleComment.builder().id(2L).user(user1).article(null).commentBundle(comment1).build());
        ArticleHashtag testHashtag1 = articleHashtagRepository.save(ArticleHashtag.builder().id(1L).hashtagName("testHashtag").build());
        ArticleHashtag testHashtag2 = articleHashtagRepository.save(ArticleHashtag.builder().id(2L).hashtagName("testHashtag").build());
        ArticleToHashtag articleToHashtag1 = articleToHashtagRepository.save(ArticleToHashtag.builder().id(1L).hashtag(testHashtag1).article(article).build());
        ArticleToHashtag articleToHashtag2 = articleToHashtagRepository.save(ArticleToHashtag.builder().id(2L).hashtag(testHashtag2).article(article).build());
    }

    @Test
    void createManySampleDate() {
        boolean run = false;

        if (run == false) return;

        IntStream.rangeClosed(1, 300).forEach(id -> {
            Article a = new Article();
            a.setTitle("%d번 게시글".formatted(id));
            a.setContent("%d번 질문의 내용".formatted(id));
            a.setCreate(LocalDateTime.now());
            a.setUpdate(LocalDateTime.now());
            articleRepository.save(a);
        });
    }

}