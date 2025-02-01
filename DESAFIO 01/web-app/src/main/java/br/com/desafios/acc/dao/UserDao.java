package br.com.desafios.acc.dao;

import br.com.desafios.acc.connection.ConexaoBanco;
import br.com.desafios.acc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//classe para salvar/listar/editar/excluir no bd os dados do usuario
public class UserDao {

    public void addUser(User user) throws SQLException {
        //comando SQL (query) para conseguir inserir no banco de dados nas colunas corretas os valores
        // os '?' representa os futuros valores que virao do usuario no front-end
        String sql = "INSERT INTO usuario (nome, email, telefone, data_nascimento, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // define os valores dos '?'
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getTelefone());
            stmt.setString(4, user.getDataNascimento());
            stmt.setString(5, user.getEndereco());

            // executa o comando no bd
            stmt.executeUpdate();

            //pega qualquer tipo de erro na hora da requisicao
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um usuário com esse e-mail.", e);
            } else {
                throw new SQLException("Erro ao inserir usuário: " + e.getMessage(), e);
            }
        }
    }

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        //comando SQL (query) para conseguir recuperar todos os cadastros da tabela 'usuario'
        String sql = "SELECT * FROM usuario";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // define os valores da lista na seguinte ordem: id, nome, email, telefone, data_nasc, endereco
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("data_nascimento"),
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar usuários: " + e.getMessage(), e);
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        //comando SQL (query) para conseguir atualizar os dados da tabela 'usuario' de um usuario especifico pelo 'id'
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, data_nascimento = ?, endereco = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // define os valores dos novos dados do usuario, a partir dos inputs no front end
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getTelefone());
            stmt.setString(4, user.getDataNascimento());
            stmt.setString(5, user.getEndereco());
            stmt.setInt(6, user.getId());

            // executa o comando no bd
            int linhasAfetadas = stmt.executeUpdate();

            // tratativa de alguns possiveis erros
            if (linhasAfetadas == 0) {
                throw new SQLException("Usuário com ID " + user.getId() + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    public void deleteUser(int id) throws SQLException {
        //comando sql (query) para deletar o usuario a partir de um id especifico
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            // executa o comando no bd
            int linhasAfetadas = stmt.executeUpdate();

            // tratativa de alguns possiveis erros
            if (linhasAfetadas == 0) {
                throw new SQLException("Usuário com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir usuário: " + e.getMessage(), e);
        }
    }
}
