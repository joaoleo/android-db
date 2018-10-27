package net.leocadio.joao.sistemadeferramentas.models;

public class Ferramenta {

    private Integer numreg;
    private String nome_ferramenta;
    private String fabricante;
    private Float preco;
    private String cor;
    private String referencia;

    public Integer getNumreg() {
        return numreg;
    }

    public void setNumreg(Integer numreg) {
        this.numreg = numreg;
    }

    public String getNome_ferramenta() {
        return nome_ferramenta;
    }

    public void setNome_ferramenta(String nome_ferramenta) {
        this.nome_ferramenta = nome_ferramenta;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
