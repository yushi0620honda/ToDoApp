package com.example.demo.todoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.model.UserForm;
import com.example.demo.todoApp.domain.service.UserService;

@Controller
public class TodoDetailController {

	@Autowired
	UserService userService;

	@GetMapping("/todoDetail/{id}")
	public String getTodoDetail(Model model, @PathVariable("id") int id, @ModelAttribute UserForm userForm) {
		List<User> todoList = userService.selectTodoList(id);
		model.addAttribute("todoList", todoList);
		return "html/todoDetail";
	}

	@PostMapping("/todoDetail/{id}")
	public String postTodoDetail(Model model, @PathVariable("id") int id, @ModelAttribute @Validated UserForm userForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getTodoDetail(model, id, userForm);
		}
		userForm.setId(id);
		// チェックリスト 完了フラグ
		boolean check = userForm.isCheck();
		// チェックリストを押すとチェックリストはTrue、 完了リストはFalseで完了リストへ
		if (check == true) {
			userService.updateTodoDetailFalse(userForm);
		} else {
			userService.updateTodoDetailTrue(userForm);
		}
		return "html/CompleteTodoDetail";
	}
}