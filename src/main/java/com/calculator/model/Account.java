package com.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String id;
    private String bankName;
    private String agencyNumber;
    private String accountNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && bankName.equals(account.bankName) && agencyNumber.equals(account.agencyNumber) && accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankName, agencyNumber, accountNumber);
    }

    @Override
    public String toString() {
        return "Banco " + bankName + '\n' +
                "Agência: ... " + agencyNumber + '\n' +
                "Conta: ..... " + accountNumber + '\n';
    }
}
