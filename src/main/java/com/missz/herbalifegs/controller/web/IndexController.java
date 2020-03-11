package com.missz.herbalifegs.controller.web;

import com.missz.herbalifegs.entity.Blog;
import com.missz.herbalifegs.entity.Tag;
import com.missz.herbalifegs.entity.Type;
import com.missz.herbalifegs.service.BlogService;
import com.missz.herbalifegs.service.TagService;
import com.missz.herbalifegs.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        Page<Blog> blogs = blogService.listBlog(pageable);
        System.out.println(blogs+"------page------");

        model.addAttribute("types", typeService.listTypeTop(6));
        List<Type> types = typeService.listTypeTop(6);
        System.out.println(types+"--------types--------");
        model.addAttribute("tags", tagService.listTagTop(10));
        List<Tag> tags = tagService.listTagTop(10);
        System.out.println(tags+"-----tags--------");
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        System.out.println("------搜索--------");
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "web/blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

}
