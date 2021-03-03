import React from 'react';
import currencyFormatter from 'currency-formatter';

export default props => {

    const rows = props.processos.map(processo => {
        return (
                <tr key={processo.codProcesso}>
                <td>{processo.codProcesso}</td>
                <td>{processo.nomeProcesso}</td>
                <td>{processo.descricaoProcesso}</td>
                <td><button type="button" 
                            className="btn btn-info"
                            onClick={e => props.editAction(processo.codProcesso)}>Vincular Usuário </button>
                </td>
            </tr>

        )
    }
    )

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nº Processo</th>
                    <th scope="col">Processo</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}