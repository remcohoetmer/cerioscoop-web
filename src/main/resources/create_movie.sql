DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
	`movie_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`category_id` INT(20) UNSIGNED NOT NULL,
	`title` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`minutes` INT(3) UNSIGNED NULL,
	`movie_type` INT(1) UNSIGNED NULL,
	`language` VARCHAR(50) NOT NULL COLLATE 'utf8_bin',
	`description` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	PRIMARY KEY (`movie_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `movie`
  (`category_id`, `title`, `minutes`, `movie_type`, `language`, `description`)
VALUES
  ('1', 'The Legend of Tarzan (2016)', '90', '3', 'Dutch', 'A story about a boy that survived in the jungle.'),
  ('1', 'Tarzan the Ape Man (1932)', '90', '2', 'English', 'A story about a boy that survived in the jungle.'),
  ('3', 'Tarzan (1999)', '120', '2', 'English', 'A story about a boy that survived in the jungle.');

SELECT * FROM `movie`;