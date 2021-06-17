import React, { useEffect, useRef, useState } from 'react';
import NVD3Chart from 'react-nvd3';
import { timestampToDate } from '../../scripts/functions';
import { API_URL } from '../../variables/urls';
var d3 = require("d3");




// formatar dados para o gráfico
function formatHumidityData(humidadeEsquerda, humidadeCentro, humidadeDireita, min, max) {
  let esquerda = [];
  let centro = [];
  let direita = [];
  let limiteSuperior = [];
  let limiteInferior = [];

  const lengths = [humidadeEsquerda.length, humidadeCentro.length, humidadeDireita.length];
  const maxLength = Math.max(...lengths)
  let sideToUse = humidadeEsquerda.length === maxLength ? "esquerda" : (
    humidadeCentro.length === maxLength ? "centro" : "direita"
  );


  console.log("maxLength -> ", maxLength)

  // linhas para os limites superior e inferior
  /*limiteInferior = Array(maxLength).fill(
    {
      //'x': timestampToDate(leitura.timestamp),
      'y': parseFloat(min)
    }
  );
  limiteSuperior = Array(maxLength).fill(
    {
      //'x': timestampToDate(leitura.timestamp),
      'y': parseFloat(max)
    }
  );*/

  


  // extrair os dados relevantes para o gráfico para cada sensor
  for (let i = 0; i < humidadeEsquerda.length; i++) {
    let leitura = humidadeEsquerda[i];

    esquerda.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )

    if(sideToUse==="esquerda"){
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

  for (let i = 0; i < humidadeCentro.length; i++) {
    let leitura = humidadeCentro[i];

    centro.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )

    if (sideToUse === "centro") {
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

  for (let i = 0; i < humidadeDireita.length; i++) {
    let leitura = humidadeDireita[i];

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

  console.log("limiteInferior -> ", limiteInferior)
  console.log("limiteSuperior -> ", limiteSuperior)

  return [
    {
      values: esquerda,
      key: 'Humidade Oeste',
      color: '#A389D4'
    },
    {
      values: centro,
      key: 'Humidade Centro',
      color: '#04a9f5'
    },
    {
      values: direita,
      key: 'Humidade Este',
      color: '#1de9b6'
    },
    // limites
    {
      values: limiteInferior,
      color: '#FF8C00'
    },
    {
      values: limiteSuperior,
      color: '#DC143C'
    },
  ];
}

function HumidityChart({minValue, maxValue}) {

  //const data = getDatum();

  const MAX_DATA_ELEMENTS = 50; 
  const SLEEP_TIME = 10000;

  const [data, setData] = useState([]);

  useEffect(() => {
    fetchHumidade();
  }, []);

  useInterval(() => {
    fetchHumidade();
  }, SLEEP_TIME);


  const fetchHumidade = async () => {
    try{
      const fetchHumidadeEsquerda = await fetch(API_URL + '/sensor/2/readings/all/' + MAX_DATA_ELEMENTS);
      console.log("fetched HumidadeEsquerda");
      const responseEsquerda = await fetchHumidadeEsquerda.json();

      const fetchHumidadeCentro = await fetch(API_URL + '/sensor/3/readings/all/' + MAX_DATA_ELEMENTS);
      console.log("fetched HumidadeCentro");
      const responseCentro = await fetchHumidadeCentro.json();

      const fetchHumidadeDireita = await fetch(API_URL + '/sensor/4/readings/all/' + MAX_DATA_ELEMENTS);
      console.log("fetched HumidadeDireita");
      const responseDireita = await fetchHumidadeDireita.json();

      const data = formatHumidityData(responseEsquerda, responseCentro, responseDireita, minValue, maxValue);

      console.log("data")
      console.log(data)

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
        data.length !== 0 ?
          <div>
            {
              React.createElement(NVD3Chart, {
                xAxis: {
                  tickFormat: function (d) { return d3.time.format('%H:%M:%S')(new Date(d)); },
                  axisLabel: 'Hora'
                },
                yAxis: {
                  axisLabel: 'Humidade (%)',
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

export default HumidityChart;