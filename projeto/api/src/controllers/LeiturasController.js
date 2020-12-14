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
};

module.exports = Leitura;
