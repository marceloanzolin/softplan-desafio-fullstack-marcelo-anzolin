import React from 'react';
import Card from '../components/card';
import { withRouter } from 'react-router-dom';
import ProcessoUsuarioTable from '../views/processosUsuarioTable';
import ProcessoService from '../app/service/processoService';
import ProcessoUsuarioService from '../app/service/processoUsuarioService';
import * as messages from '../components/toast';
import LocalStorageService from '../app/service/localStorageService';
import FormGroup from '../components/form-group';
import SelectMenu from '../components/selectMenu';
import { Redirect } from 'react-router-dom';

class ConsultaProcessosFinalizador extends React.Component {



    state = {
        statusFiltro: `P`,
        showConfirmDialog: false,
        usuarioDeletar: {},
        processosUsuario: [],

    }

    constructor() {
        super();
        this.processoService = new ProcessoService();
        this.processoUsuarioService = new ProcessoUsuarioService();
        this.buscar()
    }

    buscar = () => {

        const usuarioLogado = LocalStorageService.buscaItem('_usuario_logado');

        const parametros = {
            codUsuarioLogado: usuarioLogado.codUsuario,
            statusProcesso: this.state.statusFiltro
        }

        this.processoUsuarioService.consultarProcessosFinalizadorStatus(parametros).then(resposta => {
            const listaProcessosUsuario = resposta.data;

            if (listaProcessosUsuario.length < 1) {
                messages.msgAlerta('Nenhum processo pedente encontrado para você !')
            }
            this.setState({ processosUsuario: listaProcessosUsuario })
        }).catch(
            error => {
                console.log(error);
            }
        )

    }

    editar = (codProcesso, codUsuarioFinalizador) => {
        this.props.history.push(`/cadastro-processo-usuario/${codProcesso}/${codUsuarioFinalizador}`)
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/cadastro-processos')

    }

    render() {
        const usuarioLogado = LocalStorageService.buscaItem('_usuario_logado');

        if (usuarioLogado.tpUsuario == 'F') {



            const statusProcessos = this.processoService.obterListaStatusProcesso();
            return (
                <Card title="Processos do Usuário Finalizador"  >
                    <br />


                    <div className="row">
                        <p> Status do Processo : </p>
                        <div className="col-md-6">
                            <div className="bs-component">
                                <SelectMenu id="inputStatus"
                                    value={this.state.statusProcesso}
                                    onChange={e => this.setState({ statusFiltro: e.target.value })}
                                    className="form-control"
                                    lista={statusProcessos} />
                            </div>
                        </div>
                        <div className="col-md-4">
                            <FormGroup>
                                <button type="button" onClick={this.buscar} className="btn btn-success">Buscar</button>
                            </FormGroup>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-md-12">
                            <div className="bs-component">
                                <ProcessoUsuarioTable processosUsuario={this.state.processosUsuario}
                                    editAction={this.editar} />
                            </div>
                        </div>
                    </div>
                </Card>
            )
        } else {

            messages.msgAlerta('[Pareceres dos Processos] Somente Usuários Finalizadores tem permissão!');
            return (
                <Redirect to={{ pathname: '/home' }}></Redirect>
            )
        }
    }
}
export default withRouter(ConsultaProcessosFinalizador);