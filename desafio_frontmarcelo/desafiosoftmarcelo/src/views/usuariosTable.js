import React from 'react';
import currencyFormatter from 'currency-formatter';

export default props => {

    const rows = props.usuarios.map(usuario => {
        return (
            <tr key={usuario.codUsuario}>
                <td>{usuario.nmUsuario}</td>
                <td>{usuario.emailUsuario}</td>
                <td>{usuario.tpUsuario}</td>
                <td><button type="button" 
                            className="btn btn-primary"
                            onClick={e => props.editAction(usuario.codUsuario)}>Editar</button>
                    <button type="button" 
                            className="btn btn-danger" 
                            onClick={e => props.deleteAction(usuario)}>Deletar</button>
                </td>
            </tr>

        )
    }
    )

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Email</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}