const connection = require('../database/connection');

const Leitura = {
  async getCpu(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 10 cpuMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async attCpu(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 1 cpuMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async getRam(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 10 memoriaMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async attRam(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 1 memoriaMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async getDisco(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 10 discoMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async attDisco(request, response) {
    const { id } = request.params;

    const sql = `SELECT TOP 1 discoMonitoracao , FORMAT (dataHoraMonitoracao , 'HH:mm:ss') as dataHora FROM monitoracao WHERE fkTerminal = ${id} ORDER BY idMonitoracao DESC`;

    connection.conectar().then(() => {
      return connection.sql
        .query(sql)
        .then((result) => {
          response.send(result.recordsets[0]);
        })
        .catch((erro) => {
          console.log(erro);
        });
    });
  },

  async metrica1(request, response) {
    const sql =
      "SELECT [dbo].[calcularErros](1) as 'alerta1', [dbo].[calcularErros](2) as 'alerta2', [dbo].[calcularErros](3) as 'alerta3', [dbo].[calcularErros](8) as 'alerta8', [dbo].[calcularErros](9) as 'alerta9'";

    connection
      .conectar()
      .then(() => {
        return connection.sql
          .query(sql)
          .then((result) => {
            response.send(result.recordsets[0]);
          })
          .catch((erro) => {
            console.log(erro);
          });
      })
      .catch((erro) => {
        console.log(erro);
      });
  },
};

module.exports = Leitura;
