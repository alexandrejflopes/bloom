import React, { useEffect, useState } from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import NotificationAlert from "react-notification-alert";
import SockJsClient from 'react-stomp';
import CO2Chart from "../components/Charts/CO2Chart";
import HumidityChart from '../components/Charts/HumidityChart';
import TemperatureChart from "../components/Charts/TemperatureChart";
import Aux from "../hoc/_Aux";
import { API_URL, SOCKET_URL } from '../variables/urls';


function Historic() {

  const [minTemp, setMinTemp] = useState(undefined);
  const [maxTemp, setMaxTemp] = useState(undefined);
  const [maxHum, setMaxHum] = useState(undefined);
  const [minHum, setMinHum] = useState(undefined);

  useEffect(() => {
    fetchLimitesTemp();
    fetchLimitesHum();
    // TODO: just to test
    //setInterval(function () { showAlarm("Alarme!"); /*console.log("Toast!");*/ }, 1000);
  }, []); // array vazio para só fazer isto ao carregar a página

  // alarms
  const notificationAlertRef = React.useRef(null);
  const showAlarm = (message, place = "tr", type = "danger") => {

    /**
     * place:
     *  - tl
     *  - tc
     *  - tr
     *  - bl
     *  - bc
     *  - br
     * colors:
     *  - primary
     *  - success
     *  - danger
     *  - warning
     *  - info
     */

    let options = {
      place: place,
      message: (
        <div>
          <div>
            {message}
          </div>
        </div>
      ),
      type: type,
      //icon: "tim-icons icon-bell-55",
      autoDismiss: 15,
    };
    notificationAlertRef.current.notificationAlert(options);
  };

  let onConnected = () => {
    console.log("Alarms Socket connected!!")
  }

  let onMessageReceived = (msg) => {
    console.log('New Message Received:', msg);
    showAlarm(msg);
  }

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


  return (
    <Aux>
      <div className="react-notification-alert-container">
        <NotificationAlert ref={notificationAlertRef} />
      </div>
      <SockJsClient
        url={SOCKET_URL}
        topics={['/topic/alarm']}
        onConnect={onConnected}
        onDisconnect={console.log("Alarms Socket disconnected!")}
        onMessage={msg => onMessageReceived(msg)}
        debug={false}
      />
      <Row>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Temperatura</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              {minTemp && maxTemp ? <TemperatureChart minValue={minTemp} maxValue={maxTemp} /> : "LOADING..."}
            </Card.Body>
          </Card>
        </Col>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Humidade</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              {minHum && maxHum ? <HumidityChart minValue={minHum} maxValue={maxHum} /> : "LOADING..."}
            </Card.Body>
          </Card>
        </Col>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Dióxido de Carbono (CO<sub>2</sub>)</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              <CO2Chart />
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Aux>
  );
}

export default Historic;