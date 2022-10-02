package com.example.demo.todoApp.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.todoApp.domain.model.User;

public interface UserDao {
	public List<User> selectTodoListTrue() throws DataAccessException;

	public List<User> selectTodoListFalse() throws DataAccessException;

	public void getTodoListDelete() throws DataAccessException;
}