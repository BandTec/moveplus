const connection = require('../database/connection');

const Usuario = {
  async index(request, response) {
    connection
      .conectar()
      .then(() => {
        return connection.sql
          .query('SELECT * FROM UsuarioEstacao')
          .then((result) => {
            response.send(result);
          });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
    //response.send("XABRAU")
  },

  async station(request, response) {
    connection
      .conectar()
      .then(() => {
        return connection.sql.query('SELECT * FROM Estacao').then((result) => {
          response.send(result.recordset);
        });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
    //response.send("XABRAU")
  },

  async login(request, response) {
    const { email, senha } = request.body;
    console.log(request.body);
    connection
      .conectar()
      .then(() => {
        return connection.sql
          .query(
            `select * from UsuarioEstacao where emailUsuarioEstacao = '${email}' and senhaUsuarioEstacao = '${senha}'`,
          )
          .then((result) => {
            console.log(result);
            response.send(result.recordset);
          });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
  },

  async create(request, response) {
    const { email, credencial, senha, estacao } = request.body;
    connection
      .conectar()
      .then(() => {
        return connection.sql
          .query(
            `insert into UsuarioEstacao(emailUsuarioEstacao, credencialUsuarioEstacao, senhaUsuarioEstacao, fkEstacao) values ('${email}','${credencial}','${senha}',${estacao})`,
          )
          .then((result) => {
            response.send(result.recordset);
          });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
  },

  async cadMaquina(request, response) {
    const { nome, series, estacao } = request.body;
    connection
      .conectar()
      .then(() => {
        return connection.sql
          .query(
            `insert into Terminal(nomeTerminal, seriesNumberTerminal, fkEstacao) values ('${nome}','${series}',${estacao})`,
          )
          .then((result) => {
            response.send(result.recordset);
          });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
  },

  async update(request, response) {
    const { email, credencial, senha, oldEmail } = request.body;
    console.log(request.body);

    const sql = `update UsuarioEstacao set emailUsuarioEstacao = '${email}', credencialUsuarioEstacao = '${credencial}', senhaUsuarioEstacao = '${senha}' where emailUsuarioEstacao like '${oldEmail}'`;

    connection
      .conectar()
      .then(() => {
        console.log('Entrou 1');
        return connection.sql
          .query(sql)
          .then((result) => {
            console.log(result);
            response.send(result.recordset);
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch((error) => {
        console.log('ERRO' + error);
      })
      .finally(() => {
        connection.sql.close();
      });
    /*connection.query(sql, [email, credencial, senha, oldEmail], function (error, result) {
      if (error) throw error;
      response.send(result);
    });*/
  },

  async getTerminais(request, response) {
    const { id } = request.params;
    const sql = `SELECT * FROM Terminal WHERE fkEstacao = ${id}`;
    connection.conectar().then(() => {
      return connection.sql.query(sql).then((result) => {
        console.log(result);
        response.send(result.recordset);
      });
    });
  },
};

module.exports = Usuario;
