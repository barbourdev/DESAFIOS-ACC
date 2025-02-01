package br.com.desafios.acc.test;

import br.com.desafios.acc.service.ExportFile;
import br.com.desafios.acc.service.ImportFile;

public class Main {

    public static void main(String[] args){
        ImportFile importar = new ImportFile();
        ExportFile exportar = new ExportFile();

        String csvFile = "resources/lista.csv";
        String txtFile = "resources/saida.txt";

        //importar CSV para o banco
        importar.importarFile(csvFile);

        //exportar dados para TXT
        exportar.exportarParaTXT(txtFile);
    }
}

