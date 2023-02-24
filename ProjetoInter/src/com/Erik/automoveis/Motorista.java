package com.Erik.automoveis;

import java.util.ArrayList;

public class Motorista {
    private String nome;

    private Veiculo veiculo;

    private String chapa;

    private final ArrayList<Multa> multas = new ArrayList<>();


    public Motorista(String nome, String chapa, Veiculo veiculo) {
        this.nome = nome;
        this.chapa = chapa;
        this.veiculo = veiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void cadastraMulta(Multa multa){
        multas.add(multa);
    }
    public ArrayList<Multa> getMultas() {
        return multas;
    }

    @Override
    public String toString(){
        StringBuilder multinha = new StringBuilder();
        String veiculinho = "";
        for (Multa multa: multas) {
            multinha.append(multa).append("\n");
        }
        if (multinha.isEmpty()){
            multinha.append("Não há multas");
        }
        if (veiculo == null){
            veiculinho = "Não há veiculo cadastrado nesse motorista";
        }else {
            veiculinho = veiculo.toString();
        }
        return "com.Erik.automoveis.Motorista: " + nome + "\nChapa: " + chapa + "\ncom.Erik.automoveis.Veiculo: " + veiculinho + "\nMultas: " + multinha;
    }
}
