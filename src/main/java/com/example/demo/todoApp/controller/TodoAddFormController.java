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
		// countの結果
		boolean titleCount = userService.count(userForm.getTitle());
		// dbに登録されている値と入力値を比較し、Trueならエラーメッセージ、Falseなら更新
		if (titleCount == false) {
			// チェックボックスを押す（True）と完了（True）、
			// チェックボックスを押さない（False）と未完了リスト(False)
			if (check == false) {
				userService.insertTodoDetailFalse(userForm);
			} else {
				userService.insertTodoDetailTrue(userForm);
			}
		} else {
			model.addAttribute("message", "このタスクは既に登録されています");
			return getTodoAddForm(model, userForm);
		}
		return "redirect:/todoList";
	}
}