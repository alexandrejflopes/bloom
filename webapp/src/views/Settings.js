import React from 'react';
import {Row, Col, Card, Form, Button, InputGroup, FormControl, DropdownButton, Dropdown, Tab, Tabs} from 'react-bootstrap';

import Aux from "../hoc/_Aux";
import "../assets/css/settings.css"

function Settings() {
    const[email, setEmail]=React.useState(localStorage.getItem('email'));
    const[minTemp, setMinTemp] = React.useState(0);
    const[maxTemp, setMaxTemp] = React.useState(30);
    const[maxHum, setMaxHum] = React.useState(700);
    const[minHum, setMinHum] = React.useState(0);

    const guardarLimites = () => {

        if(minTemp>maxTemp || minHum>maxHum){
            alert("Os limites mínimos não podem ser superiores aos limites máximos!")
        }
        else if(maxTemp<minTemp || maxHum<minHum){
            alert("Os limites máximos não podem ser inferiores aos limites máximos!")
        }
        else if( maxTemp>30 || minTemp<0 ){
            alert("A temperatura deve encontrar-se entre 0ºC e 30ºC.")
        }
        else if(maxHum>700 || minHum<0){
            alert("A humidade deve encontrar-se entre 0ppm e 700ppm.")
        }
        else{
            alert("Sucesso! Novos limites de humidade e temperatura salvos.")
        }
        
    }

    return (
        <Aux>
            <Row>
                <Col>
                    <Card>
                        <Card.Header>
                            <Card.Title as="h5">Configurações</Card.Title>
                        </Card.Header>
                        <Card.Body>
                            <Row>
                                <Col>
                                        <h6>Estufa de {email}</h6>
                                        <hr/>
                                        <Tabs defaultActiveKey="humidade" >
                                            <Tab eventKey="humidade" title="Humidade">
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoHumidade">
                                                        <Form.Label>Limite Mínimo</Form.Label>
                                                        <Form.Control type="number"  placeholder="Valor mínimo aceitável" value={minHum} onChange={e => setMinHum(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoHumidade">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="number" placeholder="Valor máximo aceitável" value={maxHum} onChange={e => setMaxHum(e.target.value)}  />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                            <Tab eventKey="temperatura" title="Temperatura">
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoTemperatura">
                                                        <Form.Label>Limite Mínimo</Form.Label>
                                                        <Form.Control type="number" placeholder="Valor mínimo aceitável" value={minTemp} onChange={e => setMinTemp(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoTemperatura">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="number" placeholder="Valor máximo aceitável" value={maxTemp} onChange={e => setMaxTemp(e.target.value)}  />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                        </Tabs>
                                    <Button variant="outline-dark" className="guardar_bt" onClick={() => guardarLimites()}>Guardar</Button>
                                </Col>
                            </Row>
                            
                        </Card.Body>
                    </Card>
                    
                </Col>
            </Row>
        </Aux>
    );
}

export default Settings;