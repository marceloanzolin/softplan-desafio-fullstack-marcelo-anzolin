import ApiService from '../apiservice';
import ErroValidacao from '../exception/ErroValidacao';

 class ProcessoService extends ApiService {

    constructor() {
        super('api/processos');
    }


    obterProcessoPorId(id){
        return this.get(`/${id}`)
    }

    salvar(processo) {
        return this.post('/', processo);
    }


    consultarProcessos() {
        
        return this.get();

    }

    validarProcesso(processo){

        const erros = [];

        if (!processo.nomeProcesso) {
            erros.push('O campo Nome do Processo é Obrigatório');
        }

        if (!processo.descricaoProcesso) {
            erros.push('O campo Descrição do Processo é Obrigatório');
        }


        if (erros && erros.length > 0){

            throw new ErroValidacao(erros);
        } 
    }

    obterListaStatusProcesso(){
        return  [
            { label: 'Pendente', value: 'P' },
            { label: 'Finalizado' , value : 'F' }
        ]
    }

}
export default ProcessoService;