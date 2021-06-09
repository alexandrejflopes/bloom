import React, { useEffect, useState } from 'react';
import NVD3Chart from 'react-nvd3';
import { limitArrayToFirstX, timestampToDate } from '../../scripts/functions';
import { API_URL } from '../../variables/urls';
var d3 = require("d3");

function getDatum() {
  var sin = [],
    sin2 = [],
    cos = [];
  for (var i = 0; i < 100; i++) {
    sin.push({
      'x': i,
      'y': Math.sin(i / 10)
    });
    sin2.push({
      'x': i,
      'y': Math.sin(i / 10) * 0.25 + 0.5
    });
    cos.push({
      'x': i,
      'y': .5 * Math.cos(i / 10)
    });
  }
  return [
    {
      values: sin,
      key: 'Sine Wave',
      color: '#A389D4'
    },
    {
      values: cos,
      key: 'Cosine Wave',
      color: '#04a9f5'
    },
    {
      values: sin2,
      key: 'Another sine wave',
      color: '#1de9b6',
      area: true
    }
  ];
}


// formatar dados para o gráfico
function formatTemperatureData(tempEsquerda, tempDireita){
  let esquerda = [];
  let direita = [];

  // extrair os dados relevantes para o gráfico para cada sensor
  for(let i=0; i < tempEsquerda.length; i++){
    let leitura = tempEsquerda[i];

    esquerda.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )
  }

  for (let i = 0; i < tempDireita.length; i++) {
    let leitura = tempDireita[i];

    direita.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )
  }

  return [
    {
      values: esquerda,
      key: 'Temperatura Oeste',
      color: '#A389D4'
    },
    {
      values: direita,
      key: 'Temperatura Este',
      color: '#04a9f5'
    },
  ];
}

function TemperatureChart() {

  //const data = getDatum();

  const [data, setData] = useState([]);

  useEffect(() => {
    fetchTemperatura();
  });


  const fetchTemperatura = async () => {
    const fetchTempEsquerda = await fetch(API_URL + '/sensor/0/readings/all');
    console.log("fetched TemperaturaEsquerda");
    let responseEsquerda = await fetchTempEsquerda.json();
    //console.log(responseEsquerda)
    // limitar os resultados aos últimos 100
    // TODO: isto deverá vir já limitado da API depois
    responseEsquerda = limitArrayToFirstX(responseEsquerda,100);
    //console.log("responseEsquerda filtered")
    //console.log(responseEsquerda)
    const fetchTempDireita = await fetch(API_URL + '/sensor/0/readings/all');
    console.log("fetched TemperaturaEsquerda");
    let responseDireita = await fetchTempDireita.json();
    responseDireita = limitArrayToFirstX(responseDireita, 100);

    const data = formatTemperatureData(responseEsquerda, responseDireita);

    console.log("data")
    console.log(data)

    setData(data);
  }




  return (
    <>
    {
      data.length!==0 ? 
          <div>
            {
              React.createElement(NVD3Chart, {
                xAxis: {
                  tickFormat: function (d) { return d3.time.format('%H:%M:%S')(new Date(d)); },
                  axisLabel: 'Hora'
                },
                yAxis: {
                  axisLabel: 'Temperatura (ºC)',
                  tickFormat: function (d) { return parseFloat(d).toFixed(2); }
                },
                type: 'lineChart',
                datum: data,
                x: 'x',
                y: 'y',
                height: 300,
                renderEnd: function () {
                  console.log('renderEnd');
                }
              })
            }
          </div>

          : "LOADING"
    }
    </>
  )
}

export default TemperatureChart;