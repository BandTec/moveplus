function createUser(email, credencial, senha){
  const ajax = new XMLHttpRequest();
  const params = `email=${email}&credencial=${credencial}&senha=${senha}`;
  ajax.open("POST", "http://localhost:3333/users", true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function(){
    if(ajax.status === 200 && ajax.readyState === 4){
      const response = ajax.responseText;
      alert("cadastrado com sucesso");
      window.location.reload();
    }
    // else {
    //   alert('Preencha todos os campos');
    // }
  }

  ajax.send(params);

}

function login(email, senha){
  const ajax = new XMLHttpRequest();
  console.log('entrou');
  const params = `email=${email}&senha=${senha}`;
  ajax.open("POST", "http://localhost:3333/users/login", true);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.onreadystatechange = function(){
    if(ajax.status === 200 && ajax.readyState === 4){
      const response = JSON.parse(ajax.responseText);
      console.log(response);
      if(response.length > 0){
        const status = true;
        console.log('entrou');
        setTimeout(() =>{
          window.location = "../dashboard/dash.html";
        }, 4000);
        const user = {
          email: response[0].emailUsuarioEstacao, 
          credencial: response[0].credencialUsuarioEstacao,
          senha: response[0].senhaUsuarioEstacao
        }
        sessionStorage.setItem("user", JSON.stringify(user));
        sessionStorage.setItem("status", JSON.stringify(status));
      } else {
        alert('Usuario e senha incorretos');
        window.location.reload();
      }

    } 
  }
  ajax.send(params);
}









