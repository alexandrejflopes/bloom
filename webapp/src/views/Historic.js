import React from 'react';
import {Row, Col, Card} from 'react-bootstrap';

import Aux from "../hoc/_Aux";
import LineChart from "../components/Charts/Nvd3Chart/LineChart";
import BarDiscreteChart from "../components/Charts/Nvd3Chart/BarDiscreteChart";
import MultiBarChart from "../components/Charts/Nvd3Chart/MultiBarChart";
import PieBasicChart from "../components/Charts/Nvd3Chart/PieBasicChart";
import PieDonutChart from "../components/Charts/Nvd3Chart/PieDonutChart";

class Historic extends React.Component {

    render() {
        return (
            <Aux>
                <Row>
                    <Col sm={12}>
                        <Card>
                            <Card.Header>
                                <Card.Title as="h5">Estatísticas</Card.Title>
                            </Card.Header>
                            <Card.Body className="text-center">
                                <PieDonutChart/>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col md={6}>
                        <Card>
                            <Card.Header>
                                <Card.Title as="h5">Estatísticas</Card.Title>
                            </Card.Header>
                            <Card.Body>
                                <LineChart/>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col md={6}>
                        <Card>
                            <Card.Header>
                                <Card.Title as="h5">Estatísticas</Card.Title>
                            </Card.Header>
                            <Card.Body>
                                <BarDiscreteChart/>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Aux>
        );
    }
}

export default Historic;