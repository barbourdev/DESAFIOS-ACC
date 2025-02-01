package br.com.desafios.acc.filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        //permite que qualquer dominio acesse a API
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

        //permite o envio de credenciais
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");

        //define quais headers sao permitidos
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");

        //especifica os metodos HTTP
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
