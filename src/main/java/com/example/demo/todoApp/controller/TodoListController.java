package com.example.demo.todoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.service.UserService;

@Controller
public class TodoListController {
	@Autowired
	UserService userService;

	@GetMapping("/todoList")
	public String getTodoList(Model model) {
		// 完了リスト
		List<User> todoListTrue = userService.selectTodoListTrue();
		model.addAttribute("todoListTrue", todoListTrue);
		// 未完了リスト
		List<User> todoListFalse = userService.selectTodoListFalse();
		model.addAttribute("todoListFalse", todoListFalse);
		return "html/todoList";
	}

	@PostMapping("/todoList")
	public String postTodoList(Model model) {
		userService.deleteTodoList();
		return getTodoList(model);
	}
}