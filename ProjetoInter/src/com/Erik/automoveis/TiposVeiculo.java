package com.Erik.automoveis;

public enum TiposVeiculo {
    carro(1),
    moto(2),
    caminhao(3);

    private final int valor;

    TiposVeiculo(int valorOpcao){
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
