create database proyecto;
use proyecto;

create table usuario(
idUsu varchar(11) NOT NULL,
tipDocUsu varchar(35) NOT NULL,
nomUsu varchar(60) NOT NULL,
emaUsu varchar(100) NOT NULL,
dirUsu varchar(60) NOT NULL,
telUsu varchar(10) NOT NULL,
telAltUsu varchar(10),
rolUsu  ENUM('Supervisor','Agente') NOT NULL,
conUsu varchar(60) NOT NULL,
primary key(idUsu)
);
insert into usuario(idUsu,tipDocUsu,nomUsu,emaUsu,dirUsu,telUsu,telAltUsu,rolUsu,conUsu)
values('1102458796','Cedula de ciudadania','Juan Pablo Torres','torres@gmail.com','Calle 34 #18-90',3007894561,NULL,'Agente','torres123'),
('1025487963','Cedula de ciudadania','Maria Fernanda Ruiz','maria@gmail.com','Carrera 10 #22-15',3125478963,6014587932,'Supervisor','maria123');
select * from usuario;

create table cliente(
idCli varchar(11) NOT NULL,
tipDoCli varchar(35) NOT NULL,
nomCli varchar(60) NOT NULL,
emaCli varchar(100) NOT NULL,
dirCli varchar(60) NOT NULL,
telCli varchar(10) NOT NULL,
telAltCli varchar(10),
obsCli varchar (300) NOT NULL,
primary key(idCli)
);
insert into cliente(idCli,tipDoCli,nomCli,emaCli,dirCli,telCli,telAltCli,obsCli) 
values('11262365','Cedula de ciudadania','Juan Sebastian Porras','juan@gmail.com','calle 12 #54-43',3234212231,3642785423,'Cliente nuevo'),
('1024587963','Cedula de ciudadania','Carlos Andres Peña','carlos@gmail.com','calle 23 #32-12',3118745698,3224587963,'Cliente nuevo');
select * from cliente;

create table campana(
codCam int auto_increment,
fecIniCam date NOT NULL,
fecFinCam date NOT NULL,
defObjCam varchar(1000) NOT NULL,
primary key(codCam)
);
insert into campana(fecIniCam,fecFinCam,defObjCam)
values('2025-11-05','2025-12-10','Campaña de actualizacion de datos'),
('2025-11-15','2026-01-30','Campaña de encuestas de satisfaccion');
select * from campana;

create table registroUsuario(
codRegUsu int auto_increment,
idUsuRegUsu varchar(11) NOT NULL,
conRegUsu varchar(60) NOT NULL,
fecHoraIniRegUsu datetime NOT NULL,
fecHoraCieRegUsu datetime,
tieTotRegUsu time,
primary key(codRegUsu),
foreign key(idUsuRegUsu) references usuario(idUsu)
);
insert into registroUsuario(idUsuRegUsu,conRegUsu,fecHoraIniRegUsu,fecHoraCieRegUsu,tieTotRegUsu)
values('1102458796','torres123','2025-11-9 08:00:00','2025-11-9 16:00:00','08:00:00'),
('1025487963','maria123','2025-11-9 08:10:00','2025-11-9 16:20:00','08:10:00');
select * from registroUsuario;

create table asignacionLlamada(
codAsi int auto_increment,
idUsuAsi varchar(11) NOT NULL,
idCliAsi varchar(11) NOT NULL,
primary key(codAsi),
foreign key(idusuAsi) references usuario(idUsu),
foreign key(idCliAsi) references cliente(idCli)
);
insert into asignacionLlamada(idUsuAsi,idCliAsi)
values('1102458796','11262365'),('1102458796','1024587963');
select * from asignacionLlamada;

create table interaccion(
codInt int auto_increment,
idUsuInt varchar(11) NOT NULL,
idCliInt varchar(11) NOT NULL,
codCamInt int NOT NULL,
tipConInt ENUM('Llamada','Chat','Correo') NOT NULL,
motInt text NOT NULL,
resInt text NOT NULL,
fecInt date NOT NULL,
horIniInt time NOT NULL,
horFinInt time NOT NULL,
tieProInt time NOT NULL,
obsInt text NOT NULL,
cieCasInt ENUM('Abierto','En proceso','Resuelto','Escalado','Seguimiento')  NOT NULL,
primary key(codInt),
foreign key(idUsuInt) references usuario(idUsu),
foreign key(idCliInt) references cliente(idCli),
foreign key(codCamInt) references campana(codCam)
);
insert into interaccion(idUsuInt,idCliInt,codCamInt,tipConInt,motInt,resInt,fecInt,horIniInt,horFinInt,tieProInt,obsInt,cieCasInt)
values('1102458796','11262365',1,'Llamada','Actualización de datos personales','Datos actualizados correctamente','2025-11-10','09:00:00','09:10:00','00:10:00','Cliente colaboró en todo el proceso','Resuelto'),
('1025487963','1024587963',2,'Llamada','Encuesta de satisfacción','Encuesta completada','2025-11-10','09:20:00','09:27:00','00:07:00','Cliente respondió todas las preguntas','Resuelto');
select * from interaccion;

create table caso(
codCas int auto_increment,
codIntCas int NOT NULL,
comIntCas text NOT NULL,
fecIniCas date NOT NULL,
fecCieCas date NOT NULL,
primary key(codCas),
foreign key(codIntCas) references interaccion(codInt)
);
insert into Caso(codIntCas,comIntCas,fecIniCas,fecCieCas)
values(1,'Actualizacion completada y verificada','2025-11-10','2025-11-10'),
(2,'Encuesta finalizada sin inconvenientes','2025-11-10','2025-11-10');
select * from Caso;


