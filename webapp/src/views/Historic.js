import React from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import TemperatureChart from "../components/Charts/TemperatureChart";
import Aux from "../hoc/_Aux";


function Historic() {


  
  return (
    <Aux>
      <Row>
        <Col sm={12}>
          <Card>
            <Card.Header>
              <Card.Title as="h5">Estatísticas</Card.Title>
            </Card.Header>
            <Card.Body className="text-center">
              <TemperatureChart />
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Aux>
  );
}

export default Historic;