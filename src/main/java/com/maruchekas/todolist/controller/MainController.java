package com.maruchekas.todolist.controller;

import com.maruchekas.todolist.model.Task;
import com.maruchekas.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/")
  public String index(Model model){
    model.addAttribute("title", "Список дел");
    Iterable<Task> tasks = taskRepository.findAll();
    model.addAttribute("tasks", tasks);
    return "index";
  }

  @GetMapping("/about")
  public String about(Model model){
    model.addAttribute("title", "О нас");
    return "about";
  }

}
