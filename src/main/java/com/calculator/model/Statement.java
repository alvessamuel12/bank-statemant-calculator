package com.calculator.model;

import java.util.Set;

public class Statement {

    private static final int DATE_COLUMN_LENGTH = 24;
    private static final int TYPE_OPERATION_COLUMN_LENGTH = 16;
    private static final int VALUE_COLUMN_LENGTH = 16;
    private static final String DATE_COLUNM_TITLE = "Data";
    private static final String TYPE_OPERATION_COLUMN_TITLE = "Tipo";
    private static final String VALUE_COLUMN_TITLE = "Valor";
    private static final String OPERATOR_NAME_COLUMN_TITLE = "Operador";

    private Account account;
    private Set<Operation> operations;
    private double balance;
    private StringBuilder builder = new StringBuilder();

    public Statement(Account account, Set<Operation> operations) {
        this.account = account;
        this.operations = operations;
        this.balance = 0;
    }

    public String getAccountID() {
        return account.getId();
    }

    private void addOperation(Operation operation) {
        builder.append(createOperationLine(operation)).append('\n');
    }

    private String createOperationLine(Operation operation) {
        String date = operation.getOperationDate().toString().replace('T', ' ');
        String type = operation.getType().toString();
        String value = ((operation.getType().equals(OperationOptions.DEPOSITO)) ? "+" : "-")+String.format("%.2f", operation.getValue()).replace(',', '.');
        String operatorName = operation.getOperator();
        return date+build(' ', DATE_COLUMN_LENGTH-date.length())
                +type+build(' ', TYPE_OPERATION_COLUMN_LENGTH-type.length())
                +value+build(' ', VALUE_COLUMN_LENGTH-value.length())
                +operatorName;
    }

    private String build(char character, int length) {
        StringBuilder buildedString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buildedString.append(character);
        }
        return buildedString.toString();
    }

    private void buildStatement() {
        builder.append(account).append("\n");
        builder.append(DATE_COLUNM_TITLE).append(build(' ', DATE_COLUMN_LENGTH - DATE_COLUNM_TITLE.length()));
        builder.append(TYPE_OPERATION_COLUMN_TITLE).append(build(' ', TYPE_OPERATION_COLUMN_LENGTH - TYPE_OPERATION_COLUMN_TITLE.length()));
        builder.append(VALUE_COLUMN_TITLE).append(build(' ', VALUE_COLUMN_LENGTH - VALUE_COLUMN_TITLE.length()));
        builder.append(OPERATOR_NAME_COLUMN_TITLE).append("\n\n");
        for (Operation operation : operations) {
            addOperation(operation);
            updateBalance(operation.getType(), operation.getValue());
        }
        String balanceTitle = "\nSaldo: ";
        String totalBalanceLine = balanceTitle + build('.', DATE_COLUMN_LENGTH+TYPE_OPERATION_COLUMN_LENGTH-balanceTitle.length()-1)+String.format("  %.1f", balance).replace(',', '.');
        builder.append(totalBalanceLine);
    }

    private void updateBalance(OperationOptions type, Double value) {
        this.balance += (OperationOptions.DEPOSITO.equals(type) ? value : value*-1);
    }

    @Override
    public String toString() {
        buildStatement();
        return builder.toString();
    }
}
