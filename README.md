# guestbook
Technology and Framework used

SpringMVC+Springgboot+JPA+Spring Securitry+bootstrap

Data base:- MySQL

Project Overview

Guest book application is for Registered Guest who can enter to application and log comment. 
Modules Available
Login Modules
Registration Module
View Entry details by Admin
Log Guest Entry via text or Image.

Coomand to build.
clean install or right click on project and select run as and then clean install .Then right click on Project select maven and then update project

Run Aplicataion.
1.go to UserBookingApplication class .Right click then Run as Java Aplication.
2.open browser and then hit http://localhost:8080/{Please check Port no in console}

Test Application
1.Create account through Application.
2.Then Login to Application and Add Entry.
3. User will be Added as Normal User .
4.Again Create account with Another Name and id. Now we can MAke Another user as Admin by updating role_id 3 to user_roles.Map ur user id to 3.Now id which is Mapped to 3 become Admin.
Admin have right to view All entry. He can Approve or Reject or Delete.

Table Details.
4 tables need to be Created for this Project.


CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `file` longblob,
  `comments` varchar(252) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

CREATE TABLE `user_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`),
  CONSTRAINT `FK7ecyobaa59vxkxckg6t355l86` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKdbv8tdyltxa1qjmfnj9oboxse` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
)


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

