import React from 'react';
import Card from '../components/card';
import { withRouter } from 'react-router-dom';
import UsuariosTable from '../views/processosTable';
import ProcessoService from '../app/service/processoService';
import * as messages from '../components/toast';
import { Button } from 'primereact/button';
import LocalStorageService from '../app/service/localStorageService';
import { Redirect } from 'react-router-dom';

class ConsultaProcessos extends React.Component {



    state = {
        showConfirmDialog: false,
        usuarioDeletar: {},
        processos: [],

    }

    constructor() {
        super();
        this.processoService = new ProcessoService();
        this.buscar()
    }

    buscar = () => {


        this.processoService.consultarProcessos().then(resposta => {
            const lista = resposta.data;
            if (lista.length < 1) {
                messages.msgAlerta('Nenhum processo encontrado')
            }
            this.setState({ processos: lista })
        }).catch(
            error => {
                console.log(error);
            }
        )
    }

    editar = (id) => {
        this.props.history.push(`/cadastro-processos/${id}`)
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/cadastro-processos')

    }

    render() {

        const usuarioLogado = LocalStorageService.buscaItem('_usuario_logado');

        if (usuarioLogado.tpUsuario == 'T') {

            return (
                <Card title="Processos Cadastrados" >
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="bs-component">
                                <button type="button" onClick={this.preparaFormularioCadastro} className="btn btn-success">Novo Processo</button>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div className="row">
                        <div className="col-md-12">
                            <div className="bs-component">
                                <UsuariosTable processos={this.state.processos}
                                    deleteAction={this.abirModal}
                                    editAction={this.editar} />
                            </div>
                        </div>
                    </div>
                </Card>
            )
        } else {

            messages.msgAlerta('[Manter Processos] Somente Usuários Triadores tem permissão!');
            return (
                <Redirect to={{ pathname: '/home' }}></Redirect>
            )
        }
    }
}
export default withRouter(ConsultaProcessos);