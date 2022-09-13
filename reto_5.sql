create database SistemaLTI_SAS;
use SistemaLTI_SAS;

create table Ticket(
	id int not null auto_increment,
    nit varchar(20) not null unique,
    nombre_cliente varchar(80)not null,
    direccion_cliente varchar(80) not null,
    telefono_cliente varchar(15),
    fecha_servicio date not null,
    tipo_servicio varchar(80) not null,
    comentario varchar(255),
    constraint id_ticket_pk primary key(id)
);

insert into Ticket (nit, tipo_servicio, nombre_cliente, direccion_cliente, telefono_cliente, fecha_servicio, comentario) 
	values ("nit-3594","Limpieza","Pedro Ustaris Arias","Mz 5 apm 502","3056947545","2022-09-06","Hola mundo");
insert into Ticket (nit, tipo_servicio, nombre_cliente, direccion_cliente, telefono_cliente, fecha_servicio, comentario) 
	values ("nit-9657","Limpieza","Pedro Ustaris Arias","Mz 5 apm 502","3056947545","2022-10-06","Hola mundo");
update Ticket set nit='nit-5607',nombre_cliente='Jose Armando',direccion_cliente='Mz5 cs 22',telefono_cliente='3546876543',fecha_servicio='2022-12-12',tipo_servicio='Limpieza de virus',comentario='actualizado' where nit='nit-9657';

select * from Ticket; 
drop table Ticket;
drop database SistemaLTI_SAS;


