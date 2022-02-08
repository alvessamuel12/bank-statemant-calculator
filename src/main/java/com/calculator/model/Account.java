package com.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String id;
    private String bankName;
    private String agencyNumber;
    private String accountNumber;
}
