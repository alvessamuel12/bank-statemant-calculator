package com.calculator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Operation implements Comparable<Operation> {
    private String operator;
    private OperationOptions type;
    private Integer value;
    private LocalDateTime operationDate;
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return operator.equals(operation.operator) && type == operation.type && value.equals(operation.value) && operationDate.equals(operation.operationDate) && account.equals(operation.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, type, value, operationDate, account);
    }

    @Override
    public int compareTo(Operation o) {
        return this.getOperationDate().compareTo(o.getOperationDate());
    }
}
