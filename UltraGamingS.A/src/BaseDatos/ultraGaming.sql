create database ultraGaming;
use ultraGaming;

create table marca(
idMar int auto_increment,
nomMar varchar(50) NOT NULL,
primary key (idMar)
);

create table periferico (
    idPer int auto_increment,
    idMarPer int NOT NULL,
    nomModPer varchar(100) NOT NULL,
    tipPer varchar(50) NOT NULL, -- Mouse, Teclado, Headset
    prePer double NOT NULL,
    fecRegPer date NOT NULL,
    primary key (idPer),
    foreign key (idMarPer) references marca(idMar)
);

insert into marca(nomMar) values
('Logitech G'), 
('Razer'), 
('Corsair'), 
('SteelSeries'), 
('HyperX');	

select per.idPer, per.nomModPer, mar.nomMar, per.prePer, per.fecRegPer
from periferico per, marca mar
where mar.idMar = per.idMarPer;


select per.idPer, per.nomModPer, mar.nomMar, per.prePer, per.fecRegPer
from periferico per, marca mar
where per.fecRegPer='2026-05-04'
and mar.idMar = per.idMarPer;


select count(*)+1 from periferico;