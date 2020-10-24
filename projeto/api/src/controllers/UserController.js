const connection = require('../database/connection');
const Usuario = {
  async index(request, response){
    connection.query('select * from UsuarioEstacao', function(error, result){
      if (error) throw error;
      response.send(result); 
    });
  },

  async login(request, response){
    const { email, senha } = request.body;
    const sql = `select * from UsuarioEstacao where emailUsuarioEstacao = ? and senhaUsuarioEstacao = ?`;
    connection.query(sql,[email, senha], function(error, result){
      if (error) throw error;
      response.send(result);
    });
  },

  async create(request, response){
    const { email, credencial, senha } = request.body;

    const sql = `insert into UsuarioEstacao(emailUsuarioEstacao, credencialUsuarioEstacao, senhaUsuarioEstacao) values (?,?,?)`;

    connection.query(sql, [email, credencial, senha], function(error, result){
      if (error) throw error;
      response.send(result);
    });
  }
}

module.exports = Usuario;
