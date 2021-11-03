package com.maruchekas.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  public String index(Model model){
    model.addAttribute("title", "Список дел");
    return "index";
  }

  @GetMapping("/about")
  public String about(Model model){
    model.addAttribute("title", "О нас");
    return "about";
  }

}
