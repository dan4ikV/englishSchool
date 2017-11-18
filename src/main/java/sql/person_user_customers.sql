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
  phone   VARCHAR(13),
  email   VARCHAR(50),
  adress  VARCHAR(100),
  created TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_customer_person FOREIGN KEY (pid) REFERENCES persons (id),
  CONSTRAINT fk_webUser_person FOREIGN KEY (wid) REFERENCES webUsers (id)
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