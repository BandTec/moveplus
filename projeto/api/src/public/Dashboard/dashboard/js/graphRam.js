function atualizarGrafico1() {
  const id = estacao.value;
  console.log(id);
  fetch(`http://localhost:3333/leituras/tempo-real/ram/${id}`).then(
    (response) => {
      if (response.ok) {
        response.json().then(function (novoRegistro) {
          console.log(novoRegistro);
          dados1.labels.shift();
          dados1.labels.push(novoRegistro[0].dataHora);
          dados1.datasets[0].data.shift();
          dados1.datasets[0].data.push(
            Math.round(parseFloat(novoRegistro[0].memoriaMonitoracao)),
          );

          window.grafico_linha1.update();
          setTimeout(atualizarGrafico1, 5000);
        });
      } else {
        setTimeout(atualizarGrafico1, 5000);
      }
    },
  );
}

var dados1 = {
  labels: [],
  datasets: [
    {
      label: 'Uso',
      data: [],
      borderWidth: 4,
      borderColor: '#72efdd',
      backgroundColor: '#72efdc67',
    },
  ],
};

function configurarGrafico1() {
  var config = {
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
    layout: {
      padding: {
        left: 30,
        right: 30,
        top: 30,
        bottom: 30,
      },
    },
    title: {
      display: true,
      text: '8,0 GB',
      left: 0,
    },
  };

  return config;
}

function obterDados1() {
  const id = estacao.value;
  console.log(id);
  fetch(`http://localhost:3333/leituras/ultimas/ram/${id}`, {
    cache: 'no-store',
  }).then((response) => {
    if (response.ok) {
      response.json().then((registro) => {
        console.log(registro);
        let reg = registro.reverse();
        dados1.datasets[0].data = [];
        dados1.labels = [];

        reg.map((r) => {
          dados1.labels.push(r.dataHora);
          dados1.datasets[0].data.push(
            Math.round(parseFloat(r.memoriaMonitoracao)),
          );
        });

        plotarGrafico1(dados1);
      });
    }
  });
}

function plotarGrafico1(dados1) {
  var ctx1 = document.getElementById('graphRam').getContext('2d');
  window.grafico_linha1 = Chart.Line(ctx1, {
    data: dados1,
    options: configurarGrafico1(),
  });

  setTimeout(atualizarGrafico1, 5000);
}

// var config1 =  {
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
//     title: {
//             display: true,
//             text: '8,0 GB',
//             left: 0
//         },
// },
//     data:{
//         labels: [],
//         datasets: [{
//             label: 'Uso',
//             data: [
//             ],
//             borderWidth: 4,
//             borderColor: '#72efdd',
//             backgroundColor: '#72efdc67',
//         },]
//     },

// };

// function sortear1() {
//     var limiteMax = 100;

//     return (Math.random() * limiteMax).toFixed(0);
// }

// function recuperarDadosIniciais1() {

//     // esse "registros" será recuperado do backend futuramente
//     var registros = [
//         {
//             momento: '00:03:42',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:03:52',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:02',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:12',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:22',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:32',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear1()
//         },
//         {
//             momento: '00:04:42',
//             leitura: sortear1()
//         },
//     ];

//     var contador = 0;

//     // registros.length é a quantidade de itens em "registros"
//     while (contador < registros.length) {

//         config1.data.labels.push(registros[contador].momento);  // incluir um novo momento
//         config1.data.datasets[0].data.push(registros[contador].leitura);  // incluir uma nova leitura

//         contador++;
//     }

// }

// function receberNovasLeituras1() {
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
//             leitura: sortear1()
//         };

//         // tirando e colocando valores no gráfico
//         config1.data.labels.shift(); // apagar o primeiro
//         config1.data.labels.push(novoRegistro.momento); // incluir um novo momento
//         config1.data.datasets[0].data.shift();  // apagar o primeiro
//         config1.data.datasets[0].data.push(novoRegistro.leitura); // incluir uma nova leitura

//         // Atualiza o gráfico
//         window.graficoLinha1.update();

//         // agendar a chamada da mesma função para daqui a 2 segundos
//         receberNovasLeituras1();
//     }, 5000);
// }

// function plotarGrafico1() {
//     // chamar os 7 últimos registros de leitura
//     recuperarDadosIniciais1();

//     // criação do gráfico na página
//     var ctx1 = document.getElementById('graphRam').getContext('2d');
//     window.graficoLinha1 = new Chart(ctx1, config1);

//     // função que agenda a recuperação da última leitura para daqui a 2 segundos
//     receberNovasLeituras1();
// }

// // indicando que a função "plotarGrafico" será invocada assim que a página carregar
// //  window.onload = () => {
// //     plotarGrafico();
// //  };
