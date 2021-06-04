import React, { useEffect } from 'react';
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
import { API_URL } from '../variables/urls';



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
    const fetchItem = await fetch(API_URL + '/sensor/humidity/latest-readings');
    const item = await fetchItem.json();
    construirTabuleiros(item);
  };

  const fetchTemperaturaEsquerda = async () => {
    const fetchItem = await fetch(API_URL + '/sensor/0/readings/latest');
    const item = await fetchItem.json();
    setTemperaturaEsquerda(item.value);
  };

  const fetchTemperaturaDireita = async () => {
    const fetchItem = await fetch(API_URL + '/sensor/1/readings/latest');
    const item = await fetchItem.json();
    setTemperaturaDireita(item.value);
  };

  const fetchCO2Esquerda = async () => {
    const fetchItem = await fetch(API_URL + '/sensor/5/readings/latest');
    const item = await fetchItem.json();
    setCo2Esquerda(item.value);
  };

  const fetchCO2Direita = async () => {
    const fetchItem = await fetch(API_URL + '/sensor/6/readings/latest');
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
                
                {
                email && temperaturaEsquerda && temperaturaEsquerda && co2Esquerda && co2Direita && tabuleirosTeste.length > 0 ?
                  
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
                        {tabuleirosTeste.map((tabuleiro, index) => (
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

export default Dashboard;