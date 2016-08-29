INSERT INTO chair (nummer) VALUES
	(1),
	(2),
	(3),
	(4);

INSERT INTO customer (first_name, last_name, username, password, email, customer_create_date, customer_create_time) VALUES
	('Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl', '2016-07-01', '21:00:00'),
	('Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl', '2016-07-01', '21:00:00'),
	('Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl', '2016-07-01', '21:00:00');

INSERT INTO employee (first_name, last_name, username, password, email, employee_create_date, employee_create_time) VALUES
	('Administrator', 'A', 'A', 'A', 'A@cerios.nl', '2016-07-01', '21:00:00');

INSERT INTO language (language_name) VALUES
	('English'),
	('Dutch'),
	('French'),
	('German');

INSERT INTO movie (category, title, minutes, movie_type, language, description, trailer) VALUES
	('ACTION', 'The Legend of Tarzan (2016)', 90, 3, 'Dutch', 'A story about a boy that survived in the jungle.', 'http://www.youtube.com/embed/hk8QXTuO9WM'),
	('ACTION', 'Tarzan the Ape Man (1932)', 90, 2, 'English', 'A story about a boy that survived in the jungle.', '#'),
	('KIDS', 'Tarzan (1999)', 120, 2, 'English', 'A story about a boy that survived in the jungle.', 'http://www.youtube.com/embed/lfciC33t3M0');

INSERT INTO payment (customer_id, show_id, room_id, chair_id, amount, payment_date, payment_time) VALUES
	(1, 2, 1, 1, 10, '2018-07-01', '21:00:00'),
	(2, 1, 2, 2, 10, '2018-07-01', '20:00:00'),
	(3, 1, 1, 1, 10, '2018-07-01', '22:00:00');

INSERT INTO room (room_name, chair_amount, room_type) VALUES
	('red room', 50, 2),
	('blue room', 20, 3);

INSERT INTO show_table (movie_id, room_id, show_date, show_time, chairs_sold) VALUES
	(1, 1, '2016-12-01', '20:00:00', 0),
	(2, 1, '2016-12-01', '21:00:00', 0),
	(2, 2, '2016-12-01', '22:00:00', 0),
	(3, 1, '2017-12-01', '21:00:00', 0);