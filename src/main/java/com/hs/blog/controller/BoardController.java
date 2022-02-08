package com.hs.blog.controller;

import com.hs.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("boards", boardService.글목록(pageable));
        return "index"; // viewResolver 작동(RestController가 아니라 Controller이기 때문) 이때 model의 정보를 들고 이동
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    @GetMapping({"/board/saveForm"})
    public String saveForm(){
        return "board/saveForm";
    }
}
