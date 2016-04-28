DROP TABLE IF EXISTS `zaal`;

CREATE TABLE `zaal` (
	`zaal_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`naam` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`aantal_stoelen` INT(50) UNSIGNED NULL,
	`type` INT(1) UNSIGNED NULL,
	PRIMARY KEY (`zaal_id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `zaal`
  (`naam`, `aantal_stoelen`, `type`)
VALUES
  ('rode zaal', '50', '2'),
  ('blauwe zaal', '20','3');

SELECT * FROM `zaal`;