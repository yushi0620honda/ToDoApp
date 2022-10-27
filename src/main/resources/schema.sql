CREATE TABLE IF NOT EXISTS todo_details (
	id INT IDENTITY(1,1) PRIMARY KEY,
	title VARCHAR (40) UNIQUE,
	is_done BOOLEAN,
	time_limit DATE
);