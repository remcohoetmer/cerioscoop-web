CREATE TABLE chair (
  chair_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  nummer INTEGER DEFAULT NULL,
  PRIMARY KEY (chair_id)
);

CREATE TABLE customer (
  customer_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  customer_create_date DATE NOT NULL,
  customer_create_time TIME NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE employee (
  employee_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  employee_create_date DATE NOT NULL,
  employee_create_time TIME NOT NULL,
  PRIMARY KEY (employee_id)
);

CREATE TABLE language (
  language_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  language_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (language_id)
);

CREATE TABLE movie (
  movie_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  category VARCHAR(50) NOT NULL,
  title VARCHAR(255) NOT NULL,
  minutes INTEGER DEFAULT NULL,
  movie_type INTEGER DEFAULT NULL,
  language VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL,
  trailer VARCHAR(50) NOT NULL,
  PRIMARY KEY (movie_id)
);

CREATE TABLE payment (
  payment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  customer_id INTEGER NOT NULL,
  show_id INTEGER NOT NULL,
  room_id INTEGER NOT NULL,
  chair_id INTEGER NOT NULL,
  amount double DEFAULT NULL,
  payment_date DATE NOT NULL,
  payment_time TIME NOT NULL,
  PRIMARY KEY (payment_id)
);

CREATE TABLE room (
  room_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  room_name VARCHAR(255) NOT NULL,
  chair_amount INTEGER DEFAULT NULL,
  room_type INTEGER DEFAULT NULL,
  PRIMARY KEY (room_id)
);

CREATE TABLE show (
  show_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  movie_id INTEGER NOT NULL,
  room_id INTEGER NOT NULL,
  show_date DATE NOT NULL,
  show_time TIME NOT NULL,
  PRIMARY KEY (show_id)
);

CREATE VIEW show_presentation AS 
	SELECT S.show_id, M.title, R.room_name, S.show_date, S.show_time, R.chair_amount, M.trailer
		FROM show S 
		JOIN movie M ON S.movie_id = M.movie_id
		JOIN room R ON R.room_id = S.room_id;
