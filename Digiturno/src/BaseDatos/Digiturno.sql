create database digiturno;
use digiturno;

create table visitante(
docVis varchar(11) NOT NULL,
nomVis varchar(30) NOT NULL,
apeVis varchar(30) NOT NULL,
telVis varchar(10) NOT NULL,
emaVis varchar(100) NOT NULL,
primary key(docVis)
);
insert into visitante
values('0001','Juan','Porras','3113234343','juan@gmail.com'),
('0002','Luz','Merchan','3182312992','luz@gmail.com');

create table ingreso(
codIng Int auto_increment,
docVisIng varchar(11) NOT NULL,
fecIng datetime NOT NULL,
depIng varchar(20) NOT NULL,
motIng varchar(500) NOT NULL,
primary key(codIng),
foreign key(docVisIng) references visitante(docVis)
);

insert into ingreso(docVisIng,fecIng,depIng,motIng)
values('0001','2026-02-12 8:00','Biblioteca','Devolver libros'),
('0002','2026-03-10 7:00','Auditorio','Charla de salud en el trabajo');
