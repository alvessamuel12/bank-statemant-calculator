package com.calculator;

import com.calculator.model.Account;
import com.calculator.model.Operation;
import com.calculator.model.OperationOptions;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;



public class Main {
    private static final int DATAHORAOPERCAO = 0;
    private static final int ID_DA_CONTA = 1;
    private static final int NOME_DO_BANCO = 2;
    private static final int NUMERO_DA_AGENCIA = 3;
    private static final int NUMERO_DA_CONTA = 4;
    private static final int OPERADOR = 5;
    private static final int TIPO = 6;
    private static final int VALOR = 7;

    public static void main(String[] args) {

        Path path = Paths.get("./src/main/resources/operacoes.csv");

        Reader reader = null;
        List<Operation> operacoes = new LinkedList<>();
        String [] line;
        CSVReader csvReader = null;
        int count = 0;
        try {
            reader = Files.newBufferedReader(path);
            csvReader = new CSVReader(reader);
            while((line = csvReader.readNext()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                operacoes.add(createOperation(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        System.out.println(operacoes.get(0));


//        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);


//        reader.;

//        LocalDateTime data = LocalDateTime.parse("2022-01-08T12:18:40", DateTimeFormatter.ISO_DATE_TIME);

        /*
        * map do nome do operador com um set de operacoes
        * */

        /*
         * carregar as operações para uma estrutura e organizar por conta,
         * ordenar por data e ir efetuando o manejamento da conta
         * somando e subtraindo o saldo até terminar as operações e criar um arquivo relacionado a conta.
         * Fazer isso para todas as contas.
         * */
    }

    private static Operation createOperation(String[] line) {
        String operador = line[OPERADOR];
        OperationOptions option = OperationOptions.valueOf(line[TIPO]);
        Double valor = Double.valueOf(line[VALOR]);
        LocalDateTime date = LocalDateTime.parse(line[DATAHORAOPERCAO], DateTimeFormatter.ISO_DATE_TIME);
        String idDaConta = line[ID_DA_CONTA];
        String nomeDoBanco = line[NOME_DO_BANCO];
        String numeroDaAgencia = line[NUMERO_DA_AGENCIA];
        String numeroDaConta = line[NUMERO_DA_CONTA];
        Account conta = new Account(idDaConta, nomeDoBanco, numeroDaAgencia, numeroDaConta);
        return new Operation(operador, option, valor, date, conta);
    }
}
