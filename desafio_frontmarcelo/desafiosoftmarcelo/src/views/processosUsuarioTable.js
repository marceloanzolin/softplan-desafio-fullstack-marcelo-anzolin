import React from 'react';
import currencyFormatter from 'currency-formatter';

export default props => {

    const rows = props.processosUsuario.map(processosUsuario => {
        return (
            <tr key={processosUsuario.codProcessoUsuario.codprocesso}>
                <td>{processosUsuario.codProcessoUsuario.codprocesso}</td>
                <td>{processosUsuario.parecerProcesso}</td>
                <td>{processosUsuario.statusProcesso}</td>
                {
                    processosUsuario.statusProcesso == 'F' ? (
                        <td><button type="button"
                            className="btn btn-light "
                            disabled="true"
                            onClick={e => props.editAction(processosUsuario.codProcessoUsuario.codprocesso, processosUsuario.codProcessoUsuario.codusuariofinalizador)}> Inserir Parecer</button>
                        </td>
                    ) :
                        <td><button type="button"
                            className="btn btn-info"
                            onClick={e => props.editAction(processosUsuario.codProcessoUsuario.codprocesso, processosUsuario.codProcessoUsuario.codusuariofinalizador)}> Inserir Parecer</button>
                        </td>
                }

            </tr>

        )
    }
    )

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nº Processo</th>
                    <th scope="col">Parecer</th>
                    <th scope="col">Status</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}