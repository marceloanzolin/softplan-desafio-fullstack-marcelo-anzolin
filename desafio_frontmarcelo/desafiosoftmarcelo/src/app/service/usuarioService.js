import ApiService from '../apiservice';
import ErroValidacao from '../exception/ErroValidacao';

class UsuarioService extends ApiService {

    constructor() {
        super('api/usuarios');
    }

    autenticar(credenciais) {
        return this.post('/autenticar', credenciais);

    }

    obterUsuarioPorId(id) {
        return this.get(`/${id}`)
    }

    salvar(usuario) {
        return this.post('/', usuario);
    }

    atualizar(usuario) {
        return this.put(`/${usuario.codUsuario}`, usuario);
    }

    consultarUsuarios() {

        return this.get();

    }

    deletar(codUsuario) {
        return this.delete(`/${codUsuario}`);
    }

    obterUsuarioPorTipo(tpUsuario) {
        return this.get(`/buscausuario`)
    }

    validarUsuario(usuario) {

        const erros = [];

        if (!usuario.nmUsuario) {
            erros.push('O campo Nome é Obrigatório;');
        }
        if (!usuario.emailUsuario) {
            erros.push('O campo Email é Obrigatório')
        } else if (!usuario.emailUsuario.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]/)) {
            erros.push('Informe um email válido')
        }

        if (!usuario.senhaUsuario || !usuario.senhaRepeticao) {
            erros.push('Digite a senha 2x')
        } else if (usuario.senhaUsuario !== usuario.senhaRepeticao) {
            erros.push('Senhas estão diferentes')
        }

        if (!usuario.tpUsuario) {
            erros.push('Informe o Tipo do Usuário');
        }

        if (erros && erros.length > 0) {

            throw new ErroValidacao(erros);
        }
    }

    obterListaTipos() {
        return [
            { label: 'Selecione...', value: '' },
            { label: 'Administrador', value: 'A' },
            { label: 'Triador', value: 'T' },
            { label: 'Finalizador', value: 'F' }
        ]
    }

}
export default UsuarioService;