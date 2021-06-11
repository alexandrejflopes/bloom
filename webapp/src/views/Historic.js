import React from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import CO2Chart from "../components/Charts/CO2Chart";
import HumidityChart from '../components/Charts/HumidityChart';
import TemperatureChart from "../components/Charts/TemperatureChart";
import Aux from "../hoc/_Aux";


function Historic() {


  return (
    <Aux>
      <Row>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Temperatura</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              <TemperatureChart />
            </Card.Body>
          </Card>
        </Col>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">CO2</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              <HumidityChart />
            </Card.Body>
          </Card>
        </Col>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Humidade</Card.Title>
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