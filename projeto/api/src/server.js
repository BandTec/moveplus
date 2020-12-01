const app = require('./app');

app.listen(process.env.PORT || 3333,function() {
  console.log('Servidor rodando na porta 3333');
})