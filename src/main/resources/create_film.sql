DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
	`film_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`naam` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`minuten` INT(3) UNSIGNED NULL,
	`type` INT(1) UNSIGNED NULL,
	`taal` VARCHAR(50) NOT NULL COLLATE 'utf8_bin',
	PRIMARY KEY (`film_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `film`
  (`naam`, `minuten`, `type`, `taal`)
VALUES
  ('The Legend of Tarzan (2016)', '90', '3', 'Nederlands'),
  ('Tarzan the Ape Man (1932)', '90', '2', 'Engels'),
  ('Tarzan (1999)', '120', '2', 'Engels');

SELECT * FROM `film`;