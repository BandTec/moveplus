const express = require('express');
const routes = express.Router();

const UserController = require('./controllers/UserController');
const LeituraController = require('./controllers/LeiturasController');

routes.get('/users', UserController.index);
routes.post('/users', UserController.create);
routes.post('/users/login', UserController.login);
routes.get('/station', UserController.station);
routes.post('/users/perfil', UserController.update);
routes.post('/cadMaquina', UserController.cadMaquina);
routes.get('/getAllTerminais', UserController.getAllTerminais);
routes.get('/getActiveTerminals', UserController.getActiveTerminals);
routes.get('/getInactiveTerminals', UserController.getInactiveTerminals);
routes.get('/terminais/:id', UserController.getTerminais);

routes.get('/leituras/ultimas/cpu/:id', LeituraController.getCpu);
routes.get('/leituras/tempo-real/cpu/:id', LeituraController.attCpu);

routes.get('/leituras/ultimas/ram/:id', LeituraController.getRam);
routes.get('/leituras/tempo-real/ram/:id', LeituraController.attRam);

routes.get('/leituras/ultimas/disco/:id', LeituraController.getDisco);
routes.get('/leituras/tempo-real/disco/:id', LeituraController.attDisco);

routes.get('/leituras/metrica1', LeituraController.metrica1);
module.exports = routes;
