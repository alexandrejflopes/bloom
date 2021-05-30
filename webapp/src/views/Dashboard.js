import React from 'react';
import {Row, Col, Card, Button} from 'react-bootstrap';

import Aux from "../hoc/_Aux";
import "../assets/css/estufa.css"

class Dashboard extends React.Component {
    render() {

        return (
            <Aux>
                <Row>
                    <Col>
                        <Card>
                            <Card.Header>
                                <Card.Title as="h5">Estado da sua estufa</Card.Title>
                                <span className="d-block m-t-5">Humidade, temperatura e CO2</span>
                            </Card.Header>
                            <Card.Body>
                                {/* Versão estática */}
                                {/*<img className="estufa_dash" src={require('../assets/images/estufa/estufa.png')} />*/}
                                
                                <div className="estufa_div">
                                    <img alt="estufa imgs" src={require('../assets/images/estufa/fundo_estufa.png')} className="fundo_estufa"/>
                                    <div className="fundo_esquerda"></div>
                                    <img alt="temperature" src={require('../assets/images/estufa/temperatura.png')} className="temperatura_esquerda"/>
                                    <p className="valor_temp_esquerda">22ºC</p>
                                    <img alt="co2" src={require('../assets/images/estufa/co2.png')} className="c02_esquerda"/>
                                    <p className="valor_c02_esquerda">980 ppm</p>
                                    <img alt="tabuleiro img" src={require('../assets/images/estufa/tabuleiro_branco.png')} className="tabuleiro1"/>
                                    <div className="humidade_tab1_bg"></div>
                                    <img alt="tabuleiro2 img" src={require('../assets/images/estufa/tabuleiro_branco.png')} className="tabuleiro2"/>
                                    <div className="humidade_tab2_bg"></div>
                                    <img alt="tabuleiro3 img" src={require('../assets/images/estufa/tabuleiro_branco.png')} className="tabuleiro3"/>
                                    <div className="humidade_tab3_bg"></div>
                                    <div className="fundo_direita"></div>
                                    <img alt="temperature" src={require('../assets/images/estufa/temperatura.png')} className="temperatura_direita"/>
                                    <p className="valor_temp_direita">22ºC</p>
                                    <img alt="co2" src={require('../assets/images/estufa/co2.png')} className="c02_direita"/>
                                    <p className="valor_c02_direita">980 ppm</p>
                                </div>
                            </Card.Body>
                        </Card>
                        
                    </Col>
                </Row>
            </Aux>
        );
    }
}

export default Dashboard;