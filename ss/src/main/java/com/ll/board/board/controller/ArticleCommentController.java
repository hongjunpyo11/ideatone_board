package com.ll.board.board.controller;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.form.ArticleCommentForm;
import com.ll.board.board.service.ArticleCommentService;
import com.ll.board.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/articleComment")
@Controller
@RequiredArgsConstructor
public class ArticleCommentController {
    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;

    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable long id, @Valid ArticleCommentForm articleCommentForm, BindingResult bindingResult) {
        Article article = this.articleService.getArticle(id);

        if ( bindingResult.hasErrors() ) {
            model.addAttribute("article", article);
            return "article_detail";
        }

        articleCommentService.create(article, articleCommentForm.getContent());

        return "redirect:/article/detail/%d".formatted(id);
    }
}
