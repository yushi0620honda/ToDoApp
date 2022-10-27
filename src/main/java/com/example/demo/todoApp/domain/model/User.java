package com.example.demo.todoApp.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	// todo_detailsテーブル
	private int id;
	private String title;
	private boolean is_done;
	private LocalDate time_limit;

}