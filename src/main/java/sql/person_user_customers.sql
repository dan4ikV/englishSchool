CREATE TABLE customers (
  id         INT(11) UNSIGNED     AUTO_INCREMENT,
  firstName  VARCHAR(20),
  secondName VARCHAR(20),
  birthDate  DATE,
  login      VARCHAR(20) NOT NULL,
  password   VARCHAR(20) NOT NULL,
  status     VARCHAR(10) NOT NULL,
  rights     VARCHAR(10) NOT NULL,
  phone      VARCHAR(13),
  email      VARCHAR(50),
  adress     VARCHAR(100),
  created    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE INDEX (login)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;
EXPLAIN customers;

CREATE TABLE persons (
  id         INT(11) UNSIGNED   AUTO_INCREMENT,
  firstName  VARCHAR(20),
  secondName VARCHAR(20),
  birthDate  DATE,
  created    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

DROP TABLE webUsers;

CREATE TABLE webUsers (
  id       INT(11) UNSIGNED     AUTO_INCREMENT,
  login    VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  status   VARCHAR(10) NOT NULL,
  rights   VARCHAR(10) NOT NULL,
  created  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE INDEX (login)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

DROP TABLE customers;
CREATE TABLE customers (
  id      INT(11) UNSIGNED          AUTO_INCREMENT,
  pid     INT(11) UNSIGNED NOT NULL,
  wid     INT(11) UNSIGNED NOT NULL,
  uStatus VARCHAR(7)       NOT NULL,
  uRights VARCHAR(9)       NOT NULL,
  phone   VARCHAR(13),
  email   VARCHAR(50),
  adress  VARCHAR(100),
  created TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_customer_person FOREIGN KEY (pid) REFERENCES persons (id),
  CONSTRAINT fk_webUser_person FOREIGN KEY (wid) REFERENCES webUsers (id),
  CONSTRAINT fk_status_webUser FOREIGN KEY (uStatus) REFERENCES staus (userStatus),
  CONSTRAINT fk_rights_webUser FOREIGN KEY (uRights) REFERENCES rights (userRights)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

INSERT INTO persons (firstName, secondName, birthDate) VALUES ('danylo', 'wasylyshyn', '2002-08-01');
INSERT INTO webUsers (login, password, status, rights) VALUES ('dan4ik', 'danylodanylo', 'New', 'Default');
INSERT INTO customers (pid, wid, phone, email, adress)
  VALUE (
  (SELECT id
   FROM persons
   WHERE firstName = 'danylo'),
  (SELECT id
   FROM webUsers
   WHERE login = 'dan4ik'),
  '+380990227478',
  'danylo.wasylyshyn@gmail.com',
  'IF'
);

SELECT
  cm.id,
  pr.firstName,
  pr.secondName,
  wu.login,
  wu.password,
  cm.phone
FROM customers AS cm
  JOIN persons AS pr ON cm.pid = pr.id
  JOIN webUsers AS wu ON cm.wid = wu.id;

CREATE TABLE IF NOT EXISTS `servlab_danylo`.`customers` (
  `id`                  INT(11)          NOT NULL AUTO_INCREMENT,
  `phoneNumber`         VARCHAR(13)      NOT NULL,
  `email`               VARCHAR(50)      NOT NULL,
  `address`             VARCHAR(45)      NULL     DEFAULT NULL,
  `created`             TIMESTAMP        NULL     DEFAULT NULL,
  `updated`             TIMESTAMP        NULL     DEFAULT NULL,
  `webUsers_id`         INT(11) UNSIGNED NOT NULL,
  `webUsers_persons_id` INT(11) UNSIGNED NOT NULL,
  `status_id`           INT(11)          NOT NULL,
  `rights_id`           INT(11)          NOT NULL,
  PRIMARY KEY (`id`, `webUsers_id`, `webUsers_persons_id`, `status_id`, `rights_id`),
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_customers_webUsers1_idx` (`webUsers_id` ASC, `webUsers_persons_id` ASC),
  INDEX `fk_customers_status1_idx` (`status_id` ASC),
  INDEX `fk_customers_rights1_idx` (`rights_id` ASC),
  CONSTRAINT `fk_customers_webUsers1`
  FOREIGN KEY (`webUsers_id`, `webUsers_persons_id`)
  REFERENCES `servlab_danylo`.`webUsers` (`id`, `persons_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customers_status1`
  FOREIGN KEY (`status_id`)
  REFERENCES `servlab_danylo`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customers_rights1`
  FOREIGN KEY (`rights_id`)
  REFERENCES `servlab_danylo`.`rights` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


INSERT INTO rights (userRights) VALUES ('Admin'), ('Moderator'), ('Default');
INSERT INTO status (userStatus) VALUES ('New'), ('Active'), ('Blocked'), ('Banned');

INSERT INTO persons (firstName, secondName, birthDate) VALUES ('danylo', 'wasylyshyn', '2002-08-01');
INSERT INTO webUsers (login, password, status_id, rights_id, persons_id)
VALUES ('danylo', 'dan4ikdan4ik',
        (SELECT id
         FROM status
         WHERE userStatus = 'New'),
        (SELECT id
         FROM rights
         WHERE userRights = 'Admin'),
        (SELECT id FROM persons WHERE firstName = 'danylo'));

INSERT INTO customers(phoneNumber, email, address, webUsers_id)
VALUES('+380990227478', 'danylo.wasylyshyn@gmail.com' , 'IF',
       (SELECT id FROM webUsers WHERE webUsers.login = 'danylo'));

SELECT
  cm.id,
  pr.firstName,
  pr.secondName,
  wu.login,
  st.userStatus,
  rs.userRights,
  cm.email
FROM customers As cm
JOIN webUsers As wu ON wu.id = cm.webUsers_id
JOIN rights As rs ON rs.id = wu.rights_id
JOIN status As st ON st.id = wu.status_id
JOIN persons As pr ON  pr.id = wu.persons_id;

