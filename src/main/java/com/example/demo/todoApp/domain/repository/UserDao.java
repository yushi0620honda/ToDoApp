package com.example.demo.todoApp.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.model.UserForm;

public interface UserDao {
	public List<User> selectTodoListTrue() throws DataAccessException;

	public List<User> selectTodoListFalse() throws DataAccessException;

	public void getTodoListDelete() throws DataAccessException;

	public void updateTodoDetailTrue(UserForm userForm) throws DataAccessException;

	public void updateTodoDetailFalse(UserForm userForm) throws DataAccessException;

	public List<User> selectTodoList(int id) throws DataAccessException;
}