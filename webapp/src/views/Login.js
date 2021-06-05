import React from 'react';
import { Link } from 'react-router-dom';
import Breadcrumb from '../App/layout/AdminLayout/Breadcrumb';
import "../assets/css/login.css";
//import '../../assets/scss/style.scss';
import '../assets/css/style.css';
import logoPreto from '../assets/images/logo_preto.svg';
import Aux from "../hoc/_Aux";


function Login(){
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const checkUser = () => {
        localStorage.setItem('email', email);
    }
    return(
        <Aux>
            <Breadcrumb/>
            <div className="auth-wrapper">
                <div className="auth-content">
                    <div className="auth-bg">
                        <span className="r"/>
                        <span className="r s"/>
                        <span className="r s"/>
                        <span className="r"/>
                    </div>
                    <div className="card">
                        <div className="card-body text-center">
                            <div className="mb-4">
                                <img className="logo-dark" src={logoPreto} />
                            </div>
                            <h3 className="mb-4">Iniciar sessão</h3>
                            <div className="input-group mb-3">
                                <input type="email" id="email_field" className="form-control email_field" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)}/>
                            </div>
                            <div className="input-group mb-4">
                                <input type="password" className="form-control" placeholder="password" value={password} onChange={e => setPassword(e.target.value)}/>
                            </div>
                            <Link
                                to={{
                                    pathname: "/dashboard",
                                }}
                            >
                                <button className="btn btn-primary shadow-2 mb-4" id="entrar_button" onClick={() => checkUser()}>Entrar</button>
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </Aux>
    );
}

export default Login;