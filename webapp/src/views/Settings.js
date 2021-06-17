import React, { useEffect } from 'react';
import { Button, Card, Col, Form, Row, Tab, Tabs } from 'react-bootstrap';
import "../assets/css/settings.css";
import Aux from "../hoc/_Aux";
import { API_URL } from '../variables/urls';



function Settings() {
    const[email, setEmail]=React.useState(localStorage.getItem('email'));
    const[minTemp, setMinTemp] = React.useState("");
    const[maxTemp, setMaxTemp] = React.useState("");
    const[maxHum, setMaxHum] = React.useState("");
    const[minHum, setMinHum] = React.useState("");
    const[newMinTemp, setNewMinTemp] = React.useState("");
    const[newMaxTemp, setNewMaxTemp] = React.useState("");
    const[newMaxHum, setNewMaxHum] = React.useState("");
    const[newMinHum, setNewMinHum] = React.useState("");
    const[sucesso,setSucesso] = React.useState(false);
    const[limitesErro,setLimitesErro] = React.useState(false);
    const[tempErro,setTempErro] = React.useState(false);
    const[humErro,setHumErro] = React.useState(false);
    const[vazioErro,setVazioErro] = React.useState(false);

    useEffect(() => {
        fetchLimitesTemp();
        fetchLimitesHum();
      }, []); // array vazio para só fazer isto ao carregar a página

    const fetchLimitesTemp = async () => {
        const fetchItem = await fetch(API_URL + '/sensor-limits/temperature');
        const item = await fetchItem.json();
        setMinTemp(item.min);
        setMaxTemp(item.max);
    };

    const fetchLimitesHum = async () => {
        const fetchItem = await fetch(API_URL + '/sensor-limits/humidity');
        const item = await fetchItem.json();
        setMinHum(item.min);
        setMaxHum(item.max);
    };

    //em spring está feito com get mas acho q ficava melhor com post
    const setLimitesTemp = async () => {
        try{
          await fetch(API_URL + '/sensor-limits/temperature/new?min=' + newMinTemp + '&max=' + newMaxTemp);
          setMinTemp(newMinTemp);
          setMaxTemp(newMaxTemp);
        }
        catch(error){
          console.log(error);
        }
    };

    const setLimitesHum = async () => {
        try{
          await fetch(API_URL + '/sensor-limits/humidity/new?min=' + newMinHum + '&max=' + newMaxHum);
          //const item = await fetchItem.json(); // a resposta não é JSON
          //console.log(item);
          // update UI
          setMinHum(newMinHum);
          setMaxHum(newMaxHum);
        }
        catch(error){
          console.log(error);
        }
    };
    

    const guardarLimites = () => {

        if(newMinTemp>newMaxTemp || newMinHum>newMaxHum || newMaxTemp<newMinTemp || newMaxHum<newMinHum){
            setLimitesErro(true);
            setSucesso(false);
        }
        else if( newMaxTemp>30 || newMinTemp<0 ){
            setTempErro(true);
            setSucesso(false);
        }
        else if(newMaxHum>100 || newMinHum<0){
            setHumErro(true);
            setSucesso(false);
        }
        else if(newMaxHum === "" && newMinHum === "" && newMinTemp === "" && newMaxTemp === ""){
            setVazioErro(true);
            setSucesso(false);
        }
        else if((newMaxHum !== "" && newMinHum !== "") && (newMinTemp === "" || newMaxTemp === "")){ //se o utilizador apenas quiser definir a humidade
            setSucesso(true);
            setLimitesHum();
            fetchLimitesHum();
        }
        else if((newMinTemp !== "" && newMaxTemp !== "") && (newMaxHum === "" || newMinHum === "")){ //se o utilizador apenas quiser definir a temperatura
            setSucesso(true);
            setLimitesTemp();
            fetchLimitesTemp();
        }
        else{ //se o utilizador apenas quiser definir a temperatura e a humidade
            setSucesso(true);
            setLimitesTemp();
            setLimitesHum();
            fetchLimitesTemp();
            fetchLimitesHum();
        }
        
    }

    const getAlert = () => {
        if(sucesso){
            return(
                <div className="alert alert-success" role="alert" id="sucesso_alert">
                    Sucesso! Novos limites salvos.
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
                    A humidade deve encontrar-se entre 0% e 100%.
                </div>
            )
        } 
        else if(vazioErro){
            return(
                <div className="alert alert-danger" role="alert" id="hum_alert">
                    Os campos não podem ser todos vazios.
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
                                            <h6>Limites atuais (mínimo e máximo, respetivamente): {minHum}% e {maxHum}%</h6>
                                            <br></br>
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoHumidade">
                                                        <Form.Label>Novo Limite Mínimo (%)</Form.Label>
                                                        <Form.Control type="number" id="min_hum"  placeholder="Valor mínimo aceitável" value={newMinHum} onChange={e => setNewMinHum(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoHumidade">
                                                        <Form.Label>Novo Limite Máximo (%)</Form.Label>
                                                        <Form.Control type="number" id="max_hum" placeholder="Valor máximo aceitável" value={newMaxHum} onChange={e => setNewMaxHum(e.target.value)}  />
                                                    </Form.Group>
                                                </Col>
                                            </Tab>
                                            <Tab id="temperature_tab" eventKey="temperatura" title="Temperatura">
                                            <h6>Limites atuais (mínimo e máximo, respetivamente): {minTemp}ºC e {maxTemp}ºC</h6>
                                            <br></br>
                                                <Col md={6}>
                                                    <Form.Group controlId="limiteMinimoTemperatura">
                                                        <Form.Label>Novo Limite Mínimo (ºC)</Form.Label>
                                                        <Form.Control type="number" id="min_temp" placeholder="Valor mínimo aceitável" value={newMinTemp} onChange={e => setNewMinTemp(e.target.value)} />
                                                    </Form.Group>
                                                    <Form.Group controlId="limiteMaximoTemperatura">
                                                        <Form.Label>Novo Limite Máximo (ºC)</Form.Label>
                                                        <Form.Control type="number" id="max_temp" placeholder="Valor máximo aceitável" value={newMaxTemp} onChange={e => setNewMaxTemp(e.target.value)}  />
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