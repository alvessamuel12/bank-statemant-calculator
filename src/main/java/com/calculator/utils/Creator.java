package com.calculator.utils;

import com.calculator.model.Account;
import com.calculator.model.Operation;
import com.calculator.model.OperationOptions;
import com.calculator.model.Statement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Creator {

    private static final int DATAHORAOPERCAO = 0;
    private static final int ID_DA_CONTA = 1;
    private static final int NOME_DO_BANCO = 2;
    private static final int NUMERO_DA_AGENCIA = 3;
    private static final int NUMERO_DA_CONTA = 4;
    private static final int OPERADOR = 5;
    private static final int TIPO = 6;
    private static final int VALOR = 7;

    public static Operation createOperation(String[] line) {
        String operador = line[OPERADOR];
        OperationOptions option = OperationOptions.valueOf(line[TIPO]);
        Double valor = Double.valueOf(line[VALOR]);
        LocalDateTime date = LocalDateTime.parse(line[DATAHORAOPERCAO], DateTimeFormatter.ISO_DATE_TIME);
        Account conta = createAccount(line);
        return new Operation(operador, option, valor, date, conta);
    }

    public static Statement createStatement(Account account, Set<Operation> operations) {
        return new Statement(account, operations);
    }

    private static Account createAccount(String[] line) {
        String idDaConta = line[ID_DA_CONTA];
        String nomeDoBanco = line[NOME_DO_BANCO];
        String numeroDaAgencia = line[NUMERO_DA_AGENCIA];
        String numeroDaConta = line[NUMERO_DA_CONTA];
        return new Account(idDaConta, nomeDoBanco, numeroDaAgencia, numeroDaConta);
    }
}
