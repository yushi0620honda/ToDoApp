CREATE TABLE IF NOT EXISTS todo_details (
	id INTEGER PRIMARY KEY,
	title VARCHAR(40),
	is_done BOOLEAN,
	time_limit DATE
);