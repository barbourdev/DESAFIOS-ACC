package br.com.desafios.acc.service;

import br.com.desafios.acc.connection.ConexaoBanco;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//classe para exportar os dados do bd para um txt

public class ExportFile {

    public void exportarParaTXT(String filePath) {
        //comando sql para selecionar todos os dados da tabela 'users'
        String sql = "SELECT * FROM users";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             FileWriter writer = new FileWriter(filePath)) {

            while (rs.next()) {
                //formatacao dos dados separados por '#' e adiciona uma quebra de linha
                String linha = rs.getString("nome") + "#" +
                        rs.getString("email") + "#" +
                        rs.getString("telefone") + "\n";

                writer.write(linha);
            }

            System.out.println("Exportação para TXT concluída!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
