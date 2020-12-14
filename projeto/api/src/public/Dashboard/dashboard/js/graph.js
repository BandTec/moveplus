function atualizarGrafico() {
  const id = estacao.value;
  console.log(id);
  fetch(`http://localhost:3333/leituras/tempo-real/cpu/${id}`).then(
    (response) => {
      if (response.ok) {
        response.json().then(function (novoRegistro) {
          console.log(novoRegistro);
          dados.labels.shift();
          dados.labels.push(novoRegistro[0].dataHora);
          dados.datasets[0].data.shift();
          dados.datasets[0].data.push(
            Math.round(parseFloat(novoRegistro[0].cpuMonitoracao)),
          );

          window.grafico_linha.update();
          setTimeout(atualizarGrafico, 5000);
        });
      } else {
        setTimeout(atualizarGrafico, 5000);
      }
    },
  );
}

var dados = {
  labels: [],
  datasets: [
    {
      label: 'Uso',
      data: [],
      borderWidth: 6,
      borderColor: '#48bfe3',
      backgroundColor: '#48bfe375',
    },
  ],
};

function configurarGrafico() {
  var config = {
    layout: {
      padding: {
        left: 30,
        right: 30,
        top: 30,
        bottom: 30,
      },
    },
    scales: {
      yAxes: [
        {
          ticks: {
            min: 0,
            max: 100,
          },
        },
      ],
    },
    responsive: 'true',
    // title: {
    //   display: true,
    //   text: '',
    //   left: 0,
    // },
  };

  return config;
}

function obterDados() {
  const id = estacao.value;
  console.log(id);
  fetch(`http://localhost:3333/leituras/ultimas/cpu/${id}`, {
    cache: 'no-store',
  }).then((response) => {
    if (response.ok) {
      response.json().then((registro) => {
        console.log(registro);
        let reg = registro.reverse();
        dados.datasets[0].data = [];
        dados.labels = [];

        reg.map((r) => {
          dados.labels.push(r.dataHora);
          dados.datasets[0].data.push(Math.round(parseFloat(r.cpuMonitoracao)));
        });

        plotarGrafico(dados);
      });
    }
  });
}

function plotarGrafico(dados) {
  var ctx = document.getElementById('graphCpu').getContext('2d');
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGrafico(),
  });

  setTimeout(atualizarGrafico, 5000);
}

// var config =  {
//     type: 'line',
//     options: {
//     layout: {
//         padding: {
//             left: 30,
//             right: 30,
//             top: 30,
//             bottom: 30
//         }
//     },
//     responsive:'true',
//     title: {
//             display: true,
//             text: 'Intel(R) Core(TM) i7-8565U CPU @ 1.80GHz',
//             left: 0
//         },
// },
//     data:{
//         labels: [],
//         datasets: [{
//             label: 'Uso',
//             data: [
//             ],
//             borderWidth: 6,
//             borderColor: '#48bfe3',
//             backgroundColor: '#48bfe375',
//         },]
//     },

// };

// function sortear() {
//     var limiteMax = 100;

//     return (Math.random() * limiteMax).toFixed(0);
// }

// function recuperarDadosIniciais() {

//     // esse "registros" será recuperado do backend futuramente
//     var registros = [
//         {
//             momento: '00:03:42',
//             leitura: sortear()
//         },
//         {
//             momento: '00:03:52',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:02',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:12',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:22',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:32',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear()
//         },
//     ];

//     var contador = 0;

//     // registros.length é a quantidade de itens em "registros"
//     while (contador < registros.length) {

//         config.data.labels.push(registros[contador].momento);  // incluir um novo momento
//         config.data.datasets[0].data.push(registros[contador].leitura);  // incluir uma nova leitura

//         contador++;
//     }

// }

// var rand = 0;

// function receberNovasLeituras() {
//     setTimeout(() => {

//         // esses "agora" etc até "momentos" serão desnecessários quando usarmos o backend futuramente
//         var agora = new Date();
//         var hora = agora.getHours();
//         var minuto = agora.getMinutes();
//         var segundo = agora.getSeconds();
//         var momento = `${hora>9?'':'0'}${hora}:${minuto>9?'':'0'}${minuto}:${segundo>9?'':'0'}${segundo}`;

//         // esse "novoRegistro" será recuperado do backend futuramente
//         var novoRegistro = {
//             momento: momento,
//             leitura: sortear()
//         };

//         rand = novoRegistro.leitura;

//         // tirando e colocando valores no gráfico
//         config.data.labels.shift(); // apagar o primeiro
//         config.data.labels.push(novoRegistro.momento); // incluir um novo momento
//         config.data.datasets[0].data.shift();  // apagar o primeiro
//         config.data.datasets[0].data.push(novoRegistro.leitura); // incluir uma nova leitura

//         // Atualiza o gráfico
//         window.graficoLinha.update();

//         // agendar a chamada da mesma função para daqui a 2 segundos
//         receberNovasLeituras();
//     }, 5000);
// }

// function plotarGrafico() {
//     // chamar os 7 últimos registros de leitura
//     recuperarDadosIniciais();

//     // criação do gráfico na página
//     var ctx = document.getElementById('graphCpu').getContext('2d');
//     window.graficoLinha = new Chart(ctx, config);

//     // função que agenda a recuperação da última leitura para daqui a 2 segundos
//     receberNovasLeituras();
// }

// // indicando que a função "plotarGrafico" será invocada assim que a página carregar
//  window.onload = () => {
//     plotarGrafico();
//     plotarGrafico1();
//     plotarGrafico2();
//  };
