package com.splitwise.SplitwiseApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    User from;
    User to;
    int amount;
}
