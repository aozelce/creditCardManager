-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-03-08 10:44:33.339

-- Drop foreign key constraints first to avoid issues
ALTER TABLE IF EXISTS CreditCards DROP FOREIGN KEY IF EXISTS FK_0;
ALTER TABLE IF EXISTS CreditCards DROP FOREIGN KEY IF EXISTS FK_1;

-- Drop tables if they exist
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS CardIssuers;
DROP TABLE IF EXISTS Users;

-- tables
-- Table: CardIssuers
CREATE TABLE CardIssuers (
                             issuer_id int  NOT NULL AUTO_INCREMENT,
                             issuer_name varchar(255)  NOT NULL,
                             CONSTRAINT CardIssuers_pk PRIMARY KEY (issuer_id)
);

-- Table: CreditCards
CREATE TABLE CreditCards (
                             card_id int  NOT NULL AUTO_INCREMENT,
                             user_id int  NOT NULL,
                             issuer_id int  NOT NULL,
                             card_number varchar(255)  NOT NULL,
                             exp_date date  NOT NULL,
                             ccv varchar(255)  NOT NULL,
                             due_date date  NOT NULL,
                             credit_limit decimal(10,2)  NOT NULL,
                             current_balance decimal(10,2)  NOT NULL DEFAULT 0.00,
                             CONSTRAINT CreditCards_pk PRIMARY KEY (card_id)
);

-- Table: Users
CREATE TABLE Users (
                       user_id int  NOT NULL AUTO_INCREMENT,
                       username varchar(255)  NOT NULL,
                       email varchar(255)  NOT NULL,
                       external_auth_id varchar(255)  NULL,
                       first_name varchar(255)  NULL,
                       last_name varchar(255)  NULL,
                       UNIQUE INDEX AK_0 (username),
                       UNIQUE INDEX AK_1 (email),
                       CONSTRAINT Users_pk PRIMARY KEY (user_id)
);

-- foreign keys
-- Reference: FK_0 (table: CreditCards)
ALTER TABLE CreditCards ADD CONSTRAINT FK_0 FOREIGN KEY FK_0 (user_id)
    REFERENCES Users (user_id);

-- Reference: FK_1 (table: CreditCards)
ALTER TABLE CreditCards ADD CONSTRAINT FK_1 FOREIGN KEY FK_1 (issuer_id)
    REFERENCES CardIssuers (issuer_id);

-- End of file