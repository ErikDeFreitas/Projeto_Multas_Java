package com.Erik.automoveis;

public class Moto extends Automovel{

    public Moto(String placa, String modelo, String cor) {
        super(placa, modelo, cor, TiposVeiculo.moto);
    }
}
