import React, {useState, useEffect} from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import "../assets/css/estufa.css";
import Aux from "../hoc/_Aux";

// IMAGENS
const fundoEstufa = require('../assets/images/estufa/fundo_estufa.png');
const iconeTemperatura = require('../assets/images/estufa/temperatura.svg');
const iconeCO2 = require('../assets/images/estufa/co2.svg');
const tabuleiroImg = require('../assets/images/estufa/tabuleiro_branco.svg');
const sensorLigado = require('../assets/images/estufa/sensor_verde.svg');
const sensorDesligado = require('../assets/images/estufa/sensor_branco.svg');

function Dashboard (){
  const[email, setEmail]=React.useState(localStorage.getItem('email'));
  const[temperaturaEsquerda, setTemperaturaEsquerda] = React.useState('');
  const[co2Esquerda, setCo2Esquerda] = React.useState('');
  const[temperaturaDireita, setTemperaturaDireita] = React.useState('');
  const[co2Direita, setCo2Direita] = React.useState('');
  const [tabuleirosTeste, setTabuleirosTeste] = React.useState([]);
  
  useEffect(() => {
    fetchTabuleiros();
    fetchTemperaturaEsquerda();
    fetchTemperaturaDireita();
    fetchCO2Esquerda();
    fetchCO2Direita();
  });

  const fetchTabuleiros = async () => {
    const fetchItem = await fetch('http://192.168.160.87:50060/sensor/humidity/latest-readings');
    const item = await fetchItem.json();
    construirTabuleiros(item);
  };

  const fetchTemperaturaEsquerda = async () => {
    const fetchItem = await fetch('http://192.168.160.87:50060/sensor/0/readings/latest');
    const item = await fetchItem.json();
    setTemperaturaEsquerda(item.value);
  };

  const fetchTemperaturaDireita = async () => {
    const fetchItem = await fetch('http://192.168.160.87:50060/sensor/1/readings/latest');
    const item = await fetchItem.json();
    setTemperaturaDireita(item.value);
  };

  const fetchCO2Esquerda = async () => {
    const fetchItem = await fetch('http://192.168.160.87:50060/sensor/5/readings/latest');
    const item = await fetchItem.json();
    setCo2Esquerda(item.value);
  };

  const fetchCO2Direita = async () => {
    const fetchItem = await fetch('http://192.168.160.87:50060/sensor/6/readings/latest');
    const item = await fetchItem.json();
    setCo2Direita(item.value);
  };

  const construirTabuleiros = (item) => {
    setTabuleirosTeste(
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
                
                {/** linha para o layout todo, com a imagem da estufa como fundo */}
                <Row className="d-flex justify-content-between pb-5 pt-4" style={{ width: "inherit", height: "auto", backgroundImage: `url(${fundoEstufa})`, backgroundSize: "cover", backgroundRepeat: "no-repeat" }}>
                  
                {/** coluna para os sensores da esquerda */}
                  <Col lg="1" md="1" className="d-flex justify-content-center align-items-start flex-column" >
                    <div style={{ backgroundColor: "rgba(243, 243, 243, 0.699)", paddingLeft: "0px", paddingTop: "15px", paddingBottom: "15px", paddingRight: "0px", width: "110px"}}>
                      {/** temperatura */}
                      <div style={{ textAlign: "center"}} >
                        <img style={{ width: "80px", height: "auto" }} alt="iconTemperatura" src={iconeTemperatura}></img>
                        <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                          {temperaturaEsquerda}ºC
                        </div>
                      </div>
                      {/** co2 */}
                      <div style={{ textAlign: "center" }} >
                        <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                        <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                          {co2Esquerda} ppm
                        </div>
                      </div>
                    </div>
                  </Col>


                  <Col xl="8" lg="8" md="6" className="d-flex justify-content-center align-items-center flex-column" >
                    <Row className="d-flex justify-content-between align-items-center" >
                      { /** uma coluna para cada tabuleiro  */ }
                        {tabuleirosTeste.map((tabuleiro, index) => (
                            <Col key={tabuleiro.id} xl="4" lg="12" md="12" sm="12" xs="12" className="d-flex justify-content-center align-items-center flex-column" >
                              {/** imagem do tabuleiro */}
                              <div style={{ textAlign: "center" }}>
                                <img style={{ width: "300px", height: "auto" }} alt="tabuleiro" src={tabuleiroImg}></img>
                              </div>
                              {/** rodapé com o valor da humidade */}
                              <div style={{ backgroundColor: "#58666B", width: "250px", height: "70px", borderRadius: "7px 7px 7px 7px", flexDirection: "column" }} className="d-flex justify-content-center align-items-center">
                                <div style={{ marginTop: "7px" }}>
                                  <h3 style={{ fontWeight: "bold", color: "white", textAlign: "center" }}>
                                    <img style={{ width: "70px", height: "auto" }} alt="humidade" src={tabuleiro.ligado ? sensorLigado : sensorDesligado}></img> {tabuleiro.ligado ? tabuleiro.value : "---"}
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
                      <div style={{ textAlign: "center" }} >
                        <img style={{ width: "80px", height: "auto" }} alt="iconTemperatura" src={iconeTemperatura}></img>
                        <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                          {temperaturaDireita}ºC
                        </div>
                      </div>
                    {/** co2 */}
                      <div style={{ textAlign: "center" }} >
                        <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                        <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                          {co2Direita} ppm
                        </div>
                      </div>
                    </div>
                  </Col>
                </Row>
              
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Aux>
    
      
  );
}

export default Dashboard;