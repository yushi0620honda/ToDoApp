package com.example.demo.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.todoApp.domain.model.UserForm;
import com.example.demo.todoApp.domain.service.UserService;

@Controller
public class TodoAddFormController {

	@Autowired
	UserService userService;

	@GetMapping("/todoAddForm")
	public String getTodoAddForm(Model model, @ModelAttribute UserForm userForm) {
		return "html/todoAddForm";
	}

	@PostMapping("/todoAddForm")
	public String postTodoAddForm(Model model, @ModelAttribute @Validated UserForm userForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getTodoAddForm(model, userForm);
		}
		// チェックリスト 完了フラグ
		boolean check = userForm.isCheck();
		// チェックリストを押すとチェックリストはTrue、 完了リストはFalseで完了リストへ
		if (check == true) {
			userService.insertTodoDetailFalse(userForm);
		} else {
			userService.insertTodoDetailTrue(userForm);
		}
		return "html/completeTodoAddForm";
	}
}