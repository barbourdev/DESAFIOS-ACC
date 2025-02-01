package br.com.desafios.acc.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.sql.SQLIntegrityConstraintViolationException;

//classe que trata excecoes de violacao no banco de dados, no caso apenas coloquei a excecao de tentar registrar um valor repetido em uma coluna UNIQUE
public class DuplicateData implements ExceptionMapper<SQLIntegrityConstraintViolationException> {

    @Override
    public Response toResponse(SQLIntegrityConstraintViolationException exception) {
        String mensagem = "Erro ao cadastrar usuário: ";

        //testa se a mensagem de erro do banco indica duplicidade (chave única)
        if (exception.getMessage().contains("Duplicate entry")) {
            mensagem += "Este e-mail já está cadastrado";
        } else {
            mensagem += "Violação de restrição no banco de dados";
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"" + mensagem + "\"}") //retorna um JSON
                .type("application/json")
                .build();
    }
}
