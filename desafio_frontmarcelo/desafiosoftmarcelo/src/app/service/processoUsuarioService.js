import ApiService from '../apiservice';

 class ProcessoUsuarioService extends ApiService {

    constructor() {
        super('api/processosusuario');
    }

    obterProcessoUsuario(codProcesso,codUsuarioFinalizador){

        let params = `?codprocesso=${codProcesso}&codusuariofinalizador=${codUsuarioFinalizador}`
   
        return this.get(`/inserirparecer${params}`)

    }

    salvar(processo) {
        return this.post('/', processo);
    }

    consultarProcessos() {
        
        return this.get();

    }
    
    atualizar(processosUsuario) {
        let params = `?codprocesso=${processosUsuario.codprocesso}&codprocessofinalizador=${processosUsuario.codusuariofinalizador}`
        return this.put(`/${params}`,processosUsuario);
    }

    consultarProcessosFinalizadorStatus(parametros){
        let params = `?codusuariofinalizador=${parametros.codUsuarioLogado}&tpstatus=${parametros.statusProcesso}`
      
        return this.get(params);
    }

}
export default ProcessoUsuarioService;