const configuracoes = {
    config: {
        server: "moveplus-server.database.windows.net",
        user: "master",
        password: "Moveplus2020",
        database: "moveplus-database-final",
        options: {
            encrypt: true,
            enableArithAbort: true 
        },
        pool: {
            max: 4,
            min: 1,
            idleTimeoutMillis: 30000,
            connectionTimeout: 5000
        }
    }
}
 
const sql = require('mssql');
sql.on('error', err => {
    console.error(`Erro de Conexão: ${err}`);
});


function conectar() {
  //sql.close();
  return sql.connect(configuracoes.config)
} 

module.exports = {
    conectar: conectar,
    sql: sql
}