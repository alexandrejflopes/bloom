import React, { useEffect, useRef, useState } from 'react';
import NVD3Chart from 'react-nvd3';
import { timestampToDate } from '../../scripts/functions';
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
function formatTemperatureData(tempEsquerda, tempDireita, min, max){
  let esquerda = [];
  let direita = [];
  let limiteSuperior = [];
  let limiteInferior = [];

  const lengths = [tempEsquerda.length, tempDireita.length];
  const maxLength = Math.max(...lengths); // maior numero de leituras
  let sideToUse = tempEsquerda.length === maxLength ? "esquerda" : "direita";

  //console.log("maxLength temp -> ", maxLength)

  // extrair os dados relevantes para o gráfico para cada sensor
  for(let i=0; i < tempEsquerda.length; i++){
    let leitura = tempEsquerda[i];

    esquerda.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )

    if (sideToUse === "esquerda") {
      limiteInferior.push(
        {
          'x': timestampToDate(leitura.timestamp),
          'y': parseFloat(min)
        }
      )

      limiteSuperior.push(
        {
          'x': timestampToDate(leitura.timestamp),
          'y': parseFloat(max)
        }
      )
    }
  }

  for (let i = 0; i < tempDireita.length; i++) {
    let leitura = tempDireita[i];

    direita.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )

    if (sideToUse === "direita") {
      limiteInferior.push(
        {
          'x': timestampToDate(leitura.timestamp),
          'y': parseFloat(min)
        }
      )

      limiteSuperior.push(
        {
          'x': timestampToDate(leitura.timestamp),
          'y': parseFloat(max)
        }
      )
    }
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
    // limites
    {
      values: limiteInferior,
      key: 'Limite Inferior',
      color: '#DC143C'
    },
    {
      values: limiteSuperior,
      key: 'Limite Superior',
      color: '#DC143C'
    },
  ];
}

function TemperatureChart({ minValue, maxValue }) {

  //const data = getDatum();

  const MAX_DATA_ELEMENTS = 50; 
  const SLEEP_TIME = 10000;

  const [data, setData] = useState([]);

  useEffect(() => {
    fetchTemperatura();
  }, []);


  useInterval(() => {
    fetchTemperatura();
  }, SLEEP_TIME);


  const fetchTemperatura = async () => {
    try{
      const fetchTempEsquerda = await fetch(API_URL + '/sensor/0/readings/all/' + MAX_DATA_ELEMENTS/*, { timeout: 10000 }*/);
      //console.log("fetched TemperaturaEsquerda");
      let responseEsquerda = await fetchTempEsquerda.json();
      //console.log(responseEsquerda)
      // TODO: isto deverá vir já limitado da API depois
      //responseEsquerda = limitArrayToFirstX(responseEsquerda,100);
      //console.log("responseEsquerda filtered")
      //console.log(responseEsquerda)
      const fetchTempDireita = await fetch(API_URL + '/sensor/1/readings/all/' + MAX_DATA_ELEMENTS/*, { timeout: 10000 }*/);
      //console.log("fetched TemperaturaDireita");
      let responseDireita = await fetchTempDireita.json();
      //responseDireita = limitArrayToFirstX(responseDireita, 100);

      const data = formatTemperatureData(responseEsquerda, responseDireita, minValue, maxValue);

      //console.log("data")
      //console.log(data)

      setData(data);
    }
    catch (error) {
      console.error(error);
    }

    //await sleep(SLEEP_TIME);
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

          : "LOADING..."
    }
    </>
  )
}


function useInterval(callback, delay) {
  const savedCallback = useRef();

  // Remember the latest function.
  useEffect(() => {
    savedCallback.current = callback;
  }, [callback]);

  // Set up the interval.
  useEffect(() => {
    function tick() {
      savedCallback.current();
    }
    if (delay !== null) {
      let id = setInterval(tick, delay);
      return () => clearInterval(id);
    }
  }, [delay]);
}

export default TemperatureChart;