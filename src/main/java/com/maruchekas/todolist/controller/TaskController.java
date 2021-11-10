package com.maruchekas.todolist.controller;

import com.maruchekas.todolist.model.Task;
import com.maruchekas.todolist.repository.TaskRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/todolist")
  public String todolist(Model model){
    Iterable<Task> tasks = taskRepository.findAll();
    model.addAttribute("tasks", tasks);
    return "todolist";
  }

  @GetMapping("/todolist/add")
  public String addForm(Model model){
    return "add-task";
  }

  @PostMapping("/todolist/add")
  public String addTask(@RequestParam String title,
      @RequestParam String text, @RequestParam String endDate, Model model)
      throws ParseException {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Task task = new Task();
    task.setTitle(title);
    task.setText(text);
    task.setEndDate(format.parse(endDate));
    task.setCreatedDateTime(new Date());
    taskRepository.save(task);
    return "redirect:/todolist";
  }

  @GetMapping("/task/{id}")
  public String taskDetails(@PathVariable(value = "id") int id, Model model){
    if(!taskRepository.existsById(id)){
      return "redirect:/todolist";
    }
    Optional<Task> optionalTask = taskRepository.findById(id);
    Task task = optionalTask.get();
    model.addAttribute("task", task);
    return "details";
  }

  @GetMapping("/task/{id}/edit")
  public String taskEdit(@PathVariable(value = "id") int id, Model model){
    if(!taskRepository.existsById(id)){
      return "redirect:/todolist";
    }
    Optional<Task> optionalTask = taskRepository.findById(id);
    Task task = optionalTask.get();
    model.addAttribute("task", task);
    return "task-edit";
  }

  @PostMapping("/task/{id}/edit")
  public String taskUpdate(@PathVariable(value = "id") int id, @RequestParam String title,
      @RequestParam String text, @RequestParam String endDate, Model model)
      throws ParseException {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Task task = taskRepository.findById(id).orElseThrow();
    task.setTitle(title);
    task.setText(text);
    task.setEndDate(format.parse(endDate));
    task.setCreatedDateTime(new Date());
    taskRepository.save(task);
    return "redirect:/todolist";
  }

  @PostMapping("/task/{id}/remove")
  public String taskRemove(@PathVariable(value = "id") int id, Model model) {
    Task task = taskRepository.findById(id).orElseThrow();
    taskRepository.delete(task);
    return "redirect:/todolist";
  }

}
