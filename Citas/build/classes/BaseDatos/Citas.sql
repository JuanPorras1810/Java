create database Citas;
use Citas;
create table profesional(
docProf	varchar(11) NOT NULL,
nomProf	varchar(30) NOT NULL,
apeProf	varchar(30) NOT NULL,
emaProf	varchar(100) NOT NULL,
TarProf	varchar(10) ,
rolProf	varchar(20) NOT NULL,
telProf	varchar(11) NOT NULL,
primary key(docProf)
);
insert into profesional 
values('1001','Carlos','Perez','carlos@mail.com','TP123','Psicologo','3001234567'),
('1002','Ana','Gomez','ana@mail.com','TP456','Medico','3007654321');

create table grupo(
codGrupo varchar(10) NOT NULL,
nomGrupo varchar(300) NOT NULL,
fechIniGrupo varchar(10) NOT NULL,
fechFinGrupo varchar(10) NOT NULL,
ambGrupo varchar(10) NOT NULL,
insLidGrupo varchar(200) NOT NULL,
jornGrupo varchar(10) NOT NULL,
primary key(codGrupo)
);	
insert into grupo 
values('3000001','ADSO','2025-08-10','2027-08-10','B1308','Magda Garcia','Mañana'),
('3000002','Sistemas','2025-02-01','2027-02-01','B1309','Carlos Andres','Noche');


create table aprendiz(
docApre	varchar(11) NOT NULL,
codGrupoApre varchar(10) NOT NULL,
nomApre	varchar(30) NOT NULL,
apeApre	varchar(30) NOT NULL,
emaApre	varchar(100) NOT NULL,
telApre	varchar(11) NOT NULL,
epsApre	varchar(50) NOT NULL,
conEmerApre	varchar(100) NOT NULL,
telConEmerApre varchar(11) NOT NULL,
primary key (docApre),
foreign key(codGrupoApre) references grupo(codGrupo)
);
insert into aprendiz 
values('2001','3000001','Luis','Martinez','luis@mail.com','3011111111','Sura','Maria Martinez','3022222222'),
('2002','3000002','Laura','Rodriguez','laura@mail.com','3033333333','Sanitas','Pedro Rodriguez','3044444444');

create table agendamiento(
codAgen int auto_increment NOT NULL,
docApreAgen varchar(11) NOT NULL,
docProfAgen varchar(11) NOT NULL,
fechaAgen varchar(10) NOT NULL,
horaAgen varchar(7) NOT NULL,
motAgen varchar(300) NOT NULL,
primary key(codAgen),
foreign key(docApreAgen) references aprendiz(docApre),
foreign key(docProfAgen) references profesional(docProf)
); 

insert into agendamiento (docApreAgen, docProfAgen, fechaAgen, horaAgen, motAgen) 
values('2001','1001','2026-03-01','08:00','Consulta psicologica'),
('2002','1002','2026-04-03','10:00','problema respiratorio');


create table historiaClinica(
codHisCli int auto_increment,
codAgenHisCli int NOT NULL,
docApreHisCli varchar(11),
descHisCli varchar(300) NOT NULL,
obserHisCli	varchar(300) NOT NULL,
medHisCli varchar(100) NOT NULL,
fechHoraHisCli varchar(30) NOT NULL,
primary key(codHisCli),
foreign key(codAgenHisCli) references agendamiento(codAgen),
foreign key(docApreHisCli) references aprendiz(docApre)
);
insert into historiaClinica (codAgenHisCli, docApreHisCli, descHisCli, obserHisCli, medHisCli,fechHoraHisCli ) 
values(1,'2001','Consulta psicologica','Paciente presenta ansiedad leve','Analgesicos','2026-03-08 07:00'),
(2,'2002','Problema respiratorio','Se recomienda tratamiento y seguimiento','Inhalador','2026-04-10 08:00');