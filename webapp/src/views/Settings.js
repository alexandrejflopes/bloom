import React from 'react';
import {Row, Col, Card, Form, Button, InputGroup, FormControl, DropdownButton, Dropdown, Tab, Tabs} from 'react-bootstrap';

import Aux from "../hoc/_Aux";
import "../assets/css/settings.css"

class Settings extends React.Component {
    render() {
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
                                        <h5>Modo</h5>
                                        <hr/>
                                        <Form inline>
                                            <Form.Group className="mb-2">
                                                <Form.Label>Controlo de ambiente:</Form.Label>
                                            </Form.Group>
                                            <Form.Group className="mb-2 mr-5 modo_dropdown">
                                                <Form.Group controlId="exampleForm.ControlSelect1">
                                                    <Form.Control as="select">
                                                        <option>Automático</option>
                                                        <option>Manual</option>
                                                    </Form.Control>
                                                </Form.Group>
                                            </Form.Group>
                                        </Form>
                                        <div className="parametro_tabs">
                                            <h5>Limites aceitáveis</h5>
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
                                                <Tab eventKey="co2" title="CO2">
                                                    <Col md={6}>
                                                        <Form.Group controlId="limiteMinimoCo2">
                                                            <Form.Label>Limite Mínimo</Form.Label>
                                                            <Form.Control type="text" placeholder="Valor mínimo aceitável" />
                                                        </Form.Group>
                                                        <Form.Group controlId="limiteMaximoCo2">
                                                            <Form.Label>Limite Máximo</Form.Label>
                                                            <Form.Control type="text" placeholder="Valor máximo aceitável" />
                                                        </Form.Group>
                                                    </Col>
                                                </Tab>
                                            </Tabs>
                                        </div>
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
}

export default Settings;