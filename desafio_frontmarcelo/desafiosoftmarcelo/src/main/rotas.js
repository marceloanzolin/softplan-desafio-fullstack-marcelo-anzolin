import React from 'react';


import Login from '../views/login';
import CadastroUsuario from '../views/cadastro-usuarios';
import ConsultaUsuarios from '../views/consulta-usuarios';
import CadastroProcesso from '../views/cadastro-processos';
import ConsultaProcessos from '../views/consulta-processos';
import consultaProcessosFinalizador from '../views/consulta-processos-finalizador';
import Home from '../views/home';

import {AuthConsumer} from '../main/provedorAutenticacao';

import { Route, Switch, HashRouter,Redirect } from 'react-router-dom';
import cadastroProcessoUsuario from '../views/cadastro-processo-usuario';


function RotaAutenticada({ component: Component,isUsuarioAutenticado,tpUsuario, ...props }) {
    return (
     
        <Route {...props} render={(componentProps) => {
            if (isUsuarioAutenticado) {
                return (
                    // renderiza o componente com todas as suas propriedades
                    <Component {...componentProps} />

                )
            }else{
                    return (
                        <Redirect to={{pathname :'/login',state : {from:componentProps.location} }}></Redirect>
                    )
            }

        }
        } />
    )
}

function Rotas(props) {
    return (
        <HashRouter>
            <Switch> 
                <Route path="/login" component={Login}></Route>
                <Route path="/cadastro-usuarios/:id?" component={CadastroUsuario}></Route> 
                <RotaAutenticada tpUsuario={props.tpUsuario} isUsuarioAutenticado={props.isUsuarioAutenticado}   path="/consulta-usuarios" component={ConsultaUsuarios}></RotaAutenticada>
                <RotaAutenticada tpUsuario={props.tpUsuario} isUsuarioAutenticado={props.isUsuarioAutenticado}   path="/home" component={Home}></RotaAutenticada>

                <Route path="/cadastro-processos/:id?" component={CadastroProcesso}></Route> 
                <RotaAutenticada tpUsuario={props.tpUsuario} isUsuarioAutenticado={props.isUsuarioAutenticado}   path="/consulta-processos" component={ConsultaProcessos}></RotaAutenticada>
                <RotaAutenticada tpUsuario={props.tpUsuario} isUsuarioAutenticado={props.isUsuarioAutenticado}   path="/consulta-processos-finalizador" component={consultaProcessosFinalizador}></RotaAutenticada>
                <Route path="/cadastro-processo-usuario/:codprocesso/:codusuariofinalizador" component={cadastroProcessoUsuario}></Route> 
            </Switch>
        </HashRouter>
    )
}

export default  () => (
    <AuthConsumer>
        {
            (context)=>(<Rotas tpUsuario={context.tpUsuario} isUsuarioAutenticado={context.isAutenticado}/>)
        }

    </AuthConsumer>
);//Rotas;