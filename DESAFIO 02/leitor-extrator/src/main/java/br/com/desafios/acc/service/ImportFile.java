package br.com.desafios.acc.service;

import br.com.desafios.acc.dao.DataDao;
import br.com.desafios.acc.model.FileModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportFile {
    // novo objeto da classe dao para salvar os dados no bd
    private DataDao dataDao = new DataDao();

    //metodo que ira ler o arquivo e salvar no bd
    public void importarFile(String filePath) {
        //lista da classe FileModel para armazenar no formato esperado
        List<FileModel> dados = new ArrayList<>();

        //tentar abrir o arquivo e ler
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            boolean primeiraLinha = true;

            //caso aberto com sucesso, ler linha por linha desconsiderando o cabecalho
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                //System.out.println(linha);

                //divide os dados da LINHA com ';'
                String[] valores = linha.split(";");

                if (valores.length >= 3) {
                    //cria um novo objeto do FileModel e joga os valores no formato esperado
                    dados.add(new FileModel(valores[0], valores[1], valores[2]));
                }
            }

            //chama o metodo responsavel por salvar no bd
            dataDao.salvarDados(dados);
            System.out.println("Importação do CSV concluída!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
