var config2 =  {
    type: 'line',
    options: {
    layout: {
        padding: {
            left: 30,
            right: 30,
            top: 30,
            bottom: 30
        }
    },
    title: {
            display: true,
            text: 'SSD Kingston 200GB',
            left: 0
        },
},
    data:{
        labels: [],
        datasets: [{
            label: 'Uso',
            data: [
            ],
            borderWidth: 4,
            borderColor: '#3d3d3d',
            backgroundColor: '#3d3d3d5d',
        },]
    },
    
};

function sortear2() {
    var limiteMax = 100;

    return (Math.random() * limiteMax).toFixed(0);
}

function recuperarDadosIniciais2() {

    // esse "registros" será recuperado do backend futuramente
    var registros = [
        {
            momento: '00:03:42',
            leitura: sortear2()
        },
        {
            momento: '00:03:52',
            leitura: sortear2()
        },
        {
            momento: '00:04:02',
            leitura: sortear2()
        },
        {
            momento: '00:04:12',
            leitura: sortear2()
        },
        {
            momento: '00:04:22',
            leitura: sortear2()
        },
        {
            momento: '00:04:32',
            leitura: sortear2()
        },
        {
            momento: '00:04:42',
            leitura: sortear2()
        },
        {
            momento: '00:04:42',
            leitura: sortear2()
        },
        {
            momento: '00:04:42',
            leitura: sortear2()
        },
        {
            momento: '00:04:42',
            leitura: sortear2()
        },
    ];

    var contador = 0;

    // registros.length é a quantidade de itens em "registros"
    while (contador < registros.length) {

        config2.data.labels.push(registros[contador].momento);  // incluir um novo momento
        config2.data.datasets[0].data.push(registros[contador].leitura);  // incluir uma nova leitura

        contador++;
    }

}

function receberNovasLeituras2() {
    setTimeout(() => {
        
        // esses "agora" etc até "momentos" serão desnecessários quando usarmos o backend futuramente
        var agora = new Date();
        var hora = agora.getHours();
        var minuto = agora.getMinutes();
        var segundo = agora.getSeconds();
        var momento = `${hora>9?'':'0'}${hora}:${minuto>9?'':'0'}${minuto}:${segundo>9?'':'0'}${segundo}`;

        // esse "novoRegistro" será recuperado do backend futuramente
        var novoRegistro = {
            momento: momento,
            leitura: sortear2()
        };

        rand = novoRegistro.leitura;
        
        // tirando e colocando valores no gráfico
        config2.data.labels.shift(); // apagar o primeiro
        config2.data.labels.push(novoRegistro.momento); // incluir um novo momento
        config2.data.datasets[0].data.shift();  // apagar o primeiro
        config2.data.datasets[0].data.push(novoRegistro.leitura); // incluir uma nova leitura

        // Atualiza o gráfico
        window.graficoLinha2.update();	

        // agendar a chamada da mesma função para daqui a 2 segundos
        receberNovasLeituras2();
    }, 5000); 
}

function plotarGrafico2() {
    // chamar os 7 últimos registros de leitura
    recuperarDadosIniciais2();

    // criação do gráfico na página
    var ctx2 = document.getElementById('graphDisco').getContext('2d');
    window.graficoLinha2 = new Chart(ctx2, config2);

    // função que agenda a recuperação da última leitura para daqui a 2 segundos
    receberNovasLeituras2();
}

// indicando que a função "plotarGrafico" será invocada assim que a página carregar
//  window.onload = () => {
//     plotarGrafico();
//  };