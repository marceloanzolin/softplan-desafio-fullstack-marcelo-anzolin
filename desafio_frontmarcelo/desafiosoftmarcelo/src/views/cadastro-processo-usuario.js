
import React from 'react';
import Card from '../components/card';
import FormGroup from '../components/form-group';
import ProcessoUsuarioService from "../app/service/processoUsuarioService";
import * as messages from '../components/toast';
import { withRouter } from 'react-router-dom';
import { InputTextarea } from 'primereact/inputtextarea';


class CadastroProcessoUsuario extends React.Component {

    state = {
        codProcesso: '',
        codUsuarioFinalizador: '',
        codUsuarioTriador: '',
        parecerProcesso: '',
        statusProcesso: '',
        atualizando: false
    }
    constructor() {
        super();
        this.processoUsuarioService = new ProcessoUsuarioService();
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({ [name]: value })

    }

    componentDidMount() {
        //pega parametros;
        const params = this.props.match.params;


            this.processoUsuarioService.obterProcessoUsuario(params.codprocesso,params.codusuariofinalizador).then(
                response => {
                    console.log(response.data)
                    this.setState({ atualizando: true })
                    this.setState({ codProcesso:response.data.codProcessoUsuario.codprocesso})
                    this.setState({ codUsuarioFinalizador: response.data.codProcessoUsuario.codusuariofinalizador })
                    this.setState({ codUsuarioTriador:response.data.usuarioTriador.codUsuario})
                    this.setState({ parecerProcesso: response.data.parecerProcesso })
                    this.setState({ statusProcesso:response.data.statusProcesso})

                }).catch(erros => {
                    messages.msgErro('Erro ao carregar os dados')
                })
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

        this.props.history.push('/consulta-processos-finalizador');
    }

    render() {
        return (
            <Card title={this.state.atualizando ? 'Editando Parecer' : 'Inserindo Parecer'}>
                <div className="row">
                    <div className="col-lg-12">
                    <FormGroup label="Parecer: *" htmlFor="inputParecer">
                                <InputTextarea
                                    rows={5}
                                    cols={30}
                                    className="form-control"
                                    id="inputParecer"
                                    placeholder="Digite o Parecer do processo"
                                    name="parecerProcesso"
                                    value={this.state.parecerProcesso}
                                    onChange={this.handleChange}
                                    autoResize />
                            </FormGroup>
                            {
                               
                                    <button type="button" onClick={this.atualizar} className="btn btn-success">Salvar</button>
                            }

                            <button type="button" onClick={this.cancelar} className="btn btn-danger">Cancelar</button>
                        </div>
                </div>
            </Card>
        )
    }
}
export default withRouter(CadastroProcessoUsuario);