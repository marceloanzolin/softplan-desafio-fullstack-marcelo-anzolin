
import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import ProcessoService from "../app/service/processoService";
import * as messages from '../components/toast';
import { withRouter } from 'react-router-dom';
import { InputTextarea } from 'primereact/inputtextarea';
import { msgErro, msgSucesso } from "../components/toast";


class CadastroProcesso extends React.Component {

    state = {
        codProcesso: '',
        nomeProcesso: '',
        descricaoProcesso: '',
        atualizando: false
    }
    constructor() {
        super();
        this.processoService = new ProcessoService();
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({ [name]: value })

    }

    componentDidMount() {
        //pega parametros;
        const params = this.props.match.params;

        if (params.id) {

            this.service.obterProcessoPorId(params.id).then(
                response => {
                    this.setState({ atualizando: true })
                    this.setState({ codProcesso: response.data.codProcesso })
                    this.setState({ nomeProcesso: response.data.nomeProcesso })
                    this.setState({ descricaoProcesso: response.data.descricaoProcesso })

                }).catch(erros => {
                    messages.msgErro(erros.response.data)
                })
        }
    }



    validar() {
        const msgs = []

        if (!this.state.nomeProcesso) {
            msgs.push('O campo Nome Processo é Obrigatório;');
        }
        if (!this.state.descricaoProcesso) {
            msgs.push('O campo Descrição do Processo é Obrigatório')
        }

        return msgs;

    }
    cadastrar = () => {

        const processo = {
            nomeProcesso: this.state.nomeProcesso,
            descricaoProcesso: this.state.descricaoProcesso
        }

        try {
            this.processoService.validarProcesso(processo);
        } catch (erro) {
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.msgErro(msg));
            return false;
        }

        this.processoService.salvar(processo).then(

            response => {
                msgSucesso('Processo  Cadastrado com Sucesso!')
                this.props.history.push('/consulta-processos');
            }
        ).catch(error => {
            msgErro('Erro ao salvar o processo', error.response.data)
        })
    }

    cancelar = () => {

        this.props.history.push('/consulta-processos');
    }

    render() {
        return (
            <Card title={this.state.atualizando ? 'Editando o Processo' : 'Cadastro de Processo'}>
                <div className="row">
                    <div className="col-lg-12">
                        <div className="bs-component">
                            <FormGroup label="Nome: *" htmlFor="inputNome">
                                <input type="text"
                                    className="form-control"
                                    id="inputNome"
                                    placeholder="Digite o Nome do Processo"
                                    name="nomeProcesso"
                                    value={this.state.nomeProcesso}
                                    onChange={this.handleChange}

                                />
                            </FormGroup>
                            <FormGroup label="Descrição: *" htmlFor="inputDescricao">
                                <InputTextarea
                                    rows={5}
                                    cols={30}
                                    className="form-control"
                                    id="inputDecricao"
                                    placeholder="Digite a descrição do Processo"
                                    name="descricaoProcesso"
                                    value={this.state.descricaoProcesso}
                                    onChange={this.handleChange}
                                    autoResize />
                            </FormGroup>
                            {
                                this.state.atualizando ? (
                                    <button type="button" onClick={this.atualizar} className="btn btn-success">Atualizar</button>
                                ) :
                                    <button type="button" onClick={this.cadastrar} className="btn btn-success"><i className="pi pi-check p-mr-2" /> Salvar</button>
                            }

                            <button type="button" onClick={this.cancelar} className="btn btn-danger">Cancelar</button>
                        </div>
                    </div>
                </div>
            </Card>
        )
    }
}
export default withRouter(CadastroProcesso);