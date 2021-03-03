import React from 'react';
import AuthService from '../app/service/authService';


export const AuthContext = React.createContext();
export const AuthConsumer = AuthContext.Consumer;
const AuthProvider = AuthContext.Provider;

class ProviderAutenticacao extends React.Component {

    state = {
        usuarioAutenticado: null,
        isAutenticado: false,
        tpUsuario: null
    }

    iniciarSessao = (usuario) => {
        AuthService.logar(usuario);
        this.setState({ isAutenticado: true, usuarioAutenticado: usuario,tpUsuario: usuario.tpUsuario })
    }


    encerrarSessao = () => {
        AuthService.removerUsuarioAutenticado();
        this.setState({ isAutenticado: false, usuarioAutenticado: null,tpUsuario: null })
    }

    render() {
        const contexto = {
            usuarioAutenticado:this.state.usuarioAutenticado,
            isAutenticado:this.state.isAutenticado,
            tpUsuario:this.state.tpUsuario,
            iniciarSessao:this.iniciarSessao,
            encerrarSessao:this.encerrarSessao

        }
        return (
            <AuthProvider value={contexto}>
                {this.props.children}
            </AuthProvider>
        )
    }
}
export default  ProviderAutenticacao;