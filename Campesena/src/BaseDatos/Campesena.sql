create database Historial_Clinico;
use Historial_Clinico;


create table animal(
	codigo int auto_increment,
    categoria varchar(60) not null,
    raza varchar(60) not null,
    color varchar(60) not null,
    tamaño decimal not null,
    genero enum("Masculino","Femenino") not null,
    nombre varchar(255) not null,
    primary key (codigo)    
);

create table rol(
	id int auto_increment,
    tipo varchar(50) not null,
    primary key (id)    
);

create table usuario(
	identificacion varchar(10) not null,
    id_rol int not null,
    tipo_identificacion varchar(100) not null,
    nombre varchar(80) not null,
    direccion varchar(100) not null,
	telefono varchar(15),
    password varchar(10),
    primary key (identificacion),
	foreign key (id_rol) references rol (id)
);

create table historial_clinico(
	codigo varchar(8) not null,
    codigo_animal int not null,
    identificacion_usuario varchar(10) not null,
    diagnostico text not null,
	tratamiento varchar(255) not null,
    medicamentos text not null,
    fecha_visita date not null,
    observaciones text,
    primary key (codigo),
    foreign key (codigo_animal) references animal (codigo),
    foreign key (identificacion_usuario) references usuario (identificacion)
);

insert into animal (categoria,raza,color,tamaño,genero,nombre) 
values ('Bovino','Holstein','Blanco con negro',450,'Femenino','Lola'),
('Porcino','Yorkshire','Rosado',120,'Masculino','Max');

insert into rol (tipo) 
values('Administrador'),
('Capataz');

insert into usuario (identificacion, id_rol, tipo_identificacion, nombre, direccion, telefono, password)
values('1001',1,'Cedula de Ciudadanía','Carlos | Ramirez','Calle 10 #20-30','3001234567','admin123'),
('1002',2,'Cedula de Ciudadanía','Laura | Gomez','Carrera 15 #45-20','3109876543','vet123');

insert into historial_clinico (codigo,codigo_animal,identificacion_usuario,diagnostico,tratamiento,medicamentos,fecha_visita,observaciones) 
values('1',1,'1002','Vacunacion','Aplicación de refuerzo','Vacuna triple','2026-04-10','Control posterior recomendado'),
('2',2,'1002','Control por crecimiento','Dieta balanceada','Vitaminas','2026-04-12','Evolución estable');


