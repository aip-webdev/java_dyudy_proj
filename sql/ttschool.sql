DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`; 
USE `ttschool`;

CREATE TABLE school (
id INT(11) NOT NULL AUTO_INCREMENT,
school_name VARCHAR(50) NOT NULL,
year_study INT NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY school (school_name, year_study),
KEY/*INDEX*/ year_study (year_study)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE ‘group’ (
id INT(11) NOT NULL AUTO_INCREMENT,
group_name VARCHAR(50) NOT NULL,
room_name VARCHAR(50) NOT NULL,
schools_id INT(11) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY/*INDEX*/ group_name (group_name),
KEY/*INDEX*/ room_name (room_name),
CONSTRAINT schools_id FOREIGN KEY (schools_id) REFERENCES school (id) ON DELETE CASCADE 
) ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE trainee (
id INT(11) NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
rating INT(11) NOT NULL,
groups_id INT(11) DEFAULT NULL REFERENCES ‘group’(id),
 PRIMARY KEY (id),
  KEY/*INDEX*/ first_name (first_name),
  KEY/*INDEX*/ last_name (last_name),	
  KEY/*INDEX*/ rating (rating),
  CONSTRAINT groups_id FOREIGN KEY (groups_id) REFERENCES ‘group’ (id) ON DELETE SET null
) ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE ‘subject’ (
id INT(11) NOT NULL AUTO_INCREMENT,
subject_name VARCHAR(50) NOT NULL,
 PRIMARY KEY (id),
 UNIQUE KEY/*INDEX*/ subject_name (subject_name)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE group_subject (
id INT(11) NOT NULL AUTO_INCREMENT,
group_id INT(11) NOT NULL,
subject_id INT(11) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY/*INDEX*/ group_subject (group_id, subject_id),
KEY/*INDEX*/ subject_id (subject_id),
CONSTRAINT group_id FOREIGN KEY (group_id) REFERENCES ‘group’ (id) ON DELETE CASCADE  ,
CONSTRAINT subject_id FOREIGN KEY (subject_id) REFERENCES ‘subject’ (id) ON DELETE CASCADE  
)ENGINE=INNODB DEFAULT CHARSET=utf8;
