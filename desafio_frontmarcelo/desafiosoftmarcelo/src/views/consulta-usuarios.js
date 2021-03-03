import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import { withRouter } from 'react-router-dom';
import UsuariosTable from '../views/usuariosTable';
import UsuarioService from '../app/service/usuarioService';
import * as messages from '../components/toast';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import LocalStorageService from '../app/service/localStorageService';
import { Redirect } from 'react-router-dom';

class ConsultaUsuarios extends React.Component {



    state = {
        showConfirmDialog: false,
        usuarioDeletar: {},
        usuarios: [],

    }

    constructor() {
        super();
        this.usuarioService = new UsuarioService();
        this.buscar()
    }

    buscar = () => {


        this.usuarioService.consultarUsuarios().then(resposta => {
            const lista = resposta.data;
            if (lista.length < 1) {
                messages.msgAlerta('Nenhum Usuário encontrado')
            }
            this.setState({ usuarios: lista })
        }).catch(
            error => {
                console.log(error);
            }
        )
    }

    editar = (id) => {
        this.props.history.push(`/cadastro-usuarios/${id}`)
    }

    abirModal = (usuario) => {
        this.setState({ showConfirmDialog: true, usuarioDeletar: usuario })
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/cadastro-usuarios')

    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog: false, usuarioDeletar: {} })
    }





    deletar = () => {
        this.usuarioService.deletar(this.state.usuarioDeletar.codUsuario).then(response => {
            const usuarios = this.state.usuarios;
            const index = usuarios.indexOf(this.state.usuarioDeletar)
            usuarios.splice(index, 1);
            this.setState({ usuarios: usuarios, showConfirmDialog: false });
            messages.msgSucesso('Usuário excluido');
        }).catch(error => {
            messages.msgErro('Erro ao Excluir o Usuário');
        })
    }

    render() {

        const usuarioLogado = LocalStorageService.buscaItem('_usuario_logado');

        if (usuarioLogado.tpUsuario == 'A') {

            const coonfirmDialgoFooter = (
                <div>
                    <Button label="Confirmar" icon="pi pi-check" onClick={this.deletar} />
                    <Button label="Cancelar" icon="pi pi-times" onClick={this.cancelarDelecao} />
                </div>
            );

            return (
                <Card title="Usuários Cadastrados" >
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="bs-component">
                                <button type="button" onClick={this.preparaFormularioCadastro} className="btn btn-success">Novo Usuário</button>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div className="row">
                        <div className="col-md-12">
                            <div className="bs-component">
                                <UsuariosTable usuarios={this.state.usuarios}
                                    deleteAction={this.abirModal}
                                    editAction={this.editar} />
                            </div>
                        </div>
                    </div>
                    <div>
                        <Dialog header="Confirmação"
                            visible={this.state.showConfirmDialog}
                            style={{ width: '50vw' }}
                            modal={true}
                            footer={coonfirmDialgoFooter}
                            onHide={() => this.setState({ showConfirmDialog: false })} >
                            Deseja excluir este usuário ?
                    </Dialog>

                    </div>
                </Card>
            )
        } else {

            messages.msgAlerta('[Manter Usuários] Somente Usuários Administradores tem permissão!');
            return (
                <Redirect to={{ pathname: '/home' }}></Redirect>
            )

        }
    }
}
export default withRouter(ConsultaUsuarios);