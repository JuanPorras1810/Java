create database colegio;
use colegio;

create table ESTUDIANTE(
docEst varchar(11) NOT NULL,
nomEst varchar(30) NOT NULL,
apeEst varchar(30) NOT NULL,
dirEst varchar (60) NOT NULL,
telEst varchar(11) NOT NULL,
primary key(docEst)
);
insert into ESTUDIANTE(docEst,nomEst,apeEst,dirEst,telEst)
values('100000001','Carlos','Gomez','Calle 10 #15-20','3001234567'),
('100000002','María','Rodriguez','Carrera 8 #45-12','3012345678');

	create table ACUDIENTE(
	docAcu varchar(11) NOT NULL,
	nomAcu varchar(30) NOT NULL,
	apeAcu varchar(30) NOT NULL,
	dirAcu varchar(60) NOT NULL,
	telAcu varchar(11) NOT NULL,
	emaAcu varchar(30) NOT NULL,
	primary key(docAcu)
	);
insert into ACUDIENTE(docAcu,nomAcu,apeAcu,dirAcu,telAcu,emaAcu)
values('100000003','Jorge','Ramirez','Calle 12 #34-56','3101234567','jorge.ramirez@gmail.com'),
('100000004','Laura','Fernandez','Carrera 15 #67-89','3112345678','laura.fernandez@gmail.com');


create table PROFESOR(
	docPro varchar(11) NOT NULL,
	nomPro varchar(30) NOT NULL,
	apePro varchar(30) NOT NULL,
	dirPro varchar(60) NOT NULL,
	telPro varchar(11) NOT NULL,
	emaPro varchar(30) NOT NULL,
	titPro varchar (30) NOT NULL,
primary key(docPro)
);
insert into PROFESOR(docPro,nomPro,apePro,dirPro,telPro,emaPro,titPro)
values('100000005','Andres','Castro','Calle 25 #10-30','3201234567','andres.castro@email.com','Lic.Matematicas'),
('100000006','Lucia','Morales','Carrera 18 #40-55','3212345678','lucia.morales@email.com','Lic.Español');

create table MATERIA(
codMat int auto_increment,
nomMat varchar(30) NOT NULL,
graMat varchar(10) NOT NULL,
primary key(codMat)
);
insert into MATERIA(nomMat,graMat)
values('Matematicas','Primero'),
('Español','Segundo');

create table ACUDIENTEXESTUDIANTE(
consAcuxEst int auto_increment,
docAcuAcuxEst varchar(11) NOT NULL,
docEstAcuxEst varchar(11) NOT NULL,
primary key(consAcuxEst),
foreign key(docAcuAcuxEst) references ACUDIENTE(docAcu),
foreign key(docEstAcuxEst) references ESTUDIANTE(docEst)
);

insert into ACUDIENTEXESTUDIANTE(docAcuAcuxEst,docEstAcuxEst)
values('100000003','100000001'),
('100000004','100000002');

create table MATERIAXPROFESOR(
conMatxPro int auto_increment,
codMatMatxPro int NOT NULL,
docProfMatxPro varchar(11) NOT NULL,
graMatxPro varchar(10) NOT NULL,
primary key(conMatxPro),
foreign key(codMatMatxPro) references MATERIA(codMat),
foreign key(docProfMatxPro) references PROFESOR(docPro)
);

insert into MATERIAXPROFESOR(codMatMatxPro,docProfMatxPro,graMatxPro)
values (1,'100000005','Primero'),
(2,'100000006','Segundo');