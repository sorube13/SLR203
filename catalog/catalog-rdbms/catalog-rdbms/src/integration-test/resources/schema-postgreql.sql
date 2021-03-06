


-- Cleanup
DROP TABLE IF EXISTS T_COUNTER;
DROP TABLE IF EXISTS T_VERSION;
DROP TABLE IF EXISTS T_ORDER_LINE;
DROP TABLE IF EXISTS T_ORDER;
DROP TABLE IF EXISTS T_CUSTOMER;
DROP TABLE IF EXISTS T_ITEM;
DROP TABLE IF EXISTS T_PRODUCT;
DROP TABLE IF EXISTS T_CATEGORY;
DROP TABLE IF EXISTS T_ADDRESS;


DROP SEQUENCE IF EXISTS category_seq;
DROP SEQUENCE IF EXISTS product_seq;
DROP SEQUENCE IF EXISTS item_seq;

-- Create
CREATE TABLE T_CATEGORY ( id bigint NOT NULL PRIMARY KEY, name VARCHAR(50) NOT NULL, description VARCHAR(255) NOT NULL) ;
CREATE TABLE T_PRODUCT ( id bigint NOT NULL PRIMARY KEY, name VARCHAR(50) NOT NULL, description VARCHAR(255) NOT NULL, category_fk bigint NOT NULL, CONSTRAINT category_fk_const FOREIGN KEY (category_fk) REFERENCES t_category (id) ON DELETE CASCADE) ;
CREATE TABLE T_ITEM ( id bigint NOT NULL PRIMARY KEY, name VARCHAR(50) NOT NULL, unitCost numeric(16,2) NOT NULL, product_fk bigint NOT NULL,imagePath VARCHAR(255) DEFAULT NULL, CONSTRAINT product_fk_const FOREIGN KEY (product_fk) REFERENCES t_product (id) ON DELETE CASCADE) ;
CREATE TABLE T_ADDRESS (id bigint NOT NULL PRIMARY KEY, street1 VARCHAR(50), street2 VARCHAR(50), city VARCHAR(25), state VARCHAR(25), zipcode VARCHAR(10), country VARCHAR(25));
CREATE TABLE T_CUSTOMER (id bigint NOT NULL PRIMARY KEY, firstname VARCHAR(50) NOT NULL, lastname VARCHAR(50) NOT NULL, telephone VARCHAR(20), email VARCHAR(40) NOT NULL, password VARCHAR(50), creditcardnumber VARCHAR(25), creditcardtype VARCHAR(25), creditcardexpirydate VARCHAR(10), address_fk bigint);  

ALTER TABLE T_CUSTOMER ADD CONSTRAINT address_fk_const FOREIGN KEY (address_fk) REFERENCES T_ADDRESS(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE T_CUSTOMER ADD CONSTRAINT email_unique UNIQUE (email);

CREATE TABLE T_VERSION ( id bigint NOT NULL PRIMARY KEY, name VARCHAR(50) NOT NULL) ;



CREATE SEQUENCE category_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;


CREATE SEQUENCE product_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE item_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
