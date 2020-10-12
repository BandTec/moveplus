create database bdMetro;
use bdMetro;

create table Estacao (
idEstacao int primary key auto_increment,
nomeEstacao varchar(30),
numeroTerminais int
);

create table UsuarioEstacao(
idUsuario int primary key auto_increment,
credencialUsuarioEstacao char (13),
senhaUsuarioEstacao char (10),
fkEstacao int,
foreign key (fkEstacao) references Estacao (idEstacao)
);

create table EmpresaTerminal(
idEmpresaTerminal int primary key auto_increment,
nomeEmpresa varchar (45),
telefoneEmpresa char(13),
emailEmpresa varchar (45)
);



create table Terminal(
idTerminal int primary key auto_increment,
statusTerminal enum('3'),
numeroDeSerie char(10),
fkEstacao int,
foreign key (fkEstacao) references Estacao (idEstacao),
fkEmpresaTerminal int,
foreign key (fkEmpresaTerminal) references EmpresaTerminal(idEmpresaTerminal)
);


create table Monitoracao( 
idMonitoracao int primary key auto_increment,
memoria decimal (4,2),
cpuTerminal decimal (4,2),
disco decimal (4,2),
rede decimal (4,2),
gpu decimal (4,2),
dataHorario datetime,
fkTerminal int,
foreign key (fkTerminal) references Terminal (idTerminal)
);

create table TipoErro(
idProblema int primary key auto_increment,
nomeProblema varchar(45),
gravidadeProblema char (45),
tempoDeConcerto char(10)
);

create table TerminalLogErro(
fkTerminal int,
foreign key (fkTerminal) references Terminal (idTerminal),
fkProblema int,
foreign key (fkProblema) references TipoErro (idProblema),
primary key (fkTerminal, fkProblema),
DataHora datetime
);

