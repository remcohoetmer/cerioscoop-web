INSERT INTO customer (first_name, last_name, username, password, email) VALUES
	('Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl'),
	('Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl'),
	('Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl');

INSERT INTO movie (title, description) VALUES
	('The Legend of Tarzan (2016)','A story about a boy that survived in the jungle.',
	('Tarzan the Ape Man (1932)', 'A story about a boy that survived in the jungle.'),
	('Tarzan (1999)', 'A story about a boy that survived in the jungle.'),
	('Wedding Crashers', 'A story about two guys that crach weddings.'),
	('Blood Diamond', 'A story about illegal trading of diamonds in Sierra Leone.'),
	('The Lion King', 'A story about a lion called Simba.'),
	('Snatch', 'A story about a diamond that all the pikeys want to have.');

INSERT INTO transaction (customer_id, show_id, bankaccount, total_price, reserved_places) VALUES
	(1, 2, 1234567890, 10, 5),
	(2, 1, 2234567890, 20, 2),
	(2, 2, 3234567890, 20, 3),
	(3, 1, 4234567890, 10, 1);

INSERT INTO room (room_name, capacity) VALUES
	('red room', 50),
	('blue room', 20);

INSERT INTO show_table (movie_id, room_id, show_date, show_time, available_places, show_price) VALUES
	(1, 1, '2016-12-01', '12:00:00', 50, 10),
	(2, 1, '2016-12-01', '21:00:00', 50, 20),
	(3, 2, '2016-12-01', '16:00:00', 20, 10),
	(4, 1, '2016-12-01', '20:00:00', 50, 20),
	(5, 1, '2016-12-01', '21:00:00', 50, 20),
	(6, 2, '2016-12-01', '10:00:00', 20, 10),
	(7, 1, '2017-12-01', '15:00:00', 50, 10);