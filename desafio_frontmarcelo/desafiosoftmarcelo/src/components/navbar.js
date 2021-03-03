import React from 'react';
import NavbarItem from './navbarItem';

import { AuthConsumer } from '../main/provedorAutenticacao';


function NavBar(props) {

    return (
        <div className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
            <div className="container">
                
                <a href="#/home" className="navbar-brand">  Gerenciador de Processo Softplan</a>
                <a href="#/login" className="navbar-link"> Login  </a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
              
                <div className="collapse navbar-collapse" id="navbarResponsive">
                    <ul className="navbar-nav">
                        <NavbarItem render={props.isUsuarioAutenticado} href="#/consulta-usuarios" label="Usuários"/>
                        <NavbarItem render={props.isUsuarioAutenticado} href="#/consulta-Processos" label="Processos"/>
                        <NavbarItem render={props.isUsuarioAutenticado} href="#/consulta-Processos-finalizador" label="Processos Finalizador"/>
                        <NavbarItem render={props.isUsuarioAutenticado} onClick={props.deslogar} href="#/login" label="Sair" />
                    </ul>

                </div>
            </div>
        </div>
    )

}

export default () => (
    //renderiz o componeent
    <AuthConsumer>
        {
            (context) => (<NavBar isUsuarioAutenticado={context.isAutenticado}  tpUsuario={context.tpUsuario} deslogar={context.encerrarSessao} />)
        }

    </AuthConsumer>
);//Rotas;