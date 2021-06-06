import React from 'react';
import { Button, Card, Col, Form, Row, Tab, Tabs } from 'react-bootstrap';
import "../assets/css/settings.css";
import Aux from "../hoc/_Aux";


function Settings() {
    const[email, setEmail]=React.useState(localStorage.getItem('email'));
    const[minTemp, setMinTemp] = React.useState(0);
    const[maxTemp, setMaxTemp] = React.useState(30);
    const[maxHum, setMaxHum] = React.useState(700);
    const[minHum, setMinHum] = React.useState(0);
    const[sucesso,setSucesso] = React.useState(false);
    const[limitesErro,setLimitesErro] = React.useState(false);
    const[tempErro,setTempErro] = React.useState(false);
    const[humErro,setHumErro] = React.useState(false);



    const guardarLimites = () => {

        if(minTemp>maxTemp || minHum>maxHum || maxTemp<minTemp || maxHum<minHum){
            //alert("Os limites mínimos não podem ser superiores aos limites máximos!")
            setLimitesErro(true);
            setSucesso(false);
        }
        else if( maxTemp>30 || minTemp<0 ){
            //alert("A temperatura deve encontrar-se entre 0ºC e 30ºC.")
            setTempErro(true);
            setSucesso(false);
        }
        else if(maxHum>700 || minHum<0){
            //alert("A humidade deve encontrar-se entre 0ppm e 700ppm.")
            setHumErro(true);
            setSucesso(false);
        }
        else{
            //alert("Sucesso! Novos limites de humidade e temperatura salvos.")
            setSucesso(true);
        }
        
    }

    const getAlert = () => {
        if(sucesso){
            return(
                <div className="alert alert-success" role="alert" id="sucesso_alert">
                    Sucesso! Novos limites de humidade e temperatura salvos.
                </div>
            )
        }
        else if(limitesErro){
            return(
                <div className="alert alert-danger" role="alert" id="limites_alert">
                    Os limites mínimos não podem ser superiores aos limites máximos!
                </div>
            )
        } 
        else if(tempErro){
            return(
                <div className="alert alert-danger" role="alert" id="temp_alert">
                    A temperatura deve encontrar-se entre 0ºC e 30ºC.
                </div>
            )
        }        
        else if(humErro){
            return(
                <div className="alert alert-danger" role="alert" id="hum_alert">
                    A humidade deve encontrar-se entre 0ppm e 700ppm.
                </div>
            )
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
                                                        <Form.Control type="number" id="min_hum"  placeholder="Valor mínimo aceitável" value={minHum} onChange={e => setMinHum(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoHumidade">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="number" id="max_hum" placeholder="Valor máximo aceitável" value={maxHum} onChange={e => setMaxHum(e.target.value)}  />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                            <Tab id="temperature_tab" eventKey="temperatura" title="Temperatura">
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoTemperatura">
                                                        <Form.Label>Limite Mínimo</Form.Label>
                                                        <Form.Control type="number" id="min_temp" placeholder="Valor mínimo aceitável" value={minTemp} onChange={e => setMinTemp(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoTemperatura">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="number" id="max_temp" placeholder="Valor máximo aceitável" value={maxTemp} onChange={e => setMaxTemp(e.target.value)}  />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                        </Tabs>
                                        { 
                                            getAlert()
                                        }
                                    <Button variant="outline-dark" className="guardar_bt" id="guardar_bt" onClick={() => guardarLimites()}>Guardar</Button>
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