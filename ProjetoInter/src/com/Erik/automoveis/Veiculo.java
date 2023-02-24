package com.Erik.automoveis;

public class Veiculo {

    private String placa;
    private String modelo;
    private String cor;
    private TiposVeiculo veiculo;


    public Veiculo(String placa, String modelo, String cor, TiposVeiculo veiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.veiculo = veiculo;

    }

    public TiposVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(TiposVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    @Override
    public String toString(){
        return "Tipo do veiculo: " + veiculo + "\nCor: " + cor + "\nModelo: " + modelo + "\nPlaca: " + placa;
    }
}