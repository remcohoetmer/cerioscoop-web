-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server versie:                10.1.14-MariaDB - Source distribution
-- Server OS:                    Win32
-- HeidiSQL Versie:              9.3.0.5104
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

USE `cerioscoop_db`;

-- Dumpen data van tabel cerioscoop.category: ~4 rows (ongeveer)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`, `name`) VALUES
	(1, 'Action'),
	(2, 'Comedy'),
	(3, 'Kids'),
	(4, 'Horror');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.chair: ~4 rows (ongeveer)
DELETE FROM `chair`;
/*!40000 ALTER TABLE `chair` DISABLE KEYS */;
INSERT INTO `chair` (`chair_id`, `nummer`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4);
/*!40000 ALTER TABLE `chair` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.customer: ~3 rows (ongeveer)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `username`, `password`, `e-mail`, `customer_create_date`, `customer_create_time`) VALUES
	(1, 'Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl', '2016-07-01', '21:00:00'),
	(2, 'Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl', '2016-07-01', '21:00:00'),
	(3, 'Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl', '2016-07-01', '21:00:00');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.employee: ~3 rows (ongeveer)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_id`, `first_name`, `last_name`, `username`, `password`, `e-mail`, `employee_create_date`, `employee_create_time`) VALUES
	(1, 'Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl', '2016-07-01', '21:00:00'),
	(2, 'Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl', '2016-07-01', '21:00:00'),
	(3, 'Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl', '2016-07-01', '21:00:00');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.language: ~4 rows (ongeveer)
DELETE FROM `language`;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` (`language_id`, `name`) VALUES
	(1, 'English'),
	(2, 'Dutch'),
	(3, 'French'),
	(4, 'German');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.movie: ~3 rows (ongeveer)
DELETE FROM `movie`;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` (`movie_id`, `category_id`, `title`, `minutes`, `movie_type`, `language`, `description`) VALUES
	(1, 1, 'The Legend of Tarzan (2016)', 90, 3, 'Dutch', 'A story about a boy that survived in the jungle.'),
	(2, 1, 'Tarzan the Ape Man (1932)', 90, 2, 'English', 'A story about a boy that survived in the jungle.'),
	(3, 3, 'Tarzan (1999)', 120, 2, 'English', 'A story about a boy that survived in the jungle.');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.payment: ~3 rows (ongeveer)
DELETE FROM `payment`;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`payment_id`, `customer_id`, `show_id`, `room_id`, `chair_id`, `amount`, `payment_date`, `payment_time`) VALUES
	(1, 1, 2, 1, 1, 10, '2016-07-01', '21:00:00'),
	(2, 2, 1, 2, 2, 10, '2016-07-01', '21:00:00'),
	(3, 3, 1, 1, 1, 10, '2016-07-01', '21:00:00');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.room: ~2 rows (ongeveer)
DELETE FROM `room`;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` (`room_id`, `name`, `chair_amount`, `room_type`) VALUES
	(1, 'red room', 50, 2),
	(2, 'blue room', 20, 3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.show: ~4 rows (ongeveer)
DELETE FROM `show`;
/*!40000 ALTER TABLE `show` DISABLE KEYS */;
INSERT INTO `show` (`show_id`, `movie_id`, `room_id`, `show_date`, `show_time`) VALUES
	(1, 1, 1, '2016-07-01', '21:00:00'),
	(2, 2, 1, '2016-07-01', '21:00:00'),
	(3, 2, 2, '2016-07-01', '21:00:00'),
	(4, 3, 1, '2016-07-01', '21:00:00');
/*!40000 ALTER TABLE `show` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
