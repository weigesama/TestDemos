CREATE DATABASE mybatis;
USE mybatis;

CREATE TABLE `user`(id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), age INT);
INSERT INTO `user`(id, NAME, age) VALUES(1, '孤傲苍狼', 27);
INSERT INTO `user`(id, NAME, age) VALUES(2, '白虎神皇', 27);
INSERT INTO `user`(id, NAME, age) VALUES(3, '李小龙', 27);
INSERT INTO `user`(id, NAME, age) VALUES(4, '李小龙', 27);

CREATE TABLE `order`(
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(20),
    order_price FLOAT
);
INSERT INTO `order`(order_id, order_no, order_price) VALUES(1, 'aaaa', 23);
INSERT INTO `order`(order_id, order_no, order_price) VALUES(2, 'bbbb', 33);
INSERT INTO `order`(order_id, order_no, order_price) VALUES(3, 'cccc', 22);


CREATE TABLE person(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20)
);

CREATE TABLE id_card(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20),
    person_id INT
);
ALTER TABLE id_card ADD CONSTRAINT fk_id_card_person_id FOREIGN KEY (person_id) REFERENCES person(id);

CREATE TABLE bank_card(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20),
    person_id INT
);
ALTER TABLE bank_card ADD CONSTRAINT fk_bank_person_id FOREIGN KEY (person_id) REFERENCES person(id);


INSERT INTO person(id, NAME) VALUES(1, 'person1');
INSERT INTO person(id, NAME) VALUES(2, 'person2');

INSERT INTO id_card(id, NAME, person_id) VALUES(3, 'id_card_3', 1);
INSERT INTO id_card(id, NAME, person_id) VALUES(4, 'id_card_4', 2);

INSERT INTO bank_card(id, NAME, person_id) VALUES(5, 'bank_card_5', 1);
INSERT INTO bank_card(id, NAME, person_id) VALUES(6, 'bank_card_6', 1);
INSERT INTO bank_card(id, NAME, person_id) VALUES(7, 'bank_card_7', 2);
INSERT INTO bank_card(id, NAME, person_id) VALUES(8, 'bank_card_8', 2);


CREATE TABLE blog(
    id INT PRIMARY KEY AUTO_INCREMENT,
    state VARCHAR(20),
    title VARCHAR(50),
    author_name VARCHAR(30),
    featured INT
);

INSERT INTO blog(id, state, title, author_name, featured) VALUES(1, 'ACTIVE', '博客1', '作者1', 1);
INSERT INTO blog(id, state, title, author_name, featured) VALUES(2, 'ACTIVE', '博客2', '作者2', 2);
INSERT INTO blog(id, state, title, author_name, featured) VALUES(3, 'INACTIVE', '博客3', '作者1', 3);