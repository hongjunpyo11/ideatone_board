package com.ll.board.board.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime create;

    @Column(name = "update_at", nullable = false) //, nullable = false
    private LocalDateTime update;

    @Column(name = "title")
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "hit_count", columnDefinition = "Integer default 0")
    private Integer hitCount;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "delete_yn")
    @JsonIgnore
    private char deleteYn;

    @Column(name = "area_name")
    private String areaName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //    @Transient
    @OneToMany(mappedBy = "article")
    @JsonIgnoreProperties({"article", "commentBundle"})
    private List<ArticleComment> articleCommentList;

    public void addArticleComment(ArticleComment articleComment) {
        articleComment.setArticle(this);
        getArticleCommentList().add(articleComment);
    }


//    @OneToMany(mappedBy = "article")
//    @JsonIgnoreProperties({"article"})
//    private List<ArticleImg> articleImgList;
//
//    @OneToMany(mappedBy = "article")
//    @JsonIgnoreProperties({"article"})
//    private List<ArticleHashtag> articleHashtagList;
}
