create database proyectoVentas;
use proyectoVentas;

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
values('0001','Cedula de Ciudadania','Juan Porras','juan@gmail.com','Calle 1','3001111111','3002222222','Supervisor','juan123'),
('0002','Cedula de Ciudadania','Maria Lopez','maria@gmail.com','Calle 2','3003333333',NULL,'Agente','maria123'),
('0003','Cedula de Ciudadania','Luis Gomez','luis@gmail.com','Calle 3','3007777777',NULL,'Agente','luis123');

create table campana(
codCam int auto_increment,
nomCam varchar(500) NOT NULL,
fecIniCam date NOT NULL,
fecFinCam date NOT NULL,
proCam varchar(250),
primary key(codCam)
);
insert into campana(nomCam,fecIniCam,fecFinCam,proCam)
values('Campaña Ventas 1','2026-01-01','2026-12-31',NULL),
('Campaña Ventas 2','2026-02-01','2026-11-30',NULL);

create table baseDatosAsesor(
conAse int auto_increment,
idUsuAse varchar(11) NOT NULl,
codCamAse int NOT NULL,
primary key(conAse),
foreign key(idUsuAse) references usuario(idUsu),
foreign key(codCamAse) references campana(codCam)
);
insert into baseDatosAsesor(idUsuAse,codCamAse)
values ('0002',1),
('0003',2);

create table baseDatosCliente(
conCli int auto_increment,
codCamCli int NOT NULL,
idCli varchar(11) ,
tipDoCli varchar(35),
nomCli varchar(60),
emaCli varchar(100),
dirCli varchar(60),
barCli varchar(150),
munCli varchar(150),
telCli varchar(10) NOT NULL,
telAltCli varchar(10),
obsCli varchar (300),
primary key(conCli),
foreign key(codCamCli) references campana(codCam)
);
insert into baseDatosCliente(codCamCli,idCli,tipDoCli,nomCli,emaCli,dirCli,barCli,munCli,telCli,telAltCli,obsCli)
values(1,'0004','Cedula de Ciudadania','Carlos Ruiz','carlos@gmail.com','Calle 4','Barrio A','Municipio A','3004444444',NULL,'Cliente nuevo'),
(2,'0005','Cedula de Ciudadania','Ana Torres','ana@gmail.com','Calle 5','Barrio B','Municipio B','3005555555','3006666666','Interesado');

create table registroUsuario(
codRegUsu int auto_increment,
idUsuRegUsu varchar(11) NOT NULL,
fecHoraIniRegUsu datetime NOT NULL,
fecHoraCieRegUsu datetime,
tieTotRegUsu time,
primary key(codRegUsu),
foreign key(idUsuRegUsu) references usuario(idUsu)
);
insert into registroUsuario(idUsuRegUsu,fecHoraIniRegUsu,fecHoraCieRegUsu,tieTotRegUsu)
values('0001','2026-04-01 08:00:00','2026-04-01 17:00:00','09:00:00'),
('0002','2026-04-02 08:30:00','2026-04-02 17:30:00','09:00:00');

create table asignacionLlamada(
codAsi int auto_increment,
conAseAsi int NOT NULL,
conCliAsi int NOT NULL,
fecAsi date NOT NULL,
conAteAsi boolean,
primary key(codAsi),
foreign key(conAseAsi) references baseDatosAsesor(conAse),
foreign key(conCliAsi) references baseDatosCliente(conCli)
);
insert into asignacionLlamada(conAseAsi,conCliAsi,fecAsi,conAteAsi)
values(1,1,'2026-04-01',NULL),
(2,2,'2026-04-08',NULL);

create table tipificaciones(
codTip int auto_increment,
codCamTip int NOT NULL,
nomTip varchar(100) NOT NULL,
primary key(codTip),
foreign key(codCamTip) references campana(codCam)
);
insert into tipificaciones(codCamTip,nomTip)
values (1,'Contacto efectivo'),
(2,'No contesta');

create table interaccion(
codInt int auto_increment,
conAseInt int NOT NULL,
conCliInt int NOT NULL,
codTipInt int NOT NULL,
tipConInt ENUM('Llamada','Chat','Correo') NOT NULL,
motInt text NOT NULL,
fecInt date NOT NULL,
horIniInt time NOT NULL,
horFinInt time NOT NULL,
tieProInt time NOT NULL,
obsInt text NOT NULL,
cieCasInt ENUM('Abierto','Cerrado','Escalado')  NOT NULL,
primary key(codInt),
foreign key(conAseInt) references baseDatosAsesor(conAse),
foreign key(conCliInt) references baseDatosCliente(conCli),
foreign key(codTipInt) references tipificaciones(codTip)
);
insert into interaccion(conAseInt,conCliInt,codTipInt,tipConInt,motInt,fecInt,horIniInt,horFinInt,tieProInt,obsInt,cieCasInt)
values(1,1,1,'Llamada','Consulta producto','2026-04-10','09:00:00','09:10:00','00:10:00','Cliente atendido','Cerrado'),
(2,2,2,'Chat','Reclamo servicio','2026-04-11','10:00:00','10:20:00','00:20:00','Escalar caso','Escalado');

create table caso(
codCas int auto_increment,
codIntCas int NOT NULL,
comIntCas text NOT NULL,
fecIniCas date NOT NULL,
fecCieCas date,
primary key(codCas),
foreign key(codIntCas) references interaccion(codInt)
);
insert into caso(codIntCas,comIntCas,fecIniCas,fecCieCas)
values(2,'Caso escalado a supervisor','2026-04-11','2026-04-12');

