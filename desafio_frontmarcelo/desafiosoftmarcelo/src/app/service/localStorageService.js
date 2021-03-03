
export default class LocalStorageService {

    static adicionaItem(chave, valor) {
        localStorage.setItem(chave,JSON.stringify(valor));
    }

    static buscaItem(chave) {
        const item =  localStorage.getItem(chave);
        return JSON.parse(item);
    }

    static removerItem(chave) {
        localStorage.removeItem(chave);
    }
}