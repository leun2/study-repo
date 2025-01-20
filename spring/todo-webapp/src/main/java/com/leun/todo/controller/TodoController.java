package com.leun.todo.controller;

import com.leun.todo.entity.Todo;
import com.leun.todo.service.TodoService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
@RequestMapping
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping("todo")
//    public String todo(ModelMap modelMap) {
//        List<Todo> todos = todoService.findByUser(todoService.getUserName());
//
//        modelMap.put("todos",todos);
//
//        return "todo";
//    }
//
//    @GetMapping("/add-todo")
//    public String getAddTodo(ModelMap modelMap) {
//        Todo todo = new Todo(0, todoService.getUserName(), "", LocalDate.now(), false);
//        modelMap.put("todo",todo);
//        return "add-todo";
//    }
//
//
//    @PostMapping("/add-todo")
//    public String postAddTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap modelMap) {
//        if (bindingResult.hasErrors()) {
//            modelMap.put("todo", todo); // 오류가 발생하면 폼 데이터를 다시 모델에 추가
//            return "add-todo"; // 포워딩하여 오류 메시지 표시
//        }
//
//        todoService.addTodo(todoService.getUserName(), todo.getTodo_description(), todo.getTodo_date());
//        return "redirect:/todo";
//    }
//
//    @GetMapping("/delete-todo")
//    public String deleteTodo(@RequestParam Integer id){
//
//        todoService.deleteById(id);
//        return "redirect:/todo";
//    }
//
//    @GetMapping("/update-todo")
//    public String getUpdateTodo(@RequestParam Integer id, ModelMap modelMap){
//        Todo todo = todoService.findById(id);
//        modelMap.addAttribute("todo", todo);
//
//        return "update-todo";
//    }
//
//    @PostMapping("/update-todo")
//    public String postUpdateTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap modelMap){
//
//        if (bindingResult.hasErrors()) {
//            modelMap.put("todo", todo);
//            return "update-todo";
//        }
//
//        String username = todoService.getUserName();
//        todo.setTodo_name(username);
//        todoService.updateTodo(todo);
//        return "redirect:/todo";
//    }

    @GetMapping("/todo")
    public String todo(ModelMap modelMap) {
        List<Todo> todos = todoService.findByName(todoService.getUserName());

        modelMap.put("todos",todos);

        return "todo";
    }

    @GetMapping("/add-todo")
    public String getAddTodo(ModelMap modelMap) {
        Todo todo = new Todo(0, todoService.getUserName(), "", LocalDate.now(), false);
        modelMap.put("todo",todo);
        return "add-todo";
    }


    @PostMapping("/add-todo")
    public String postAddTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.put("todo", todo); // 오류가 발생하면 폼 데이터를 다시 모델에 추가
            return "add-todo"; // 포워딩하여 오류 메시지 표시
        }

        todoService.addTodo(todoService.getUserName(), todo.getTodoDescription(), todo.getTodoDate());
        return "redirect:/todo";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam Integer id){

        todoService.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/update-todo")
    public String getUpdateTodo(@RequestParam Integer id, ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute("todo", todo);

        return "update-todo";
    }

    @PostMapping("/update-todo")
    public String postUpdateTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap modelMap){

        if (bindingResult.hasErrors()) {
            modelMap.put("todo", todo);
            return "update-todo";
        }

        String username = todoService.getUserName();
        todo.setTodoName(username);
        todoService.updateTodo(todo);
        return "redirect:/todo";
    }
}
