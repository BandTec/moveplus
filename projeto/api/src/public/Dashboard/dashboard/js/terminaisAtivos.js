fetch('http://localhost:3333/getAllTerminais').then((response) => {
  if (response.ok) {
    const totalEstacao = document.getElementById('total');
    response.json().then((res) => {
      totalEstacao.innerHTML = res.recordset.length;
    });
  }
});

fetch('http://localhost:3333/getActiveTerminals').then((response) => {
  if (response.ok) {
    const totalEstacaoAtivo = document.getElementById('ativo');
    response.json().then((res) => {
      totalEstacaoAtivo.innerHTML = res.recordset.length;
    });
  }
});

fetch('http://localhost:3333/getInactiveTerminals').then((response) => {
  if (response.ok) {
    const totalEstacaoInativo = document.getElementById('inativo');
    response.json().then((res) => {
      totalEstacaoInativo.innerHTML = res.recordset.length;
    });
  }
});
