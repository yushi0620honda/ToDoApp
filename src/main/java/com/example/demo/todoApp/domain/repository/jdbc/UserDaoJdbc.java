package com.example.demo.todoApp.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.repository.UserDao;

@Repository
public class UserDaoJdbc implements UserDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<User> selectTodoListTrue() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM todo_details WHERE is_done = true");
		List<User> todoList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			User user = convert(map);
			todoList.add(user);
		}
		return todoList;
	}

	@Override
	public List<User> selectTodoListFalse() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM todo_details WHERE is_done = false");
		List<User> todoList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			User user = convert(map);
			todoList.add(user);
		}
		return todoList;
	}

	@Override
	public void deleteTodoList() throws DataAccessException {
		jdbc.update("DELETE FROM todo_details WHERE is_done = true");
	}

	private User convert(Map<String, Object> map) {
		User user = new User();
		user.setId((Integer) map.get("id"));
		user.setTitle((String) map.get("title"));
		user.set_done((boolean) map.get("is_done"));
		user.setTime_limit(((java.sql.Date) map.get("time_limit")).toLocalDate());
		return user;
	}
}