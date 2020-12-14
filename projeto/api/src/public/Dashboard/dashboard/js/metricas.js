var d = {
  datasets: [
    {
      label: 'Alertas',
      data: [],
      backgroundColor: ['#48bfe3', '#72efe3', '#FFFF', '#c4c4c4', '#f3f3f3'],
    },
  ],
  labels: [],
};

function configMetrica() {
  var config = {
    type: 'doughnut',
    options: {
      responsive: true,
      title: {
        display: false,
        text: 'Chart.js Doughnut Chart',
      },
      animation: {
        animateScale: true,
        animateRotate: true,
      },
    },
  };

  return config;
}

function metrica1() {
  fetch(`http://localhost:3333/leituras/metrica1`).then((response) => {
    if (response.ok) {
      response
        .json()
        .then((res) => {
          console.log(res);
          //d.datasets[0].data = [];
          //d.labels = [];

          d.datasets[0].data.push(parseFloat(res[0].alerta1));
          d.datasets[0].data.push(parseFloat(res[0].alerta2));
          d.datasets[0].data.push(parseFloat(res[0].alerta3));
          d.datasets[0].data.push(parseFloat(res[0].alerta8));
          d.datasets[0].data.push(parseFloat(res[0].alerta9));

          geraGrafico(d);
          geraGrafico(d);
        })
        .catch((erro) => {
          console.log(erro);
        });
    }
  });
}

function geraGrafico(dados) {
  var ctx = document.getElementById('graphMetrica1').getContext('2d');
  window.myDoughnut = new Chart.Doughnut(ctx, {
    data: dados,
    options: configMetrica(),
  });
}
