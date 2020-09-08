package com.pika.blogbackend.web.admin;

import com.pika.blogbackend.po.Type;
import com.pika.blogbackend.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 4, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";

    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            attributes.addFlashAttribute("message", "新增失败, 不能重复");
        } else {
            Type t = typeService.saveType(type);
            if (t == null) {
                attributes.addFlashAttribute("message", "新增失败");
            } else {
                attributes.addFlashAttribute("message", "新增成功");
            }
        }

        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type, @PathVariable Long id, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            attributes.addFlashAttribute("message", "修改失败, 不能重复");
        } else {
            Type t = typeService.updateType(id, type);
            if (t == null) {
                attributes.addFlashAttribute("message", "修改失败");
            } else {
                attributes.addFlashAttribute("message", "修改成功");
            }
        }

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
