
import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import ProcessoUsuarioService from "../app/service/processoUsuarioService";
import UsuarioService from "../app/service/usuarioService";
import * as messages from '../components/toast';
import { withRouter } from 'react-router-dom';
import SelectMenu from '../components/selectMenu';
import { InputTextarea } from 'primereact/inputtextarea';


class CadastroVinculaFinalizador extends React.Component {

    state = {
        codProcesso: '',
        codUsuarioFinalizador: '',
        codUsuarioTriador: '',
        parecerProcesso: '',
        statusProcesso: '',
        atualizando: false,
        listaUsuarios: []
    }
    constructor() {
        super();
        this.processoUsuarioService = new ProcessoUsuarioService();
        this.usuarioService = new UsuarioService();
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({ [name]: value })

    }

    componentDidMount() {

    }

    atualizar = () => {
        const { codProcesso, codUsuarioFinalizador, codUsuarioTriador, parecerProcesso, statusProcesso } = this.state;

        const processoUsuario = { codProcesso, codUsuarioFinalizador, codUsuarioTriador, parecerProcesso, statusProcesso }


        this.processoUsuarioService.atualizar(processoUsuario).then(response => {
            this.props.history.push('/consulta-processos-finalizador')
            messages.msgSucesso('Parecer Inserido com Sucesso')
        }).catch(error => {
            messages.msgErro(error.response.data);
        })

    }

    cancelar = () => {

        this.props.history.push('/consulta-processos');
    }

    render() {
        const listaUsuario = [];
        this.usuarioService.obterUsuarioPorTipo('F').then(resposta => {
            listaUsuario = resposta.data;
            if (listaUsuario.length < 1) {
                messages.msgAlerta('Nenhum usuario encontrado')
            }
        }).catch(
            error => {
                console.log(error);
            }
        )

        return (

            <Card title='Vinculando Finalizador'>
                <div className="row">
                    <div className="col-lg-12">
                        <FormGroup id="inputUsuarioFinalizador" label="Usuário Finalizador:*">
                            <SelectMenu id="inputFinalizador"
                                lista={listaUsuario}
                                className="form-control"
                                name="codUsuarioFinalizador"
                                value={this.state.codUsuarioFinalizador}
                                onChange={this.handleChange}
                            />
                        </FormGroup>
                        {

                            <button type="button" onClick={this.atualizar} className="btn btn-success">Vincular</button>
                        }

                        <button type="button" onClick={this.cancelar} className="btn btn-danger">Cancelar</button>
                    </div>
                </div>
            </Card>
        )
    }
}
export default withRouter(CadastroVinculaFinalizador);