create table Estacao (
idEstacao int primary key identity,
nomeEstacao varchar(30),
qtTerminalEstacao int
);

create table UsuarioEstacao(
idUsuarioEstacao int primary key identity,
credencialUsuarioEstacao varchar(25),
emailUsuarioEstacao varchar(50),
senhaUsuarioEstacao char (10),
fkEstacao int,
foreign key (fkEstacao) references Estacao (idEstacao)
);

create table EmpresaTerminal(
idEmpresaTerminal int primary key identity,
nomeEmpresaTerminal varchar (45),
telefoneEmpresaTerminal char(13),
emailEmpresaTerminal varchar (45)
);

create table ConfigTerminal(
idConfigTerminal int primary key identity,
processadorTerminal varchar(45),
memoriaTerminal varchar (45),
discoTerminal varchar(45),
sistemaOperacionalTerminal varchar(45)
);

create table Terminal(
idTerminal int primary key identity,
statusTerminal int check (statusTerminal IN ('Operante','Não Operante','Manutencao')),
seriesNumberTerminal char(10),
fkEstacao int,
fkEmpresaTerminal int,
fkConfigTerminal int,
foreign key (fkEstacao) references Estacao (idEstacao),
foreign key (fkEmpresaTerminal) references EmpresaTerminal(idEmpresaTerminal),
foreign key (fkConfigTerminal) references ConfigTerminal(idConfigTerminal)
);

create table Erro(
idErro int primary key IDENTITY,
nomeErro varchar(25),
nivelErro int check (nivelErro IN ('Baixo','Medio','Alto','Critico')),
tempoManutencaoErro time
);

create table TerminalErro(
datahoraTerminalErro datetime,
fkTerminal int,
fkErro int,
foreign key (fkTerminal) references Terminal (idTerminal),
foreign key (fkErro) references Erro (idErro),
primary key (fkTerminal, fkErro),
);

create table Monitoracao( 
idMonitoracao int primary key identity,
memoriaMonitoracao decimal (4,2),
cpuMonitoracao decimal (4,2),
discoMonitoracao decimal (4,2),
redeMonitoracao decimal (4,2),
dataHoraMonitoracao datetime,
fkTerminal int,
foreign key (fkTerminal) references Terminal (idTerminal)
);