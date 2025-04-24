

use training_example;

create table trainee(
trainee_id bigint Primary Key AUTO_INCREMENT,
first_name varchar(15) not null ,
last_name varchar(15)not null,
email  varchar(50),
department varchar(15),
role varchar(15)
);
create table trainer(
trainer_id bigint Primary Key,
first_name varchar(15) not null ,
last_name varchar(15)not null,
email  varchar(50),
expertise varchar(30)
);

create table course(
course_id bigint Primary Key,
course_name varchar(30) not null ,
description varchar(150),
duration timestamp
);

 create table training_Session(
session_id bigint Primary Key AUTO_INCREMENT,
course_id bigint not null,
trainer_id bigint not null, 
start_date date,
end_date date,
venue varchar(30),
foreign key(course_id) references course(course_id),
foreign key(trainer_id) references trainer(trainer_id)
 );
 create table enrollment(
enrollment_id bigint Primary Key AUTO_INCREMENT,
trainee_id bigint not null,
session_id bigint not null,
foreign key(trainee_id) references trainee(trainee_id) ,
foreign key(session_id ) references training_Session(session_id)
);
