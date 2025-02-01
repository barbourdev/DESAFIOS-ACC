package br.com.desafios.acc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe para gerenciar a conexao com o bd

public class ConexaoBanco {
    // URL de conexão com o banco (usei o mysql)
    private static final String URL = "jdbc:mysql://localhost:3306/crud_usuarios";

    // senhas de acesso ao banco
    private static final String USER = "root";
    private static final String PASSWORD = "localhost";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            throw new SQLException("Driver do MySQL não encontrado", e);
        }
    }
}