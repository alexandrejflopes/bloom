import React, { useEffect, useRef, useState } from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import "../assets/css/estufa.css";
// IMAGENS
import iconeCO2 from '../assets/images/estufa/co2.svg';
import fundoEstufa from '../assets/images/estufa/fundo_estufa.png';
import sensorDesligado from '../assets/images/estufa/sensor_branco.svg';
import sensorLigado from '../assets/images/estufa/sensor_verde.svg';
import tabuleiroImg from '../assets/images/estufa/tabuleiro_branco.svg';
import iconeTemperatura from '../assets/images/estufa/temperatura.svg';
import Aux from "../hoc/_Aux";
import { co2DireitaId, co2EsquerdaId, temperaturaDireitaId, temperaturaEsquerdaId } from '../variables/sensorsIds';
import { API_URL } from '../variables/urls';



function Dashboard (){
  const[email, setEmail] = useState(localStorage.getItem('email'));
  const[temperaturaEsquerda, setTemperaturaEsquerda] = useState('');
  const[co2Esquerda, setCo2Esquerda] = useState('');
  const[temperaturaDireita, setTemperaturaDireita] = useState('');
  const[co2Direita, setCo2Direita] = useState('');
  const [tabuleiros, setTabuleiros] = useState([]);

  //const invalidResponseError = "Invalid Response from API";
  const SLEEP_TIME = 10000;
  
  /*useEffect(() => {
    fetchLatestReadings();
    fetchTabuleiros();
    fetchTemperaturaEsquerda();
    fetchTemperaturaDireita();
    fetchCO2Esquerda();
    fetchCO2Direita();
  });*/

  useEffect(() => {
    fetchLatestReadings();
  }, []);

  useInterval(() => {
    fetchLatestReadings();
  }, SLEEP_TIME);


  const fetchLatestReadings = async () => {
    try {
      const fetchItem = await fetch(API_URL + '/sensor/all/latest-readings');
      console.log("fetched data");
      const data = await fetchItem.json();
      console.log(data);
      let leiturasHumidade = [];
      // set da temperatura e CO2
      for (let i = 0; i < data.length; i++){
        const item = data[i];

        if (item.id === temperaturaEsquerdaId){
          setTemperaturaEsquerda(item.value);
        }
        if (item.id === temperaturaDireitaId) {
          setTemperaturaDireita(item.value);
        }
        if (item.id === co2EsquerdaId) {
          setCo2Esquerda(item.value);
        }
        if (item.id === co2DireitaId) {
          setCo2Direita(item.value);
        }
        if (item.sensorType === "Humidity") {
          leiturasHumidade.push(item);
        }
      }
      construirTabuleiros(leiturasHumidade);
    }
    catch (error) {
      console.error(error);
    }
    //await sleep(SLEEP_TIME);
  }

/*
  const fetchTabuleiros = async () => {
    try{
      const fetchItem = await fetch(API_URL + '/sensor/humidity/latest-readings');
      console.log("fetched Tabuleiros");
      const item = await fetchItem.json();
      console.log(item);
      construirTabuleiros(item);
      await sleep(SLEEP_TIME);
    }
    catch(error){
      console.error(error);
    }
  };

  const fetchTemperaturaEsquerda = async () => {
    try{
      const fetchItem = await fetch(API_URL + '/sensor/0/readings/latest');
      console.log("fetched TemperaturaEsquerda");
      const item = await fetchItem.json();
      console.log(item);
      setTemperaturaEsquerda(item.value);
      await sleep(SLEEP_TIME);
    }
    catch (error) {
      console.error(error);
    }
  };

  const fetchTemperaturaDireita = async () => {
    try{
      const fetchItem = await fetch(API_URL + '/sensor/1/readings/latest');
      console.log("fetched TemperaturaDireita");
      const item = await fetchItem.json();
      console.log(item);
      setTemperaturaDireita(item.value);
      await sleep(SLEEP_TIME);
    }
    catch (error) {
      console.error(error);
    }
  };

  const fetchCO2Esquerda = async () => {
    try{
      const fetchItem = await fetch(API_URL + '/sensor/5/readings/latest');
      console.log("fetched CO2Esquerda");
      const item = await fetchItem.json();
      console.log(item);
      setCo2Esquerda(item.value);
      await sleep(SLEEP_TIME);
    }
    catch (error) {
      console.error(error);
    }
  };

  const fetchCO2Direita = async () => {
    try{
      const fetchItem = await fetch(API_URL + '/sensor/6/readings/latest');
      console.log("fetched CO2Direita");
      const item = await fetchItem.json();
      console.log(item);
      setCo2Direita(item.value);
      await sleep(SLEEP_TIME);
    }
    catch (error) {
      console.error(error);
    }
  };*/

  const construirTabuleiros = (item) => {
    setTabuleiros(
      item.map(i =>{
        return{
          id:i.id,
          ligado:true,
          value:i.value,
        }
      })
    )
  } 

  return (
      
    <Aux>
      <Row>
        <Col>
          <Card>
            <Card.Header>
              <Card.Title as="h5" className="titulo_estufa" id="titulo_estufa">Estado da estufa de {email}</Card.Title>
              <span className="d-block m-t-5">Humidade, temperatura e CO2</span>
            </Card.Header>
            <Card.Body>
              {/*<div style={{ backgroundImage: `url(${fundoEstufa})`, backgroundSize: "cover", backgroundRepeat: "no-repeat", height: "800px", width: "inherit" }}>*/}
                
                {
                temperaturaEsquerda && temperaturaEsquerda && co2Esquerda && co2Direita && tabuleiros.length > 0 ?
                  
                    /** linha para o layout todo, com a imagem da estufa como fundo */ 
                  <Row className="d-flex justify-content-between pb-5 pt-4" style={{ width: "inherit", height: "auto", backgroundImage: `url(${fundoEstufa})`, backgroundSize: "cover", backgroundRepeat: "no-repeat" }}>

                      {/** coluna para os sensores da esquerda */}
                    <Col lg="1" md="1" className="d-flex justify-content-center align-items-start flex-column" >
                      <div style={{ backgroundColor: "rgba(243, 243, 243, 0.699)", paddingLeft: "0px", paddingTop: "15px", paddingBottom: "15px", paddingRight: "0px", width: "110px" }}>
                        {/** temperatura */}
                        <div style={{ textAlign: "center" }} id="temperaturaEsquerda_div">
                          <img style={{ width: "80px", height: "auto" }} alt="iconTemperatura" src={iconeTemperatura}></img>
                          <div id="temperaturaEsquerda_valor" style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                            {temperaturaEsquerda} ºC
                          </div>
                        </div>
                        {/** co2 */}
                        <div style={{ textAlign: "center" }} id="co2Esquerda_div">
                          <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                          <div id="co2Esquerda_valor" style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                            {co2Esquerda} ppm
                              </div>
                        </div>
                      </div>
                    </Col>


                    <Col xl="8" lg="8" md="6" className="d-flex justify-content-center align-items-center flex-column" >
                      <Row className="d-flex justify-content-between align-items-center" >
                        { /** uma coluna para cada tabuleiro  */}
                        {tabuleiros.map((tabuleiro, index) => (
                          <Col key={tabuleiro.id} xl="4" lg="12" md="12" sm="12" xs="12" className="classe_teste d-flex justify-content-center align-items-center flex-column" >
                            {/** imagem do tabuleiro */}
                            <div style={{ textAlign: "center" }}>
                              <img style={{ width: "300px", height: "auto" }} alt="tabuleiro" src={tabuleiroImg}></img>
                            </div>
                            {/** rodapé com o valor da humidade */}
                            <div className="d-flex justify-content-center align-items-center rodape_humidade">
                              <div style={{ marginTop: "7px" }}>
                                <h3 style={{ fontWeight: "bold", color: "white", textAlign: "center" }} name="humidade_valor">
                                  <img style={{ width: "70px", height: "auto" }} alt="humidade" src={tabuleiro.ligado ? sensorLigado : sensorDesligado}></img> {tabuleiro.ligado ? tabuleiro.value + " %" : "---"}
                                </h3>
                              </div>
                            </div>
                          </Col>
                        ))}
                      </Row>
                    </Col>


                    {/** coluna para os sensores da direita */}
                    <Col lg="1" md="1" className="d-flex justify-content-center align-items-end flex-column"  >
                      <div style={{ backgroundColor: "rgba(243, 243, 243, 0.699)", paddingLeft: "0px", paddingTop: "15px", paddingBottom: "15px", paddingRight: "0px", width: "110px" }}>
                        {/** temperatura */}
                        <div style={{ textAlign: "center" }} id="temperaturaDireita_div">
                          <img style={{ width: "80px", height: "auto" }} alt="iconTemperatura" src={iconeTemperatura}></img>
                          <div id="temperaturaDireita_valor" style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                            {temperaturaDireita} ºC
                              </div>
                        </div>
                        {/** co2 */}
                        <div style={{ textAlign: "center" }} id="co2Direita_div">
                          <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                          <div id="co2Direita_valor" style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                            {co2Direita} ppm
                              </div>
                        </div>
                      </div>
                    </Col>
                  </Row>
                  
                  
                  : 
                  
                  <h3>A carregar...</h3>
                }

                
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Aux>
    
      
  );
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

export default Dashboard;