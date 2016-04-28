DROP TABLE IF EXISTS `voorstellingen`;

CREATE TABLE `voorstellingen` (
	`voorstellingen_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`film_id` INT(20) UNSIGNED NOT NULL,
	`zaal_id` INT(20) UNSIGNED NOT NULL,
	`premiere_datum` DATE NOT NULL,
	`premiere_tijd` DATE NOT NULL,
	`laatste_voorstelling_datum` DATE NOT NULL,
	`laatste_voorstelling_tijd` TIME NOT NULL,
	PRIMARY KEY (`voorstellingen_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `voorstellingen`
  (`film_id`, `zaal_id`, `premiere_datum`, `premiere_tijd`, `laatste_voorstelling_datum`, `laatste_voorstelling_tijd`)
VALUES
  ('1', '1', '2016-07-01', '2016-07-01', '2016-07-01', '20:00:00'),
  ('2', '1', '2016-07-01', '2016-07-01', '2016-07-01', '20:00:00'),
  ('2', '2', '2016-07-01', '2016-07-01', '2016-07-01', '20:00:00'),
  ('3', '1', '2016-07-01', '2016-07-01', '2016-07-01', '20:00:00');
  
SELECT * FROM `voorstellingen`;