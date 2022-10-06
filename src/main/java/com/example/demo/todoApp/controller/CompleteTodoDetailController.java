package com.example.demo.todoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompleteTodoDetailController {

	@GetMapping("/completeTodoDetail")
	public String getTodoList() {

		return "lhtml/todoList";
	}
}