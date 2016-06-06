DROP TABLE IF EXISTS `motionpicture`;

CREATE TABLE `motionpicture` (
	`motionpicture_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`film_id` INT(20) UNSIGNED NOT NULL,
	`room_id` INT(20) UNSIGNED NOT NULL,
	`premiere_date` DATE NOT NULL,
	`premiere_time` TIME NOT NULL,
	`last_motionpicture_date` DATE NOT NULL,
	`last_motionpicture_time` TIME NOT NULL,
	PRIMARY KEY (`motionpicture_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `motionpicture`
  (`film_id`, `room_id`, `premiere_date`, `premiere_time`, `last_motionpicture_date`, `last_motionpicture_time`)
VALUES
  ('1', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('2', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('2', '2', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('3', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00');
  
SELECT * FROM `motionpicture`;