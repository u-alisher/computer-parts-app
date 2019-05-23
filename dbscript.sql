DROP DATABASE IF EXISTS test;
CREATE DATABASE test COLLATE utf8_bin;

USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part(
  id        INT(11)      NOT NULL AUTO_INCREMENT,
  name      VARCHAR(100) NOT NULL,
  needed  BIT(1)       NOT NULL DEFAULT FALSE,
  amount    INT(11)      NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

INSERT INTO part (name, needed, amount) VALUES
       ('cpu#1', true, 5),
       ('motherboard#1', false, 2),
       ('cpu#2', true, 10),
       ('RAM#1', false, 0),
       ('HDD#1', false, 10),
       ('case#1', true, 10),
       ('RAM#2', true, 10),
       ('SSD#1', true, 15),
       ('videocard#1', false, 7),
       ('motherboard#2', true, 5),
       ('soundcard#1', false, 9),
       ('cpu#3', true, 7),
       ('powerswitch#1', false, 14),
       ('HDD#2', false, 4),
       ('case#2', false, 2),
       ('RAM#3', true, 3),
       ('SSD#2', true, 8),
       ('videocard#2', false, 1),
       ('motherboard#3', true, 16),
       ('soundcard#2', false, 0),
       ('cpu#4', true, 23),
       ('lightingkit#1', false, 13),
       ('DVD#1', false, 7),
       ('case#3', true, 13),
       ('cooler#1', true, 5),
       ('powerswitch#2', true, 11),
       ('videocard#2', false, 4);