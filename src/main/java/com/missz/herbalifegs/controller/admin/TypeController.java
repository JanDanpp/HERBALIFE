package com.missz.herbalifegs.controller.admin;

import com.missz.herbalifegs.entity.Type;
import com.missz.herbalifegs.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    private String input(Model model){
        model.addAttribute("type",new Type());
        System.out.println("--------types/input------");
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typename = typeService.getTypename(type.getName());
        if (typename != null){
            result.rejectValue("name","nameError","该名称重复不能添加重复的分类");
        }
        //获取到字段值提示到前端
        if (result.hasErrors()){
            return "admin/types-input";
        }
        System.out.println(type);
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
            System.out.println("-----types数据插入成功-----");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editpost(@Valid Type type, BindingResult result,@PathVariable("id") Long id, RedirectAttributes attributes){
        Type typename = typeService.getTypename(type.getName());
        if (typename != null){
            result.rejectValue("name","nameError","该名称重复不能添加重复的分类");
        }
        //获取到字段值提示到前端
        if (result.hasErrors()){
            return "admin/types-input";
        }
        System.out.println(type);
        Type t = typeService.updateType(id,type);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
            System.out.println("-----types数据更新成功-----");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


}
