package application;

import entities.ContaNova;
import entities.ContasCliente;
import entities.enums.TipoConta;
import entities.enums.TipoContaJuridica;
import entities.enums.TipoPessoa;

import java.math.BigDecimal;
import java.util.Scanner;

public class OpcaoDepositar {

    GestaoClientesContas gestaoClientesContas = new GestaoClientesContas();
    public void depositar(){
        int j = 0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite o documento do responsavel (CPF ou CNPJ): ");
    String documento = scanner.next();

    CriadorConta criadorConta = new CriadorConta();
    String tipoPessoaDoc = criadorConta.TipoPessoa(documento);

        System.out.println("Digite o tipo da conta desejado: ");
        if (tipoPessoaDoc.equals(TipoPessoa.PESSOA_FISICA.toString())) {
            System.out.println(j + " - " + TipoConta.CONTA_CORRENTE);
            j++;
            System.out.println(j + " - " + TipoConta.CONTA_POUPANCA);
            j++;
            System.out.println(j + " - " + TipoConta.CONTA_INVESTIMENTO);
            j++;
        } else {
            for (
                TipoContaJuridica tipoContaJuridica : TipoContaJuridica.values()) {
                System.out.println(j + " - " + tipoContaJuridica);
                j++;
            }
        }
        int tipoContaEscolhido = scanner.nextInt();
        TipoConta tipoconta = null;
        switch (tipoContaEscolhido) {
            case 0:
                tipoconta = TipoConta.CONTA_CORRENTE;
                break;
            case 1:
                tipoconta = TipoConta.CONTA_POUPANCA;
                break;
            case 2:
                tipoconta = TipoConta.CONTA_INVESTIMENTO;
                break;
        }


        System.out.println("Informe o valor do deposito ou investimento: ");
        BigDecimal valorDeposito = scanner.nextBigDecimal();

        ContaNova contaNova = gestaoClientesContas.buscaContaCliente(documento, tipoconta);
        if (contaNova!= null){
            contaNova.depositar(valorDeposito);
            System.out.println("Deposito realizado com sucesso!");
        }else {
            System.out.println(" xxxxxx Cliente não possui conta. Crie uma conta para realizar a operação xxxxxx");
        }

    }

}
