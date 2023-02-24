package com.Erik.main;

import com.Erik.automoveis.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Motorista> motoristas = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    Seja Muito Bem-Vindo ao sistema!\s
                    Por favor insira seu cargo:\s
                    [ 1 ] - com.Erik.automoveis.Motorista.\s
                    [ 2 ] - Agente.\s
                    [ 3 ] - Gerente.\s
                    [ 4 ] - Fechar Sistema""");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 ->

                    //Parte do motorista, aqui ele só podera ter acesso as multas no nome dele, ou seja, tera que pedir
                    //um input para colocar a chapa dele e buscar multas designadas a chapa dele.
                        handleMotorista(scanner);
                case 2 ->
                    //Parte do agente, apenas podera cadastrar multas designadas a placas de carros, tera que fazer um esquema
                    //para vincular a placa do carro à chapa do motorista.
                        handleAgente(scanner);
                case 3 ->
                    //Parte do gerente, tem acesso a porra toda, acho que vai ser igual a do motorista, só que podendo ver
                    // como uma lista organizada igual a do estacionamento.
                        handleGerente(scanner);
                case 4 -> isRunning = false;
                default -> System.out.println("Opção invalida");
            }

        }

        System.out.println("Sistema Encerrado!");

    }
    public static void handleMotorista(Scanner scanner) {
        System.out.println("Bem vindo com.Erik.automoveis.Motorista!");
        consultaMulta(scanner);
    }
    public static void consultaMulta(Scanner scanner){
        consultaMulta(scanner, false);
    }
    public static void consultaMulta(Scanner scanner, boolean fromGerente) {
        boolean isRunning = true;
        while (isRunning) {
            int escolha = 1;
            if (!fromGerente){
                System.out.println("[ 1 ] - Consultar Multas \n" +
                        "[ 2 ] - Sair do Menu com.Erik.automoveis.Motorista");
                escolha = scanner.nextInt();
            }

            if (escolha == 1){

                System.out.println("Digite sua chapa.");
                String chapa = scanner.next();
                boolean temMulta = false;
                for (Motorista motorista: motoristas){
                    if (motorista.getChapa().equals(chapa)){

                        ArrayList<Multa> multasDoMotorista = motorista.getMultas();

                        if (multasDoMotorista.size()>0){
                            for (Multa multa: multasDoMotorista) {
                                System.out.print("Descricao: " + multa.getDescricao());
                                System.out.println(" ,valor: R$" + multa.getValor());
                            }
                            temMulta = true;
                        }
                    }
                }
                if (!temMulta){
                    System.out.println("Não há multas cadastradas nessa chapa!");
                    isRunning = false;
                }
            } else if (escolha == 2) {
                isRunning = false;
            }
        }
    }
    public static void handleAgente(Scanner scanner) {
        System.out.println("Bem vindo Agente!");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    [ 1 ] - Cadastrar com.Erik.automoveis.Motorista\s
                    [ 2 ] - Cadastrar com.Erik.automoveis.Veiculo\s
                    [ 3 ] - Cadastrar com.Erik.automoveis.Multa\s
                    [ 4 ] - Sair do Menu Agente""");

            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1 -> cadastraMotorista(scanner);
                case 2 -> cadastrarVeiculo(scanner);
                case 3 -> cadastraMulta(scanner);
                case 4 -> isRunning = false;
                default -> System.out.println("Opção invalida");
            }
        }
    }
    public static void handleGerente(Scanner scanner){
        System.out.println("Bem vindo Gerente!");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    [ 1 ] - Consultar Multas\s
                    [ 2 ] - Cadastrar com.Erik.automoveis.Motorista\s
                    [ 3 ] - Cadastrar com.Erik.automoveis.Veiculo\s
                    [ 4 ] - Cadastrar com.Erik.automoveis.Multa\s
                    [ 5 ] - Relatorio Geral\s
                    [ 6 ] - Sair do Menu Gerente""");

            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1 -> consultaMulta(scanner, true);
                case 2 -> cadastraMotorista(scanner);
                case 3 -> cadastrarVeiculo(scanner);
                case 4 -> cadastraMulta(scanner);
                case 5 -> consultaRelatorio();
                case 6 -> isRunning = false;
                default -> System.out.println("Opção invalida");
            }
        }
    }
    public static void consultaRelatorio(){
        for (Motorista motorista:motoristas) {
            System.out.println(motorista + "\n");
        }
    }
    public static void cadastraMotorista(Scanner scanner){
        Motorista motorista = askForMotorista(scanner);
        motoristas.add(motorista);
        System.out.println("com.Erik.automoveis.Motorista " + motorista.getNome() + " cadastrado com sucesso");
        System.out.println();
    }
    public static Motorista askForMotorista(Scanner scanner){
        System.out.println("Digite o nome do Motorita: ");
        String nome = scanner.next();

        String chapa = "";
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Digite a chapa do com.Erik.automoveis.Motorista: ");
            chapa = scanner.next();

            boolean jaExiste = false;
            for (Motorista motorista : motoristas) {
                if (motorista.getChapa().equals(chapa)) {
                    jaExiste = true;
                    System.out.println("Essa chapa ja foi utilizada!");
                    break;
                }
            }
            if (!jaExiste) {
                isRunning = false;
            }
        }

        return new Motorista(nome, chapa, null);
    }
    public static void cadastrarVeiculo(Scanner scanner){
        Veiculo veiculo = askForVeiculo(scanner);
        System.out.println("Qual a chapa do motorista a ser vinculado a este veiculo? ");
        String chapa = scanner.next();

        boolean existeMotorista = false;
        for (Motorista motorista: motoristas){
            if (chapa.equals(motorista.getChapa())){
                motorista.setVeiculo(veiculo);
                existeMotorista = true;
            }
        }
        if(!existeMotorista){
            System.out.println("Esse motorista não esta cadastrado! \n" +
                    "Deseja cadastrar um? (y/n)");
            String resposta = scanner.next();

            if (resposta.equals("y")){
                Motorista motorista = askForMotorista(scanner);
                motorista.setVeiculo(veiculo);
                motoristas.add(motorista);
            }else {
                System.out.println("Sem um motorista não é possivel cadastrar um veiculo, encerrando cadastro de veiculo!");
                return;
            }
        }
        System.out.println("com.Erik.automoveis.Veiculo cadastrado com sucesso");
        System.out.println();
    }
    public static Veiculo askForVeiculo(Scanner scanner){
        System.out.println("Digite a placa do veiculo: ");
        String placa = scanner.next();

        System.out.println("Digite a cor do com.Erik.automoveis.Veiculo: ");
        String cor = scanner.next();

        System.out.println("Digite o modelo do veiculo: ");
        String modelo = scanner.next();

        System.out.println("Digite o tipo do veiculo:");

        for (TiposVeiculo tiposVeiculo: TiposVeiculo.values()){
            System.out.println("[ " + tiposVeiculo.getValor() + " ] = " + tiposVeiculo.name());
        }

        int indexTipoVeiculo = scanner.nextInt();

        TiposVeiculo tiposVeiculo = null;

        for (TiposVeiculo tV: TiposVeiculo.values()){
            if (tV.getValor() == indexTipoVeiculo){
                if (indexTipoVeiculo == 1){
                    return new Moto(placa,modelo,cor);
                }if (indexTipoVeiculo == 3) {
                    return new Caminhao(placa, modelo, cor);
                }
                tiposVeiculo = tV;
            }
        }
        return new Veiculo(placa, modelo, cor, tiposVeiculo);
    }
    public static void cadastraMulta(Scanner scanner){
        Multa multa = askForMulta(scanner);
        System.out.println("Qual a chapa do motorista a ser vinculado a este veiculo? ");
        String chapa = scanner.next();

        boolean existeMotorista = false;
        for (Motorista motorista: motoristas){
            if (chapa.equals(motorista.getChapa())){
                motorista.cadastraMulta(multa);
                existeMotorista = true;
            }
        }
        if(!existeMotorista){
            System.out.println("Esse motorista não esta cadastrado! \n" +
                    "Deseja cadastrar um? (y/n)");
            String resposta = scanner.next();

            if (resposta.equals("y")){
                Motorista motorista = askForMotorista(scanner);
                motorista.cadastraMulta(multa);
                motoristas.add(motorista);
            }else {
                System.out.println("Sem um motorista não é possivel cadastrar uma multa, encerrando cadastro de multa!");
                return;
            }
        }
        System.out.println("com.Erik.automoveis.Multa cadastrada com sucesso");
        System.out.println();
    }
    public static Multa askForMulta(Scanner scanner){
        System.out.println("Digite a descricao da multa: ");
        String descricao = scanner.next();

        System.out.println("Digite o valor da multa: ");
        float valor = scanner.nextFloat();
        return new Multa(descricao, valor);
    }
}
