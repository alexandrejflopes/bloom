import React from 'react';
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



class Dashboard extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      temperaturaEsquerda: "22 ºC", // "---"
      co2Esquerda: "980 ppm",
      temperaturaDireita: "22 ºC",
      co2Direita: "980 ppm",
      tabuleiros: {
        1: {
          ligado: true,
          valor: "60%"
        },
        2: {
          ligado: false,
          valor: "---"
        },
        3: {
          ligado: false,
          valor: "---"
        }
      }
    }
  }


  componentDidMount(){
    
  }



  render() {
      return (
          
        <Aux>
          {/* Versão estática (dentro do card body) */}
          {/*<img className="estufa_dash" src={require('../assets/images/estufa/estufa.png')} />*/}
          {/*<Row>
            <Col>
              <Card>
                <Card.Header>
                  <Card.Title as="h5">Estado da sua estufa</Card.Title>
                  <span className="d-block m-t-5">Humidade, temperatura e CO2</span>
                </Card.Header>
                <Card.Body>
                  <div className="estufa_div">
                    <img alt="estufa imgs" src={fundoEstufa} className="fundo_estufa" />
                    <div className="fundo_esquerda"></div>
                    <img alt="temperature" src={iconeTemperatura} className="temperatura_esquerda" />
                    <p className="valor_temp_esquerda">22ºC</p>
                    <img alt="co2" src={iconeCO2} className="c02_esquerda" />
                    <p className="valor_c02_esquerda">980 ppm</p>
                    <img alt="tabuleiro img" src={tabuleiroImg} className="tabuleiro1" />
                    <div className="humidade_tab1_bg"></div>
                    <img alt="tabuleiro2 img" src={tabuleiroImg} className="tabuleiro2" />
                    <div className="humidade_tab2_bg"></div>
                    <img alt="tabuleiro3 img" src={tabuleiroImg} className="tabuleiro3" />
                    <div className="humidade_tab3_bg"></div>
                    <div className="fundo_direita"></div>
                    <img alt="temperature" src={iconeTemperatura} className="temperatura_direita" />
                    <p className="valor_temp_direita">22ºC</p>
                    <img alt="co2" src={iconeCO2} className="c02_direita" />
                    <p className="valor_c02_direita">980 ppm</p>
                  </div>
                </Card.Body>
              </Card>

            </Col>
          </Row>*/}

          
          <Row>
            <Col>
              <Card>
                <Card.Header>
                  <Card.Title as="h5">Estado da sua estufa</Card.Title>
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
                              {this.state.temperaturaEsquerda}
                            </div>
                          </div>
                          {/** co2 */}
                          <div style={{ textAlign: "center" }} >
                            <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                            <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                              {this.state.co2Esquerda}
                            </div>
                          </div>
                        </div>
                      </Col>


                      <Col xl="8" lg="8" md="6" className="d-flex justify-content-center align-items-center flex-column" >
                        <Row className="d-flex justify-content-between align-items-center" >
                          { /** uma coluna para cada tabuleiro  */
                            Object.keys(this.state.tabuleiros).map((tabuleiroId) => {
                              const tabuleiro = this.state.tabuleiros[tabuleiroId];
                              return(
                                <Col key={tabuleiroId} xl="4" lg="12" md="12" sm="12" xs="12" className="d-flex justify-content-center align-items-center flex-column" >
                                  {/** imagem do tabuleiro */}
                                  <div style={{ textAlign: "center" }}>
                                    <img style={{ width: "300px", height: "auto" }} alt="tabuleiro" src={tabuleiroImg}></img>
                                  </div>
                                  {/** rodapé com o valor da humidade */}
                                  <div style={{ backgroundColor: "#58666B", width: "250px", height: "70px", borderRadius: "7px 7px 7px 7px", flexDirection: "column" }} className="d-flex justify-content-center align-items-center">
                                    <div style={{ marginTop: "7px" }}>
                                      <h3 style={{ fontWeight: "bold", color: "white", textAlign: "center" }}>
                                        <img style={{ width: "70px", height: "auto" }} alt="humidade" src={tabuleiro.ligado ? sensorLigado : sensorDesligado}></img> {tabuleiro.ligado ? this.state.tabuleiros[tabuleiroId].valor : "---"}
                                      </h3>
                                    </div>
                                  </div>
                                </Col>
                              )
                            })
                          }                          
                        </Row>
                      </Col>
                      
                      
                    {/** coluna para os sensores da direita */}
                      <Col lg="1" md="1" className="d-flex justify-content-center align-items-end flex-column"  >
                        <div style={{ backgroundColor: "rgba(243, 243, 243, 0.699)", paddingLeft: "0px", paddingTop: "15px", paddingBottom: "15px", paddingRight: "0px", width: "110px" }}>
                        {/** temperatura */}
                          <div style={{ textAlign: "center" }} >
                            <img style={{ width: "80px", height: "auto" }} alt="iconTemperatura" src={iconeTemperatura}></img>
                            <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                              {this.state.temperaturaDireita}
                            </div>
                          </div>
                        {/** co2 */}
                          <div style={{ textAlign: "center" }} >
                            <img style={{ width: "80px", height: "auto" }} alt="iconCO2" src={iconeCO2}></img>
                            <div style={{ fontWeight: "bold", color: "black", marginTop: "5px", marginBottom: "5px", textAlign: "center" }}>
                              {this.state.co2Direita}
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
}

export default Dashboard;