package com.example.demo.todoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompleteTodoAddFormController {

	@GetMapping("/completeTodoAddForm")
	public String getTodoList() {

		return "html/todoList";
	}
}