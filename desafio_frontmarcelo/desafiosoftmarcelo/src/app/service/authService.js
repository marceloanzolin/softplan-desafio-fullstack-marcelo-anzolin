import LocalStorageService from './localStorageService'; 

export const USUARIO_LOGADO = '_usuario_logado';



export default class AuthService {

    static isUsuarioAutenticado(){
        const  usuario = LocalStorageService.buscaItem(USUARIO_LOGADO);
        return usuario && usuario.codUsuario;
     
    }

    static removerUsuarioAutenticado(){
         LocalStorageService.removerItem(USUARIO_LOGADO);
    }


    static logar(usuario){
        LocalStorageService.adicionaItem(USUARIO_LOGADO,usuario);
    }

    static obterUsuarioLogado(){
        LocalStorageService.obterItem(USUARIO_LOGADO);
    }

 }
