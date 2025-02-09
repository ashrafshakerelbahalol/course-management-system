

use training_example;

create table Trainee(
trainee_id bigint Primary Key,
first_name varchar(15) not null ,
last_name varchar(15)not null,
email  varchar(50),
department varchar(15),
role varchar(15)
);
create table Trainer(
trainer_id bigint Primary Key,
first_name varchar(15) not null ,
last_name varchar(15)not null,
email  varchar(50),
expertise varchar(30)
);

create table Course(
course_id bigint Primary Key,
course_name varchar(30) not null ,
description varchar(150),
duration timestamp
);

 create table Training_Session(
session_id bigint Primary Key,
course_id bigint not null,
trainer_id bigint not null, 
start_date date,
end_date date,
venue varchar(30),
foreign key(course_id) references Course(course_id) on delete cascade on update cascade,
foreign key(trainer_id) references Trainer(trainer_id) on delete cascade on update cascade
 );
 create table Enrollment(
enrollment_id bigint Primary Key ,
trainee_id bigint not null,
session_id bigint not null,
foreign key(trainee_id) references Trainee(trainee_id) on delete cascade on update cascade,
foreign key(session_id ) references Training_Session(session_id)  on delete cascade on update cascade
);

 create table Assessment(
assessment_id  bigint Primary Key ,
enrollment_id bigint not null,
score integer,
feedback varchar(200),
foreign key(enrollment_id ) references Enrollment(enrollment_id ) on delete cascade on update cascade
 );
 