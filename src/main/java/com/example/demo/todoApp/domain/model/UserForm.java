package com.example.demo.todoApp.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserForm {

	// todo_detailsテーブル
	private int id;
	@Length(max = 40)
	@NotBlank
	private String title;
	@NotNull
	private boolean is_done;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate time_limit;
	private boolean check;
}