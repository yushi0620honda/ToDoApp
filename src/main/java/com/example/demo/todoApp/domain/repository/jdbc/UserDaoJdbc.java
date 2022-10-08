package com.example.demo.todoApp.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.todoApp.domain.model.User;
import com.example.demo.todoApp.domain.model.UserForm;
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
	public void getTodoListDelete() throws DataAccessException {
		jdbc.update("DELETE FROM todo_details WHERE is_done = false");
	}

	@Override
	public void updateTodoDetailTrue(UserForm userForm) throws DataAccessException {
		jdbc.update("UPDATE todo_details SET title = ?, time_limit = ?, is_done = ? WHERE id = ?", userForm.getTitle(),
				userForm.getTime_limit(), true, userForm.getId());
	}

	@Override
	public void updateTodoDetailFalse(UserForm userForm) throws DataAccessException {
		jdbc.update("UPDATE todo_details SET title = ?, time_limit = ?, is_done = ? WHERE id = ?", userForm.getTitle(),
				userForm.getTime_limit(), false, userForm.getId());
	}

	@Override
	public List<User> selectTodoList(int id) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM todo_details WHERE id = ?", id);
		List<User> todoList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			User user = convert(map);
			todoList.add(user);
		}
		return todoList;
	}

	@Override
	public String getNowTitleById(int id) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT title FROM todo_details WHERE id = ?", id);
		String nowTitle = (String) map.get("title");
		return nowTitle;
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