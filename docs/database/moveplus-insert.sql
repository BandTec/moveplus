INSERT INTO Estacao values ('Piqueri',4);
INSERT INTO Estacao values ('Ana Rosa',8);
INSERT INTO Estacao values ('Lapa',4);

INSERT INTO UsuarioEstacao values ('ABCDE12345','luis.sousa@cptm.gov.sp','qwerty123',1);


INSERT INTO EmpresaTerminal values ('PRODATA Mobility','11967738383','suporte@prodata.com.br');


INSERT INTO Terminal (nomeTerminal,statusTerminal,seriesNumberTerminal,fkEstacao,fkEmpresaTerminal) values 
('TERM-NORTE-01','Inoperante','ZXCVB98765',1,1);
INSERT INTO Terminal (nomeTerminal,statusTerminal,seriesNumberTerminal,fkEstacao,fkEmpresaTerminal) values 
('TERM-NORTE-02','Inoperante','ZXCVB98766',1,1);
INSERT INTO Terminal (nomeTerminal,statusTerminal,seriesNumberTerminal,fkEstacao,fkEmpresaTerminal) values 
('TERM-NORTE-03','Inoperante','YXPTO33240',1,1);

INSERT INTO Alerta values ('USO DE RAM SUPERIOR A 95% POR 10 MINUTOS','Alto');
INSERT INTO Alerta values ('USO DE CPU SUPERIOR A 95% POR 10 MINUTOS','Alto');
INSERT INTO Alerta values ('USO DE DISCO SUPERIOR A 75% POR 1 HORA','Alto');

select * from Alerta;

select * from TerminalAlerta;

select * from Terminal;

select * from ConfigTerminal;

select * from Monitoracao;