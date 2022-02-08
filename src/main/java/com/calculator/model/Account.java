package com.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
    private String id;
    private String bankName;
    private String agencyNumber;
    private String accountNumber;
}
