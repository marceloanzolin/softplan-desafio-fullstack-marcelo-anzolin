
import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import SelectMenu from '../components/selectMenu';
import UsuarioService from "../app/service/usuarioService";
import * as messages from '../components/toast';
import { withRouter } from 'react-router-dom';
import { msgErro, msgSucesso } from "../components/toast";


class CadastroUsuario extends React.Component {

    state = {
        codUsuario: '',
        nmUsuario: '',
        emailUsuario: '',
        senhaUsuario: '',
        senhaRepeticao: '',
        tpUsuario: '',
        atualizando: false
    }
    constructor() {
        super();
        this.service = new UsuarioService();
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({ [name]: value })

    }

    componentDidMount() {
      
        const params = this.props.match.params;

        if (params.id) {

            this.service.obterUsuarioPorId(params.id).then(
                response => {
                    this.setState({ atualizando: true })
                    this.setState({ codUsuario: response.data.codUsuario })
                    this.setState({ nmUsuario: response.data.nmUsuario })
                    this.setState({ emailUsuario: response.data.emailUsuario })
                    this.setState({ senhaUsuario: response.data.senhaUsuario })
                    this.setState({ senhaRepeticao: response.data.senhaUsuario })
                    this.setState({ tpUsuario: response.data.tpUsuario })

                }).catch(erros => {
                    messages.msgErro(erros.response.data)
                })
        }
    }



    validar() {
        const msgs = []

        if (!this.state.nmUsuario) {
            msgs.push('O campo Nome é Obrigatório;');
        }
        if (!this.state.emailUsuario) {
            msgs.push('O campo Email é Obrigatório')
        } else if (!this.state.emailUsuario.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]/)) {
            msgs.push('Informe um email válido')
        }

        if (!this.state.senhaUsuario || !this.state.senhaRepeticao) {
            msgs.push('Digite a senha 2x')
        } else if (this.state.senhaUsuario !== this.state.senhaRepeticao) {
            msgs.push('Senhas estão diferentes')
        }

        if (!this.state.tpUsuario) {
            msgs.push('Informe o Tipo do Usuário')
        }

        return msgs;

    }

    atualizar = () => {

        const { nmUsuario, emailUsuario, senhaUsuario, tpUsuario, codUsuario } = this.state;

        const usuario = { nmUsuario, emailUsuario, senhaUsuario, tpUsuario, codUsuario }

        this.service.atualizar(usuario).then(response => {
            this.props.history.push('/consulta-usuarios')
            messages.msgSucesso('Usuario salvo com Sucesso')
        }).catch(error => {
            messages.msgErro(error.response.data);
        })

    }

    cadastrar = () => {


        const usuario = {
            nmUsuario: this.state.nmUsuario,
            emailUsuario: this.state.emailUsuario,
            senhaUsuario: this.state.senhaUsuario,
            tpUsuario: this.state.tpUsuario,
            senhaRepeticao: this.state.senhaRepeticao
        }

        try {
            this.service.validarUsuario(usuario);
        } catch (erro) {
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.msgErro(msg));
            return false;
        }

        this.service.salvar(usuario).then(

            response => {
                msgSucesso('Usuário Cadastrado com Sucesso!')
                this.props.history.push('/consulta-usuarios');
            }
        ).catch(error => {
            msgErro('Erro ao salvar o Usuário', error.response.data)
        })
    }

    cancelar = () => {

        this.props.history.push('/consulta-usuarios');
    }

    render() {
        const tipoUsuarios = this.service.obterListaTipos();
        return (
            <Card title={this.state.atualizando ? 'Editando o Usuário' : 'Cadastro de Usuário'}>
                <div className="row">
                    <div className="col-lg-12">
                        <div className="bs-component">
                            <FormGroup label="Nome: *" htmlFor="inputNome">
                                <input type="text"
                                    className="form-control"
                                    id="inputNome"
                                    placeholder="Digite o Nome"
                                    name="nmUsuario"
                                    value={this.state.nmUsuario}
                                    onChange={this.handleChange}

                                />
                            </FormGroup>
                            <FormGroup label="Email: *" htmlFor="inputEmail">
                                <input type="email"
                                    className="form-control"
                                    id="inputEmail"
                                    name="emailUsuario"
                                    value={this.state.emailUsuario}
                                    onChange={this.handleChange}
                                    placeholder="Digite o Email" />
                            </FormGroup>
                            <div>
                            <FormGroup id="inputTipo" label="Tipo:*">
                            <SelectMenu id="inputTipo"
                                lista={tipoUsuarios}
                                className="form-control"
                                name="tpUsuario"
                                value={this.state.tpUsuario}
                                onChange={this.handleChange}
                            />
                            </FormGroup>
                            </div>
                            <FormGroup label="Senha: *" htmlFor="inputSenha">
                                <input type="password"
                                    className="form-control"
                                    id="inputSenha"
                                    name="senhaUsuario"
                                    value={this.state.senhaUsuario}
                                    onChange={this.handleChange}
                                    placeholder="Digite o Senha" />
                            </FormGroup>
                            <FormGroup label="Repita a Senha: *" htmlFor="inputRepitaSenha">
                                <input type="password"
                                    className="form-control"
                                    id="inputRepitaSenha"
                                    name="senhaRepeticao"
                                    value={this.state.senhaRepeticao}
                                    onChange={this.handleChange}
                                    placeholder="Digite o Senha" />
                            </FormGroup>
                            {
                                this.state.atualizando ? (
                                    <button type="button" onClick={this.atualizar} className="btn btn-success">Atualizar</button>
                                ) :
                                    <button type="button" onClick={this.cadastrar} className="btn btn-success"><i className="pi pi-check p-mr-2"/> Salvar</button>
                            }

                            <button type="button" onClick={this.cancelar} className="btn btn-danger">Cancelar</button>
                        </div>
                    </div>
                </div>
            </Card>
        )
    }
}
export default withRouter(CadastroUsuario);