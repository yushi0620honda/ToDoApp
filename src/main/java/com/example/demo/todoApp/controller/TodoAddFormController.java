package com.example.demo.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.todoApp.domain.model.UserForm;
import com.example.demo.todoApp.domain.service.UserService;

@Controller
public class TodoAddFormController {

	@Autowired
	UserService userService;

	@GetMapping("/todoAddForm/{id}")
	public String getTodoAddForm(Model model, @PathVariable("id") int id, @ModelAttribute UserForm userForm) {
		return "html/todoAddForm";
	}

	@PostMapping("/todoAddForm/{id}")
	public String postTodoAddForm(Model model, @PathVariable("id") int id, @ModelAttribute @Validated UserForm userForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getTodoAddForm(model, id, userForm);
		}
		// チェックリスト 完了フラグ
		boolean check = userForm.isCheck();
		// dbに登録されている値と入力値
		String nowTitle = userService.getNowTitleById(id);
		String newTitle = userForm.getTitle();
		// チェックリストを押すとチェックリストはTrue、 完了リストはFalseで完了リストへ
		if (check == true) {
			// dbに登録されている値と入力値を比較し、Trueならエラーメッセージ、Falseなら更新
			if (newTitle.equals(nowTitle)) {
				model.addAttribute("message", "このタスクは既に登録されています");
				return getTodoAddForm(model, id, userForm);
			} else {
				userService.insertTodoDetailFalse(userForm);
			}
		} else {
			// dbに登録されている値と入力値を比較し、Trueならエラーメッセージ、Falseなら更新
			if (newTitle.equals(nowTitle)) {
				model.addAttribute("message", "このタスクは既に登録されています");
				return getTodoAddForm(model, id, userForm);
			} else {
				userService.insertTodoDetailTrue(userForm);
			}
		}
		return "html/completeTodoAddForm";
	}
}