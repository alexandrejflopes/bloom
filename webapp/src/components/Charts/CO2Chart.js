import React, { useEffect, useRef, useState } from 'react';
import NVD3Chart from 'react-nvd3';
import { timestampToDate } from '../../scripts/functions';
import { API_URL } from '../../variables/urls';
var d3 = require("d3");


// formatar dados para o gráfico
function formatCO2Data(co2Esquerda, co2Direita) {
  let esquerda = [];
  let direita = [];

  // extrair os dados relevantes para o gráfico para cada sensor
  for (let i = 0; i < co2Esquerda.length; i++) {
    let leitura = co2Esquerda[i];

    esquerda.push(
      {
        'x': timestampToDate(leitura.timestamp),
        'y': leitura.value
      }
    )
  }

  for (let i = 0; i < co2Direita.length; i++) {
    let leitura = co2Direita[i];

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
      key: 'CO2 Oeste',
      color: '#A389D4'
    },
    {
      values: direita,
      key: 'CO2 Este',
      color: '#04a9f5'
    },
  ];
}

function CO2Chart() {

  //const data = getDatum();

  const MAX_DATA_ELEMENTS = 5; // TODO: change
  const SLEEP_TIME = 10000;

  const [data, setData] = useState([]);
  
  useEffect(() => {
    fetchCO2();
  }, []);

  useInterval(() => {
    fetchCO2();
  }, SLEEP_TIME);


  const fetchCO2 = async () => {
    try{
      const fetchCO2Esquerda = await fetch(API_URL + '/sensor/5/readings/all/' + MAX_DATA_ELEMENTS/*, { timeout: 10000 }*/);
      console.log("fetched CO2Esquerda");
      let responseEsquerda = await fetchCO2Esquerda.json();

      const fetchCO2Direita = await fetch(API_URL + '/sensor/6/readings/all/' + MAX_DATA_ELEMENTS/*, { timeout: 10000 }*/);
      console.log("fetched CO2Direita");
      let responseDireita = await fetchCO2Direita.json();

      const data = formatCO2Data(responseEsquerda, responseDireita);

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
                  axisLabel: 'CO2 (ppm)',
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

export default CO2Chart;