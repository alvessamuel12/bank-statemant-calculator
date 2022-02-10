package com.calculator.controller;

import com.calculator.model.Account;
import com.calculator.model.Operation;
import com.calculator.model.Statement;
import com.calculator.utils.Creator;
import com.calculator.utils.ManageFiles;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    List<Operation> operationsData;
    Map<Account, Set<Operation>> operationsByAccount;
    ManageFiles manager = new ManageFiles();
    List<Statement> statements;

    public void run() {
        this.operationsData = manager.readFile("./src/main/resources/operacoes.csv");
        this.operationsByAccount = transformData();
        exportData();
    }

    private Map<Account, Set<Operation>> transformData() {
        return this.operationsData.stream()
            .collect(Collectors.toMap(
                Operation::getAccount,
                operation -> {
                    Set<Operation> operationSet = new TreeSet<>();
                    operationSet.add(operation);
                    return operationSet;
                },
                (acumulador, atual) -> {
                    acumulador.addAll(atual);
                    return acumulador;
                }
            )
        );
    }

    private void exportData() {

        String pathDir = "./statements";
        Path pathDirectory = Paths.get(pathDir);
        try {
            boolean hasCreated = manager.createDirectory(pathDirectory);
            if(!hasCreated) {
                manager.cleanDirectory(pathDirectory);
            }

            this.statements = this.operationsByAccount.keySet().stream()
                    .map(account -> Creator.createStatement(account, this.operationsByAccount.get(account))).toList();

            exportStatementsToDirectory(pathDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportStatementsToDirectory(Path pathDirectory) throws IOException {
        for (Statement statement : statements) {
            String fileName = statement.getAccountID()+".txt";
            String filePath = pathDirectory +"/"+fileName;
            manager.createFile(filePath);
            manager.writeFile(filePath, statement.toString());
        }
    }

}
