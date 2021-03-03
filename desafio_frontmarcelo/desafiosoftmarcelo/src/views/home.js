import reactDom from "react-dom";
import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import { withRouter } from 'react-router-dom';
import LocalStorageService from '../app/service/localStorageService';

import { AuthContext } from '../main/provedorAutenticacao';

class Home extends React.Component {

    state = {
        saldo: 0
    }
    constructor() {
        super();
    }
    componentDidMount() {
        const usuarioLogado = this.context.usuarioAutentticado;
    }

    render() {
        return (

            <div className="jumbotron" >
                <h1 className="display-5">Gerenciador de Processos...</h1>
                <p className="lead">Olá este é o desafio feito por Marcelo Anzolin</p>
                <hr className="my-4" />
            </div>
        )
    }
}

Home.contextType = AuthContext;
export default Home;