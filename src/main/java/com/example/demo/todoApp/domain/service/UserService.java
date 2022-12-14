package com.example.demo.todoApp.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.model.UserForm;
import com.example.demo.todoApp.domain.repository.jdbc.UserDaoJdbc;

@Service
public class UserService {
	// JDBCに紐づけ
	@Autowired
	UserDaoJdbc dao;

	public List<User> selectTodoListTrue() {
		return dao.selectTodoListTrue();
	}

	public List<User> selectTodoListFalse() {
		return dao.selectTodoListFalse();
	}

	public void deleteTodoList() {
		dao.deleteTodoList();
	}

	public void updateTodoDetailTrue(UserForm userForm) {
		dao.updateTodoDetailTrue(userForm);
	}

	public void updateTodoDetailFalse(UserForm userForm) {
		dao.updateTodoDetailFalse(userForm);
	}

	public List<User> selectTodoList(int id) {
		return dao.selectTodoList(id);
	}

	public void insertTodoDetailTrue(UserForm userForm) {
		dao.insertTodoDetailTrue(userForm);
	}

	public void insertTodoDetailFalse(UserForm userForm) {
		dao.insertTodoDetailFalse(userForm);
	}

	public boolean count(String title) {
		return dao.count(title);
	}
}