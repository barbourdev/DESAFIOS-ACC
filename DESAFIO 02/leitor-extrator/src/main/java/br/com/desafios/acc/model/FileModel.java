package br.com.desafios.acc.model;

public class FileModel {

    private int id;
    private String coluna1;
    private String coluna2;
    private String coluna3;

    public FileModel(String coluna1, String coluna2, String coluna3) {
        this.coluna1 = coluna1;
        this.coluna2 = coluna2;
        this.coluna3 = coluna3;
    }

    //metodos getters para acessar os valores de cada coluna individualmente

    public String getColuna1() {
        return coluna1;
    }
    public String getColuna2() {
        return coluna2;
    }
    public String getColuna3() {
        return coluna3;
    }

    //metodo getDados() retorna um array com todas as colunas

    public String[] getDados(){
        return new String[]{
                coluna1, coluna2, coluna3
        };
    }

}
