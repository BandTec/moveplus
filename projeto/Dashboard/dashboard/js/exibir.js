fetch('http://localhost:3333/station').then((response) => {
  if (response.ok) {
    const nameStation = document.getElementById('nomeEstacao');
    response.json().then((res) => {
      const user = JSON.parse(sessionStorage.getItem('user'));
      const estacao = res.find((item) => item.idEstacao === user.estacao);
      nameStation.innerHTML = estacao.nomeEstacao;
    });
  }
});
