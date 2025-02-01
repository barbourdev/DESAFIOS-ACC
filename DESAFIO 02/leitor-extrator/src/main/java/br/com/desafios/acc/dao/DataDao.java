package br.com.desafios.acc.dao;

import br.com.desafios.acc.connection.ConexaoBanco;
import br.com.desafios.acc.model.FileModel;

import java.sql.*;
import java.util.List;

//classe para salvar no bd os dados do arquivo

public class DataDao {

    public void salvarDados(List<FileModel> dados) throws SQLException {
        //comando SQL (query) para conseguir inserir no banco de dados nas colunas corretas os valores
        // os '?' representa os futuros valores que virao do arquivo csv
        String sql = "INSERT INTO users (nome, email, telefone) VALUES (? , ? ,?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (FileModel dado : dados) {
                // define os valores dos '?'
                stmt.setString(1, dado.getColuna1()); // Nome
                stmt.setString(2, dado.getColuna2()); // Email
                stmt.setString(3, dado.getColuna3()); // Telefone

                // executa o comando no bd
                stmt.executeUpdate();
            }
        }
    }
}
