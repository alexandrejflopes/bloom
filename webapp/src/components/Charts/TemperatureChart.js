import React, { useState } from 'react';
import NVD3Chart from 'react-nvd3';
import { API_URL } from '../variables/urls';


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
function formatTemperatureData(esquerda, direita){

}

function TemperatureChart() {

  //const data = getDatum();

  const [data, setData] = useState('');


  const fetchTemperatura = async () => {
    const fetchTempEsquerda = await fetch(API_URL + '/sensor/0/readings/latest');
    console.log("fetched TemperaturaEsquerda");
    const responseEsquerda = await fetchTempEsquerda.json();

    const fetchTempDireita = await fetch(API_URL + '/sensor/0/readings/latest');
    console.log("fetched TemperaturaEsquerda");
    const responseDireita = await fetchTempDireita.json();


    const data = formatData(responseEsquerda, responseDireita);

    setData(data);
  }

  const fetchTemperaturaDireita = async () => {
    const fetchItem = await fetch(API_URL + '/sensor/0/readings/latest');
    console.log("fetched TemperaturaEsquerda");
    const response = await fetchItem.json();
    const data = formatData(response);

    setData(data);
  }


  return (
    <div>
      {
        React.createElement(NVD3Chart, {
          xAxis: {
            tickFormat: function (d) { return d; },
            axisLabel: 'Di'
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
  )
}

export default TemperatureChart;