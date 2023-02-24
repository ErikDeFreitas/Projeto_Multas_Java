package com.Erik.automoveis;

public class Multa {
    private float valor;
    private String descricao;

    public Multa(String descricao, float valor) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "Motivo da multa: " + descricao + "\nValor da multa: R$" + valor;
    }
}
