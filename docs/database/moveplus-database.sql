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
processadorTerminal varchar(30),
memoriaTerminal varchar (30),
discoTerminal varchar(30),
sistemaOperacionalTerminal varchar(100)
);

create table Terminal(
idTerminal int primary key identity,
nomeTerminal varchar(15),
statusTerminal varchar(10),
seriesNumberTerminal char(10),
fkEstacao int,
fkEmpresaTerminal int,
fkConfigTerminal int,
foreign key (fkEstacao) references Estacao (idEstacao),
foreign key (fkEmpresaTerminal) references EmpresaTerminal(idEmpresaTerminal),
foreign key (fkConfigTerminal) references ConfigTerminal(idConfigTerminal),

check (statusTerminal = 'Operante' or statusTerminal = 'Inoperante' or statusTerminal = 'Manutencao')
);

create table Alerta(
idAlerta int primary key IDENTITY,
nomeAlerta varchar(100),
nivelAlerta varchar(5),

check (nivelAlerta = 'Alto' or nivelAlerta = 'Médio' or nivelAlerta = 'Baixo')
);

create table TerminalAlerta(
dataHoraTerminalAlerta datetime,
fkTerminal int,
fkAlerta int,
foreign key (fkTerminal) references Terminal (idTerminal),
foreign key (fkAlerta) references Alerta (idAlerta),
primary key (dataHoraTerminalAlerta,fkTerminal, fkAlerta)
);

create table Monitoracao( 
idMonitoracao int primary key identity,
memoriaMonitoracao decimal (4,2),
cpuMonitoracao decimal (4,2),
discoMonitoracao decimal (4,2),
dataHoraMonitoracao datetime,
fkTerminal int,
foreign key (fkTerminal) references Terminal (idTerminal)
);