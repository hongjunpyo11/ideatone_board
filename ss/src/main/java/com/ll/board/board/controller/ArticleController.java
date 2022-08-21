package com.ll.board.board.controller;

import com.ll.board.board.domain.article.Article;
import com.ll.board.board.form.ArticleCommentForm;
import com.ll.board.board.form.ArticleForm;
import com.ll.board.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/article")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Article> paging = articleService.getList(page);

        model.addAttribute("paging", paging);
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
//    @GetMapping("/test")
//    public @ResponseBody List<Article> bundle() {
//
//            return articleService.getList();
//    }

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

    @GetMapping("/modify/{id}")
    public String articleModify(ArticleForm articleForm, @PathVariable(name = "id") Long id) {

        Article article = this.articleService.getArticle(id);

        articleForm.setId(article.getId());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setUpdate(article.getUpdate());
        articleForm.setAreaName(article.getAreaName());

        return "articleUpdate_form";
    }

    @PostMapping("/modify")
    public String articleModify(Model model, @Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        System.out.println("articleForm.getId()  " + articleForm.getId());

        boolean result = articleService.modify(articleForm.getId(), articleForm.getTitle(), articleForm.getContent(), articleForm.getAreaName());

        if (result){
            return String.format("redirect:/article/detail/%s", articleForm.getId());
        } else {
            // 수정실패관련 페이지나 메시지 리턴
            return null; // 여기에 해당코드작성
        }


    }

    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable(name = "id") Long id) {
        boolean result = articleService.delete(id);
        if (result){
            return "redirect:/article/list";
        } else {
            // 삭제실패관련 페이지나 메시지 리턴
            return null;
        }
    }

}
