fetch('http://localhost:3333/station').then((response) => {
  if (response.ok) {
    console.log(response);
    const nameStation = document.getElementById('nomeEstacao');
    response.json().then((res) => {
      res.map((nome) => {
        nameStation.innerHTML = nome.nomeEstacao;
      });
    });
  }
});
