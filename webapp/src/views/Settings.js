import React from 'react';
import { Button, Card, Col, Form, Row, Tab, Tabs } from 'react-bootstrap';
import "../assets/css/settings.css";
import Aux from "../hoc/_Aux";


function Settings() {
    const[email, setEmail]=React.useState(localStorage.getItem('email'));
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
                                                        <Form.Control type="text" placeholder="Valor mínimo aceitável" />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoHumidade">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="text" placeholder="Valor máximo aceitável" />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                            <Tab eventKey="temperatura" title="Temperatura">
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoTemperatura">
                                                        <Form.Label>Limite Mínimo</Form.Label>
                                                        <Form.Control type="text" placeholder="Valor mínimo aceitável" />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoTemperatura">
                                                        <Form.Label>Limite Máximo</Form.Label>
                                                        <Form.Control type="text" placeholder="Valor máximo aceitável" />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                        </Tabs>
                                    <Button variant="outline-dark" className="guardar_bt">Guardar</Button>
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