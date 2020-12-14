function createUser(email, credencial, senha, estacao) {
  const ajax = new XMLHttpRequest();
  const params = `email=${email}&credencial=${credencial}&senha=${senha}&estacao=${estacao}`;
  ajax.open('POST', 'http://localhost:3333/users', true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function () {
    if (ajax.status === 200 && ajax.readyState === 4) {
      const response = ajax.responseText;
      alert('cadastrado com sucesso');
      window.location.reload();
    }
    // else {
    //   alert('Preencha todos os campos');
    // }
  };

  ajax.send(params);
}

function cadMaquina(nome, series, estacao) {
  const ajax = new XMLHttpRequest();
  const params = `nome=${nome}&series=${series}&estacao=${user.estacao}`;
  console.log(params);
  ajax.open('POST', 'http://localhost:3333/cadMaquina', true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function () {
    if (ajax.status === 200 && ajax.readyState === 4) {
      const response = ajax.responseText;
      alert('cadastrado com sucesso');
      window.location.reload();
    }
    console.log(ajax.status);
    // else {
    //   alert('Preencha todos os campos');
    // }
  };

  ajax.send(params);
}

function login(email, senha) {
  const ajax = new XMLHttpRequest();
  const params = `email=${email}&senha=${senha}`;
  ajax.open('POST', 'http://localhost:3333/users/login', true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function () {
    console.log(ajax.status);
    if (ajax.status === 200 && ajax.readyState === 4) {
      const response = JSON.parse(ajax.responseText);
      if (response.length > 0) {
        const status = true;
        setTimeout(() => {
          window.location = './../dashboard/dash.html';
        }, 4000);
        const user = {
          email: response[0].emailUsuarioEstacao,
          credencial: response[0].credencialUsuarioEstacao,
          senha: response[0].senhaUsuarioEstacao,
          estacao: response[0].fkEstacao,
        };
        sessionStorage.setItem('user', JSON.stringify(user));
        sessionStorage.setItem('status', JSON.stringify(status));
      } else {
        alert('Usuario e senha incorretos');
        window.location.reload();
      }
    }
  };
  ajax.send(params);
}

function updateUser() {
  const email = document.getElementById('userProfile').value;
  const credencial = document.getElementById('credencialProf').value;
  const senha = document.getElementById('senhaProf').value;

  const oldEmail = localStorage.getItem('email');

  this.update(email, credencial, senha, oldEmail);
}

function update(email, credencial, senha, oldEmail) {
  const ajax = new XMLHttpRequest();
  const params = `email=${email}&credencial=${credencial}&senha=${senha}&oldEmail=${oldEmail}`;
  ajax.open('POST', 'http://localhost:3333/users/perfil', true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function () {
    if (ajax.status === 200 && ajax.readyState === 4) {
      const response = ajax.responseText;
      alert('Atualizado');
      login(email, senha);
    }
    // else {
    //   alert('Preencha todos os campos');
    // }
  };

  ajax.send(params);
}
