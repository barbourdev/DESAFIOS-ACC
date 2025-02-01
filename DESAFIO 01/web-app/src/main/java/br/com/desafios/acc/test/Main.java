package br.com.desafios.acc.test;

import br.com.desafios.acc.exception.DuplicateData;
import br.com.desafios.acc.filter.CorsFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("br.com.desafios.acc.resource");
        rc.register(CorsFilter.class);
        rc.register(DuplicateData.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println("Servidor iniciado: " + BASE_URI);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}