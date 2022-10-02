package com.example.demo.todoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoListController {

	// @Autowired
	// UserService userService;

	@GetMapping("/todoList")
	public String getTodoList(Model model) {
//		List<User> todoList = userService.selectMany();
//		model.addAttribute("todoList", todoList);

		return "html/todoList";
	}

	@PostMapping("/todoList")
	public String postTodoList() {

		return "html/todoList";
	}
}