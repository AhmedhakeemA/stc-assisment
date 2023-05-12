create database stc ;
use stc;

CREATE TABLE User (
  user_id INT PRIMARY KEY,
  username VARCHAR(255) not null
);

insert into User(user_id,username) values (1,'John Doe');
insert into User(user_id,username) values (2,'Jane Don');
insert into User(user_id,username) values (3,'Alice Jones');
insert into User(user_id,username) values (4,'Lisa Romero');

CREATE TABLE Training_details (
  user_training_id INT PRIMARY KEY,
   user_id INT,
  training_id INT ,
  training_date DATE,
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);

insert into Training_details(user_training_id,user_id,training_id,training_date) values (1,1,1,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (2,2,1,'2015-08-03');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (3,3,2,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (4,4,2,'2015-08-04');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (5,2,2,'2015-08-03');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (6,1,1,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (7,3,2,'2015-08-04');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (8,4,3,'2015-08-03');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (9,1,4,'2015-08-03');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (10,3,1,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (11,4,2,'2015-08-04');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (12,3,2,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (13,1,1,'2015-08-02');
insert into Training_details(user_training_id,user_id,training_id,training_date) values (14,4,3,'2015-08-03');






