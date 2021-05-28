import React from 'react';
import {Row, Col, Card} from 'react-bootstrap';

import Aux from "../../hoc/_Aux";
import "../../assets/css/style.css"

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
                                <img className="estufa_dash" src={require('../../assets/images/estufa/estufa.png')} />
                            </Card.Body>
                            <p>quando a página de settings estiver feita, vão aparecer aqui botões caso o modo escolhido seja manual</p>
                        </Card>
                        
                    </Col>
                </Row>
            </Aux>
        );
    }
}

export default Dashboard;