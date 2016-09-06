CREATE TABLE customer (
  customer_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE movie (
  movie_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (movie_id)
);

CREATE TABLE transaction (
  transaction_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  customer_id INTEGER NOT NULL,
  show_id INTEGER NOT NULL,
  bankaccount varchar(34) NOT NULL,
  reserved_places INTEGER NOT NULL,
  total_prices FLOAT DEFAULT NULL,
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
  FOREIGN KEY (show_id) REFERENCES show_table (show_id)
);

CREATE TABLE room (
  room_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  room_name VARCHAR(255) NOT NULL,
  capacity INTEGER DEFAULT NULL,
  PRIMARY KEY (room_id)
);

CREATE TABLE show_table (
  show_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  movie_id INTEGER NOT NULL,
  room_id INTEGER NOT NULL,
  show_date DATE NOT NULL,
  show_time TIME NOT NULL,
  available_places INTEGER NOT NULL,
  show_price FLOAT NOT NULL,
  PRIMARY KEY (show_id),
  FOREIGN KEY (movie_id) REFERENCES movie (movie_id),
  FOREIGN KEY (room_id) REFERENCES room (room_id)
);

CREATE VIEW show_presentation AS
	SELECT S.show_id, M.movie_title, R.room_name, S.show_date, S.show_time
		FROM show_table S 
		JOIN movie M ON S.movie_id = M.movie_id