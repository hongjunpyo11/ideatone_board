package com.ll.board.board.controller;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.form.ArticleCommentForm;
import com.ll.board.board.form.ArticleForm;
import com.ll.board.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/article")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable long id, ArticleCommentForm articleCommentForm) {
        Article article = articleService.getArticle(id);

        model.addAttribute("article", article);

        return "article_detail";
    }


//    @GetMapping("/")
//    public String root() {
//        return "redirect:/article/list";
//    }

    //테스트용
    @GetMapping("/test")
    public @ResponseBody List<Article> bundle() {

            return articleService.getList();
    }

    @GetMapping("/create")
    public String articleCreate(ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreate(Model model, @Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }

        articleService.create(articleForm.getTitle(), articleForm.getContent(), articleForm.getAreaName());
        return "redirect:/article/list"; // 질문 저장 후 질문 목록으로 이동
    }

}
