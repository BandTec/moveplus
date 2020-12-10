const express = require('express');
const routes = express.Router();

const UserController = require('./controllers/UserController');

routes.get('/users', UserController.index);
routes.post('/users', UserController.create);
routes.post('/users/login', UserController.login);
routes.get('/station', UserController.station);
routes.post('/users/perfil', UserController.update);

module.exports = routes;
